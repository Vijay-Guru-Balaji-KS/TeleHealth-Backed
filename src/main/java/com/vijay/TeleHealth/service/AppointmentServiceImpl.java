package com.vijay.TeleHealth.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.TeleHealth.dto.ApiResponse;
import com.vijay.TeleHealth.dto.CompletePatientDetailDTO;
import com.vijay.TeleHealth.dto.PartialDocInfoForPatientDTO;
import com.vijay.TeleHealth.dto.PrescriptionResponseDTO;
import com.vijay.TeleHealth.dto.ProviderAvailabilityResponseDTO;
import com.vijay.TeleHealth.entity.Appointment;
import com.vijay.TeleHealth.entity.HealthMetrics;
import com.vijay.TeleHealth.entity.MedicalHistory;
import com.vijay.TeleHealth.entity.PatientProfile;
import com.vijay.TeleHealth.entity.ProviderAvailability;
import com.vijay.TeleHealth.entity.SugarLevels;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.AppointmentStatus;
import com.vijay.TeleHealth.enums.SlotStatus;
import com.vijay.TeleHealth.exception.AppointmentException;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.repository.AppointmentRepository;
import com.vijay.TeleHealth.repository.HealthMetricRepository;
import com.vijay.TeleHealth.repository.MedicalHistoryRepository;
import com.vijay.TeleHealth.repository.PatientProfileRepository;
import com.vijay.TeleHealth.repository.ProviderAvailabilityRepository;
import com.vijay.TeleHealth.repository.SugarLevelsRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl {
	@Autowired
	private EmailService emailService;

	@Autowired
	private ProviderAvailabilityRepository availabilityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private HealthMetricRepository healthMetricRepository;

	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;

	@Autowired
	private PatientProfileRepository patientProfileRepository;

	@Autowired
	private SugarLevelsRepository sugarLevelsRepository;

	@Autowired
	private FileStorageService fileStorageService;

	public Long getProviderId(String name) {
		User user = userRepository.findByName(name);
		return user.getId();
	}

	public ApiResponse patientBookSlot(LocalDate date, Long patientId, Long ProviderId, Long slotId)
			throws CustomException, ProviderAvailabilityException {
		User provider = userRepository.findById(ProviderId)
				.orElseThrow(() -> new CustomException("Provider is not available"));
		User patient = userRepository.findById(patientId)
				.orElseThrow(() -> new CustomException("Patient is not available"));

		ProviderAvailability slot = availabilityRepository.findByIdAndProviderId(slotId, ProviderId)
				.orElseThrow(() -> new ProviderAvailabilityException("There is no slot with the required slotid"));

		slot.setIsBooked(true);
		slot.setSlotStatus(SlotStatus.PENDING);

		availabilityRepository.save(slot);

		Appointment appointment = new Appointment(date, AppointmentStatus.PENDING, patient, provider, false, slotId,
				null, null, null, null);

		appointmentRepository.save(appointment);

		Optional<User> docOpt = userRepository.findById(appointment.getProvider().getId());
		User doctor = docOpt.get();

		String docEmail = doctor.getEmail();
		String subject = "Appointment request from " + appointment.getPatient().getName();
		String body = "Respected" + doctor.getName() + ",\n\n" + "You received a appointment request on"
				+ appointment.getDate() + "for the slot" + slot.getStartTime() + "-" + slot.getEndTime() + "\n\n"
				+ "Please approve or reject the slot \n \n Note : \n Once the endTime exceeds the current Time then it will automatically get rejected";

		emailService.sendEmail(docEmail, subject, body);

		return new ApiResponse("Booking request sent from patient side");
	}

	public CompletePatientDetailDTO getPatientCompleteDetail(Long patient_id) throws CustomException {
		CompletePatientDetailDTO dto = new CompletePatientDetailDTO();
		dto.setId(patient_id);
		PatientProfile profile = patientProfileRepository.findByUserId(patient_id)
				.orElseThrow(() -> new CustomException("No patient found with the given patient id"));
		dto.setGender(profile.getGender());
		dto.setDateOfBirth(profile.getDateOfBirth());
		dto.setAddress(profile.getAddress());
		dto.setInsuranceNumber(profile.getInsuranceNumber());
		dto.setInsuranceProvider(profile.getInsuranceProvider());
		dto.setPhoneNumber(profile.getPhoneNumber());
		Optional<MedicalHistory> medical = medicalHistoryRepository.findByUserId(patient_id);
		dto.setAllergies(medical.get().getAllergies());
		dto.setPreviousConditions(medical.get().getPreviousConditions());
		dto.setMedications(medical.get().getMedications());
		Optional<SugarLevels> sugar = sugarLevelsRepository.findUserById(patient_id);
		dto.setFastingLvl(sugar.get().getFastingLvl());
		dto.setPostMealLvl(sugar.get().getPostMealLvl());
		dto.setRandomLvl(sugar.get().getRandomLvl());
		Optional<HealthMetrics> metric = healthMetricRepository.findById(sugar.get().getId());
		dto.setHeight(metric.get().getHeight());
		dto.setWeight(metric.get().getWeight());
		dto.setBloodPressure(metric.get().getBloodPressure());
		dto.setHeartRate(metric.get().getHeartRate());
		dto.setPatientName(profile.getUser().getName());
		return dto;
	}

	public List<CompletePatientDetailDTO> getAllAppointmentsForProvider(Long providerId) {
		List<Appointment> appointments = appointmentRepository.findByProviderId(providerId);

		return appointments.stream().map(a -> {
			try {
				CompletePatientDetailDTO res = getPatientCompleteDetail(a.getPatient().getId());
				res.setSlotId(a.getSlot_id());
				try {
					ProviderAvailability availability = availabilityRepository.findById(a.getSlot_id())
							.orElseThrow(() -> new ProviderAvailabilityException(
									"There is no provider and no slot details available for the requested slot Id"));
					res.setAppointmentDate(availability.getDate());
					res.setStartTime(availability.getStartTime());
					res.setEndTime(availability.getEndTime());
					res.setStatus(availability.getSlotStatus());

				} catch (ProviderAvailabilityException e) {
					e.printStackTrace();
				}
				return res;
			} catch (CustomException e) {
				e.printStackTrace();
				return null;
			}
		}).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public List<CompletePatientDetailDTO> getAllPendingPatientDetails(Long providerId) {
		List<Appointment> appointments = appointmentRepository.findByProvider_IdAndStatus(providerId,
				AppointmentStatus.PENDING);

		return appointments.stream().map(a -> {
			try {
				CompletePatientDetailDTO res = getPatientCompleteDetail(a.getPatient().getId());
				res.setSlotId(a.getSlot_id());
				try {
					ProviderAvailability availability = availabilityRepository.findById(a.getSlot_id())
							.orElseThrow(() -> new ProviderAvailabilityException(
									"There is no provider and no slot details available for the requested slot Id"));
					res.setAppointmentDate(availability.getDate());
					res.setStartTime(availability.getStartTime());
					res.setEndTime(availability.getEndTime());
					res.setStatus(availability.getSlotStatus());

				} catch (ProviderAvailabilityException e) {
					e.printStackTrace();
				}
				return res;
			} catch (CustomException e) {
				e.printStackTrace();
				return null;
			}
		}).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public ApiResponse ApproveOrRejectSlot(Long slotId, AppointmentStatus providerResponseStatus)
			throws AppointmentException, ProviderAvailabilityException {
		Appointment appointment = appointmentRepository.findBySlotId(slotId)
				.orElseThrow(() -> new AppointmentException("No appointment available with the required slot Id"));
		if (!appointment.getStatus().equals(AppointmentStatus.PENDING))
			throw new AppointmentException("Status can be changed only for the pending slots");
		else {
			ProviderAvailability availability = availabilityRepository
					.findByIdAndProviderId(slotId, appointment.getProvider().getId())
					.orElseThrow(() -> new ProviderAvailabilityException(
							"Provider not available for the given slotId and providerId"));
			if (providerResponseStatus.equals(AppointmentStatus.APPROVED)) {
				availability.setSlotStatus(SlotStatus.BOOKED);
				appointment.setStatus(providerResponseStatus);
				appointmentRepository.save(appointment);
				availabilityRepository.save(availability);

				Optional<User> patientOpt = userRepository.findById(appointment.getPatient().getId());
				User patient = patientOpt.get();

				String patientEmail = patient.getEmail();
				String subject = "Appointment" + AppointmentStatus.APPROVED.toString().toLowerCase();
				String body = "Dear" + appointment.getPatient().getName();
				emailService.sendEmail(patientEmail, subject, body);

				return new ApiResponse("Provider accepted your booking request for the slot" + slotId);
			} else if (providerResponseStatus.equals(AppointmentStatus.REJECTED)) {
				availability.setSlotStatus(SlotStatus.REJECTED);
				appointment.setStatus(providerResponseStatus);
				appointmentRepository.save(appointment);
				availabilityRepository.save(availability);

				Optional<User> patientOpt = userRepository.findById(appointment.getPatient().getId());
				User patient = patientOpt.get();

				String patientEmail = patient.getEmail();
				String subject = "Appointment" + AppointmentStatus.APPROVED.toString().toLowerCase();
				String body = "Dear" + appointment.getPatient().getName();
				emailService.sendEmail(patientEmail, subject, body);

				return new ApiResponse("Provider rejected your booking request for the slot" + slotId);
			}
		}
		return null;
	}

	public ApiResponse submitFollowUpDetails(Long appointmentId, String dietAdvice, String exerciseSuggestion,
			MultipartFile prescriptionFile, String notes) throws IOException, AppointmentException {
		Appointment appointment = appointmentRepository.findBySlotId(appointmentId)
				.orElseThrow(() -> new AppointmentException("No appointment available with the req Id"));

		appointment.setDietAdvice(dietAdvice);
		appointment.setExerciseSuggestion(exerciseSuggestion);
		appointment.setPrescriptionFilePath(fileStorageService.storeFile(prescriptionFile, "prescriptions"));
		appointment.setNotes(notes);

		appointmentRepository.save(appointment);

		return new ApiResponse("Follow up submitted successfully");
	}

	public PrescriptionResponseDTO getPrescriptionDetails(Long appointmentId) throws AppointmentException {
		Appointment appointment = appointmentRepository.findBySlotId(appointmentId)
				.orElseThrow(() -> new AppointmentException("No appointment avlb to view prescriptions"));
		PrescriptionResponseDTO dto = new PrescriptionResponseDTO(appointmentId, appointment.getDietAdvice(),
				appointment.getExerciseSuggestion(), appointment.getPrescriptionFilePath(),
				appointment.getProvider().getId(), appointment.getNotes());
		return dto;
	}

	public List<PartialDocInfoForPatientDTO> getAllPatientSlots(Long patientId) {
		List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);

		return appointments.stream().map(appointment -> {
			PartialDocInfoForPatientDTO dto = new PartialDocInfoForPatientDTO();
			dto.setSlotId(appointment.getSlot_id());
			dto.setAppointmentDate(appointment.getDate());
			dto.setAppointmentStatus(appointment.getStatus());
			dto.setNotes(appointment.getNotes());

			if (appointment.getProvider() != null)
				dto.setDocName(appointment.getProvider().getName());
			else
				dto.setDocName("N/A");

			Optional<ProviderAvailability> slotOpt = availabilityRepository.findById(appointment.getSlot_id());
			slotOpt.ifPresent(slot -> {
				dto.setStartTime(slot.getStartTime());
				dto.setEndTime(slot.getEndTime());
			});

			String prescriptionPath = appointment.getPrescriptionFilePath();
			if (prescriptionPath != null && !prescriptionPath.isEmpty()) {
				String fullPath = "http://localhost:8080" + prescriptionPath.replace("\\", "/");
				dto.setPrescriptionPath(fullPath);
			}

			dto.setWorkOut(appointment.getExerciseSuggestion());
			dto.setDiet(appointment.getDietAdvice());
			dto.setDocId(appointment.getProvider().getId());
			return dto;
		}).filter(u -> u != null).collect(Collectors.toList());
	}

	public boolean cancelAppointmentById(Long slotId) throws ProviderAvailabilityException, AppointmentException {
		Appointment appointment = appointmentRepository.findBySlotId(slotId)
				.orElseThrow(() -> new AppointmentException("No appointment available with the required slot Id"));

		appointment.setStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.delete(appointment);

		ProviderAvailability availability = availabilityRepository
				.findByIdAndProviderId(appointment.getSlot_id(), appointment.getProvider().getId())
				.orElseThrow(() -> new ProviderAvailabilityException("No provider detail available"));

		Optional<User> patientOpt = userRepository.findById(appointment.getPatient().getId());
		User patient = patientOpt.get();

		String patientEmail = patient.getEmail();
		String subject = "Appointment" + AppointmentStatus.CANCELLED.toString().toLowerCase();
		String body = "Dear" + appointment.getPatient().getName();
		emailService.sendEmail(patientEmail, subject, body);

		availability.setIsBooked(false);
		availability.setSlotStatus(SlotStatus.AVAILABLE);
		availabilityRepository.save(availability);
		return true;
	}
}
