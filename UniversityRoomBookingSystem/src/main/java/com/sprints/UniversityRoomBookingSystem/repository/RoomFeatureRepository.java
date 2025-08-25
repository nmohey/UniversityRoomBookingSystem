package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.RoomFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Long> {

    Optional<RoomFeature> findByFeature(String name);
}
