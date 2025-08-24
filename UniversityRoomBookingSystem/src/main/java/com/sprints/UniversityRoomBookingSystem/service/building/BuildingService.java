package com.sprints.UniversityRoomBookingSystem.service.building;

import com.sprints.UniversityRoomBookingSystem.dto.BuildingDto;

import java.util.List;

public interface BuildingService {

    public BuildingDto addBuilding(BuildingDto buildingDto);

    public List<BuildingDto> getAllBuildings();

    public BuildingDto getBuildingById(Long id);

    public BuildingDto updateBuilding(Long id, BuildingDto buildingDto);

    public String deleteBuilding(Long id);
}
