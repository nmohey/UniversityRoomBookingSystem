package com.sprints.UniversityRoomBookingSystem.service.roomfeature;

import com.sprints.UniversityRoomBookingSystem.dto.RoomFeatureDto;
import com.sprints.UniversityRoomBookingSystem.model.RoomFeature;
import com.sprints.UniversityRoomBookingSystem.repository.RoomFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomFeatureServiceImp implements RoomFeatureService {

    private final RoomFeatureRepository roomFeatureRepository;

    @Autowired
    public RoomFeatureServiceImp(RoomFeatureRepository roomFeatureRepository) {
        this.roomFeatureRepository = roomFeatureRepository;
    }

    private RoomFeatureDto mapToDto(RoomFeature feature) {
        return new RoomFeatureDto(
                feature.getFeature(),
                feature.getRooms()
        );
    }

    private RoomFeature mapToEntity(RoomFeatureDto dto) {
        RoomFeature feature = new RoomFeature();
        feature.setFeature(dto.getFeature());
        feature.setRooms(dto.getRooms());
        return feature;
    }

    @Override
    public RoomFeatureDto addFeature(RoomFeatureDto roomFeatureDto) {
        RoomFeature feature = mapToEntity(roomFeatureDto);
        RoomFeature savedFeature = roomFeatureRepository.save(feature);
        return mapToDto(savedFeature);
    }

    @Override
    public List<RoomFeatureDto> getAllFeatures() {
        return roomFeatureRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomFeatureDto getFeatureById(Long id) {
        Optional<RoomFeature> feature = roomFeatureRepository.findById(id);
        return feature.map(this::mapToDto).orElse(null);
    }

    @Override
    public RoomFeatureDto updateFeature(Long id, RoomFeatureDto roomFeatureDto) {
        Optional<RoomFeature> existingFeature = roomFeatureRepository.findById(id);
        if (existingFeature.isPresent()) {
            RoomFeature feature = existingFeature.get();
            feature.setFeature(roomFeatureDto.getFeature());
            feature.setRooms(roomFeatureDto.getRooms());
            RoomFeature updatedFeature = roomFeatureRepository.save(feature);
            return mapToDto(updatedFeature);
        }
        return null;
    }

    @Override
    public String deleteFeature(Long id) {
        if (roomFeatureRepository.existsById(id)) {
            roomFeatureRepository.deleteById(id);
            return "RoomFeature with id " + id + " deleted successfully.";
        }
        return "RoomFeature with id " + id + " not found.";
    }
}
