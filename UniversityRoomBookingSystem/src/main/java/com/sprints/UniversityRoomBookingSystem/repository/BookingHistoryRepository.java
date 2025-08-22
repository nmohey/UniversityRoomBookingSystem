package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
}
