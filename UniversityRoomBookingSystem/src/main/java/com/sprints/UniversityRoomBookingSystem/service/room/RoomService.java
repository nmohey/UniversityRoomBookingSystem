package com.sprints.UniversityRoomBookingSystem.service.room;

import com.sprints.UniversityRoomBookingSystem.dto.RoomDto;

import java.util.List;

public interface RoomService {

    public RoomDto addRoom(RoomDto roomDto);

    public List<RoomDto> getAllRooms();

    public RoomDto getRoomById(Long id);

    public RoomDto updateRoom(Long id, RoomDto roomDto);

    public String deleteRoom(Long id);
}
