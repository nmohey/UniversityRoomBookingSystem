package com.sprints.UniversityRoomBookingSystem.service.building;

import com.sprints.UniversityRoomBookingSystem.dto.BuildingDto;
import com.sprints.UniversityRoomBookingSystem.model.Building;
import com.sprints.UniversityRoomBookingSystem.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImp implements BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingServiceImp(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    private BuildingDto mapToDto(Building building) {
        return new BuildingDto(
                building.getName(),
                building.getBuilding_no()
        );
    }

    private Building mapToEntity(BuildingDto buildingDto) {
        Building building = new Building();
        building.setName(buildingDto.getName());
        building.setBuilding_no(buildingDto.getBuilding_no());
        return building;
    }

    @Override
    public BuildingDto addBuilding(BuildingDto buildingDto) {
        Building building = mapToEntity(buildingDto);
        Building savedBuilding = buildingRepository.save(building);
        return mapToDto(savedBuilding);
    }

    @Override
    public List<BuildingDto> getAllBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingDto getBuildingById(Long id) {
        Optional<Building> building = buildingRepository.findById(id);
        return building.map(this::mapToDto).orElse(null);
    }

    @Override
    public BuildingDto updateBuilding(Long id, BuildingDto buildingDto) {
        Optional<Building> existingBuilding = buildingRepository.findById(id);
        if (existingBuilding.isPresent()) {
            Building building = existingBuilding.get();
            building.setName(buildingDto.getName());
            building.setBuilding_no(buildingDto.getBuilding_no());
            Building updatedBuilding = buildingRepository.save(building);
            return mapToDto(updatedBuilding);
        }
        return null;
    }

    @Override
    public String deleteBuilding(Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
            return "Building with id " + id + " deleted successfully.";
        }
        return "Building with id " + id + " not found.";
    }
}
