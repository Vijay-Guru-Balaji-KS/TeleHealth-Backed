package com.vijay.TeleHealth.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.HealthMetricResponseDTO;
import com.vijay.TeleHealth.dto.MedicalHistoryResponseDTO;
import com.vijay.TeleHealth.dto.PatientProfileResponseDTO;
import com.vijay.TeleHealth.dto.PatientSideDocInfoDTO;
import com.vijay.TeleHealth.dto.ProviderAvailabilityRequestDTO;
import com.vijay.TeleHealth.dto.ProviderAvailabilityResponseDTO;
import com.vijay.TeleHealth.dto.ProviderSidePatientInfoDTO;
import com.vijay.TeleHealth.dto.UserDTO;
import com.vijay.TeleHealth.entity.ProviderAvailability;
import com.vijay.TeleHealth.entity.ProviderProfile;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.enums.SlotStatus;
import com.vijay.TeleHealth.exception.HealthMetricException;
import com.vijay.TeleHealth.exception.MedicalHistoryException;
import com.vijay.TeleHealth.exception.PatientBookingException;
import com.vijay.TeleHealth.exception.ProfileDetailsException;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.repository.ProviderAvailabilityRepository;
import com.vijay.TeleHealth.repository.ProviderProfileRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProviderAvailabilityServiceImpl {

	@Autowired
	private ProviderAvailabilityRepository availabilityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProviderProfileRepository providerProfileRepository;

	@Autowired
	PatientProfileServiceImpl patientProfileServiceImpl;

	@Autowired
	HealthMetricServiceImpl healthMetricServiceImpl;

	@Autowired
	MedicalHistoryServiceImpl medicalHistoryServiceImpl;

	public List<PatientSideDocInfoDTO> getAllDetailsAboutDoc(LocalDate reqDate) {
		List<User> dbusers = userRepository.findAll();

		return dbusers.stream().map(u -> {
			if (u.getRole().equals(RoleType.ROLE_PROVIDER)) {
				Optional<ProviderProfile> p = providerProfileRepository.findByUserId(u.getId());
				List<ProviderAvailabilityResponseDTO> slots = getAvailableSlots(p.get().getUser().getId(), reqDate);
				return new PatientSideDocInfoDTO(p.get().getUser().getId(), p.get().getUser().getName(),
						p.get().getUser().getEmail(), p.get().getUser().getPhoneNumber(), p.get().getBio(),
						p.get().getSpecialization(), p.get().getCertificationDetails(), p.get().getProfilePhotoPath(),
						slots);
			}
			return null;
		}).filter(p -> {
			return p != null;
		}).collect(Collectors.toList());
	}

	public List<ProviderAvailabilityResponseDTO> getAvailableSlots(Long providerId, LocalDate date) {
		List<ProviderAvailability> slots = availabilityRepository.findByProviderIdAndDateAndIsBookedFalse(providerId,
				date);

		LocalTime now = LocalTime.now();
		LocalDate today = LocalDate.now();

		if (date.equals(today)) {
			List<ProviderAvailability> expiredSlots = slots.stream().filter(slot -> slot.getEndTime().isBefore(now))
					.collect(Collectors.toList());

			if (!expiredSlots.isEmpty()) {
				availabilityRepository.deleteAll(expiredSlots);
				slots.removeAll(expiredSlots);
			}
		}

		slots.sort(Comparator.comparing(ProviderAvailability::getStartTime));

		return slots.stream().map(s -> {
			return new ProviderAvailabilityResponseDTO(s.getId(), s.getStartTime(), s.getEndTime(), s.getSlotStatus(),
					s.getProvider().getId());
		}).collect(Collectors.toList());
	}

	public List<UserDTO> getAllProviders() {
		List<User> db = userRepository.findAll();

		return db.stream().map(u -> {
			if (u.getRole().equals(RoleType.ROLE_PROVIDER))
				return new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getPhoneNumber(),
						u.getRole());
			return null;
		}).filter(p -> {
			return p != null;
		}).collect(Collectors.toList());
	}

	public void addAvailability(ProviderAvailabilityRequestDTO req) throws ProviderAvailabilityException {
		User provider = userRepository.findById(req.getProviderId())
				.orElseThrow(() -> new ProviderAvailabilityException("Provider not found"));

		boolean slotExsists = availabilityRepository.hasOverlappingSlot(req.getProviderId(), req.getDate(),
				req.getStartTime(), req.getEndTime());

		if (slotExsists) {
			throw new ProviderAvailabilityException("Overlapping slot already exsists for the given time.");
		}

		if (!req.getStartTime().isBefore(req.getEndTime())) {
			throw new ProviderAvailabilityException("Start time must be before end time");
		}

		LocalDateTime startDateTime = LocalDateTime.of(req.getDate(), req.getStartTime());
		LocalDateTime endDateTime = LocalDateTime.of(req.getDate(), req.getEndTime());
		LocalDateTime now = LocalDateTime.now();

		if (startDateTime.isBefore(now) || endDateTime.isBefore(now))
			throw new ProviderAvailabilityException("Start or EndTime cannot be before the current date and time");

		if (req.getStartTime().equals(req.getEndTime()))
			throw new ProviderAvailabilityException("Start and End Times cannot be same");

		ProviderAvailability s = new ProviderAvailability();
		s.setProvider(provider);
		s.setDate(req.getDate());
		s.setStartTime(req.getStartTime());
		s.setEndTime(req.getEndTime());
		s.setSlotStatus(SlotStatus.AVAILABLE);

		availabilityRepository.save(s);
	}

	public void deleteSlot(Long providerId, Long slotId) throws ProviderAvailabilityException {
		ProviderAvailability slot = availabilityRepository.findByIdAndProviderId(slotId, providerId)
				.orElseThrow(() -> new ProviderAvailabilityException("Slot not found"));

		availabilityRepository.delete(slot);
	}

	public void updateSlot(Long providerId, Long slotId, ProviderAvailabilityRequestDTO req)
			throws ProviderAvailabilityException {
		ProviderAvailability slot = availabilityRepository.findByIdAndProviderId(slotId, providerId)
				.orElseThrow(() -> new ProviderAvailabilityException("Slot not found"));

		boolean slotExsists = availabilityRepository.hasDuplicateSlot(providerId, req.getDate(), req.getStartTime(),
				req.getEndTime(), slotId);

		if (slotExsists) {
			throw new ProviderAvailabilityException("slot conflict with another availability");
		}

		if (req.getStartTime().equals(req.getEndTime()))
			throw new ProviderAvailabilityException("Start and EndTime cannot be same");

		slot.setDate(req.getDate());
		slot.setStartTime(req.getStartTime());
		slot.setEndTime(req.getEndTime());
		availabilityRepository.save(slot);
	}

	public List<ProviderAvailabilityResponseDTO> getBookedSlots(Long providerId, LocalDate date) {
		List<ProviderAvailability> slots = availabilityRepository.findByProviderIdAndDateAndIsBookedTrue(providerId,
				date);

		slots.sort(Comparator.comparing(ProviderAvailability::getStartTime));

		return slots.stream().map(s -> {
			return new ProviderAvailabilityResponseDTO(s.getId(), s.getStartTime(), s.getEndTime(), s.getSlotStatus(),
					s.getProvider().getId());
		}).collect(Collectors.toList());
	}

	public List<ProviderAvailabilityResponseDTO> showSlotsBasedOnDate(LocalDate date) {
		List<ProviderAvailability> slots = availabilityRepository.findByDate(date);

		slots.sort(Comparator.comparing(ProviderAvailability::getStartTime));
		return slots.stream().map(s -> {
			return new ProviderAvailabilityResponseDTO(s.getId(), s.getStartTime(), s.getEndTime(), s.getSlotStatus(),
					s.getProvider().getId());
		}).collect(Collectors.toList());
	}

	public ProviderSidePatientInfoDTO updateSlotStatusAndGetPatientDetails(Long patient_id, Long slot_id,
			Long provider_id)
			throws PatientBookingException, MedicalHistoryException, HealthMetricException, ProfileDetailsException {
		ProviderSidePatientInfoDTO dto = new ProviderSidePatientInfoDTO();
		dto.setPatientId(patient_id);
		dto.setSlotId(slot_id);
		PatientProfileResponseDTO pp = patientProfileServiceImpl.getProfile(patient_id);
		MedicalHistoryResponseDTO mh = medicalHistoryServiceImpl.getMedicalHistory(patient_id);
		HealthMetricResponseDTO hm = healthMetricServiceImpl.getHealthMetric(patient_id);

		ProviderAvailability slot = availabilityRepository.findByIdAndProviderId(slot_id, provider_id)
				.orElseThrow(() -> new PatientBookingException("No details found for the given providerId and slotId"));

		dto.setMedicalHistory(mh);
		dto.setHealthMetric(hm);
		dto.setPatientProfile(pp);

		slot.setIsBooked(true);
		slot.setSlotStatus(SlotStatus.PENDING);

		availabilityRepository.save(slot);

		ProviderAvailabilityResponseDTO res = new ProviderAvailabilityResponseDTO(slot_id, slot.getStartTime(),
				slot.getEndTime(), slot.getSlotStatus(), provider_id);

		dto.setProviderAvailabilityResponse(res);
		return dto;
	}
}
