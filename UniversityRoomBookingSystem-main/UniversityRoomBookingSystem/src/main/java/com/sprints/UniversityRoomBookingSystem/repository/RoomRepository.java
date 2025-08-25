package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByBuilding_Id(Long buildingId);

    List<Room> findByCapacityGreaterThanEqual(int capacity);

    @Query("""
        SELECT r FROM Room r 
        WHERE r.id NOT IN (
            SELECT b.room.id FROM Booking b 
            WHERE b.start_date < :end 
              AND b.end_date > :start 
              AND b.status = 'APPROVED'
        )
        """)
    List<Room> findAvailableRooms(LocalDateTime start, LocalDateTime end);
}
