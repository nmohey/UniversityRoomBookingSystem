package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    boolean existsByStartDate(LocalDateTime startDate);

    boolean existsByEndDate(LocalDateTime endDate);

    List<Holiday> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    List<Holiday> findByEndDateBetween(LocalDateTime start, LocalDateTime end);

    default boolean isHoliday(LocalDateTime dateTime) {
        return existsByStartDate(dateTime) || existsByEndDate(dateTime)
                || !findByStartDateBetween(dateTime, dateTime).isEmpty()
                || !findByEndDateBetween(dateTime, dateTime).isEmpty();
    }
}
