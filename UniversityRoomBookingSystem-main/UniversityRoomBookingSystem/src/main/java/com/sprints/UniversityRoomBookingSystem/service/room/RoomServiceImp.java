package com.sprints.UniversityRoomBookingSystem.service.room;

import com.sprints.UniversityRoomBookingSystem.dto.RoomDto;
import com.sprints.UniversityRoomBookingSystem.model.Room;
import com.sprints.UniversityRoomBookingSystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private RoomDto mapToDto(Room room) {
        return new RoomDto(
                room.getRoom_no(),
                room.getCapacity(),
                room.getBuilding(),
                room.getFeatures()
        );
    }

    private Room mapToEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setRoom_no(roomDto.getRoom_no());
        room.setCapacity(roomDto.getCapacity());
        room.setBuilding(roomDto.getBuilding());
        room.setFeatures(roomDto.getFeatures());
        return room;
    }

    @Override
    public RoomDto addRoom(RoomDto roomDto) {
        Room room = mapToEntity(roomDto);
        Room savedRoom = roomRepository.save(room);
        return mapToDto(savedRoom);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.map(this::mapToDto).orElse(null);
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        Optional<Room> existingRoom = roomRepository.findById(id);
        if (existingRoom.isPresent()) {
            Room room = existingRoom.get();
            room.setRoom_no(roomDto.getRoom_no());
            room.setCapacity(roomDto.getCapacity());
            room.setBuilding(roomDto.getBuilding());
            room.setFeatures(roomDto.getFeatures());
            Room updatedRoom = roomRepository.save(room);
            return mapToDto(updatedRoom);
        }
        return null;
    }

    @Override
    public String deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return "Room with id " + id + " deleted successfully.";
        }
        return "Room with id " + id + " not found.";
    }
}
