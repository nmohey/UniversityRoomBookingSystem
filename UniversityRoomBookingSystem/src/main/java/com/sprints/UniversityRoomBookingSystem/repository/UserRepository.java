package com.sprints.UniversityRoomBookingSystem.repository;

import com.sprints.UniversityRoomBookingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
