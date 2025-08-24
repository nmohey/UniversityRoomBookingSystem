package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Holiday findByStartDate(LocalDateTime startDate);

    List<Holiday> findByStartDateBetween(LocalDateTime start, LocalDateTime end);

    Holiday findByEndDate(LocalDateTime endDate);

    List<Holiday> findByEndDateBetween(LocalDateTime start, LocalDateTime end);
}
