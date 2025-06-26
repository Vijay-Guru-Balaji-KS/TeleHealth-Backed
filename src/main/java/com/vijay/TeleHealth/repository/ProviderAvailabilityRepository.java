package com.vijay.TeleHealth.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijay.TeleHealth.entity.ProviderAvailability;

public interface ProviderAvailabilityRepository extends JpaRepository<ProviderAvailability, Long> {

	List<ProviderAvailability> findByDate(LocalDate date);

	Optional<ProviderAvailability> findByIdAndProviderId(Long slotId, Long providerId);

	@Query("select count(pa) > 0 from ProviderAvailability pa where pa.provider.id=:providerId and pa.date=:date and pa.startTime=:startTime and pa.endTime=:endTime and pa.id!=:slotId")
	boolean hasDuplicateSlot(Long providerId, LocalDate date, LocalTime startTime, LocalTime endTime, Long slotId);

	List<ProviderAvailability> findByProviderIdAndDateAndIsBookedFalse(Long providerId, LocalDate date);

	List<ProviderAvailability> findByProviderIdAndDateAndIsBookedTrue(Long providerId, LocalDate date);

	@Query("select count(p)>0 from ProviderAvailability p where p.provider.id=:providerId and p.date=:date and p.startTime = :startTime and p.endTime=:endTime")
	public Boolean hasDuplicateSlot(@Param("providerId") Long providerId, @Param("date") LocalDate date,
			@Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

	void delete(ProviderAvailability slot);

	@Query("select count(p) > 0 from ProviderAvailability p where p.provider.id = :providerId and p.date=:date and ((:startTime < p.endTime and :endTime > p.startTime))")
	boolean hasOverlappingSlot(@Param("providerId") Long provierId, @Param("date") LocalDate date,
			@Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);
}
