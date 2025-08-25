package com.sprints.UniversityRoomBookingSystem.service.roomfeature;

import com.sprints.UniversityRoomBookingSystem.dto.RoomFeatureDto;

import java.util.List;

public interface RoomFeatureService {

    public RoomFeatureDto addFeature(RoomFeatureDto roomFeatureDto);

    public List<RoomFeatureDto> getAllFeatures();

    public RoomFeatureDto getFeatureById(Long id);

    public RoomFeatureDto updateFeature(Long id, RoomFeatureDto roomFeatureDto);

    public String deleteFeature(Long id);
}
