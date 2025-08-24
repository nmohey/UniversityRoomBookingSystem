package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser_Id(Long userId);

    @Query("""
        SELECT b FROM Booking b 
        WHERE b.room.id = :roomId 
          AND b.start_date < :end 
          AND b.end_date > :start 
          AND b.status = 'APPROVED'
        """)
    List<Booking> findActiveBookings(Long roomId, LocalDateTime start, LocalDateTime end);

    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = com.sprints.UniversityRoomBookingSystem.model.BookingStatus.CANCELLED WHERE b.id = :bookingId")
    void cancelBooking(Long bookingId);

    List<Booking> findByStatus(BookingStatus status);
}
