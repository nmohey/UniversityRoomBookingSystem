package com.sprints.UniversityRoomBookingSystem.service.holiday;

import com.sprints.UniversityRoomBookingSystem.dto.HolidayDto;
import com.sprints.UniversityRoomBookingSystem.model.Holiday;
import com.sprints.UniversityRoomBookingSystem.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImp implements HolidayService {

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayServiceImp(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    private HolidayDto mapToDto(Holiday holiday) {
        return new HolidayDto(
                holiday.getDescription(),
                holiday.getStartDate(),
                holiday.getEndDate()
        );
    }

    private Holiday mapToEntity(HolidayDto holidayDto) {
        Holiday holiday = new Holiday();
        holiday.setDescription(holidayDto.getDescription());
        holiday.setStartDate(holidayDto.getStartDate());
        holiday.setEndDate(holidayDto.getEndDate());
        return holiday;
    }

    @Override
    public HolidayDto addHoliday(HolidayDto holidayDto) {
        Holiday holiday = mapToEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return mapToDto(savedHoliday);
    }

    @Override
    public List<HolidayDto> getAllHolidays() {
        return holidayRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HolidayDto getHolidayById(Long id) {
        Optional<Holiday> holiday = holidayRepository.findById(id);
        return holiday.map(this::mapToDto).orElse(null);
    }

    @Override
    public HolidayDto updateHoliday(Long id, HolidayDto holidayDto) {
        Optional<Holiday> existingHoliday = holidayRepository.findById(id);
        if (existingHoliday.isPresent()) {
            Holiday holiday = existingHoliday.get();
            holiday.setDescription(holidayDto.getDescription());
            holiday.setStartDate(holidayDto.getStartDate());
            holiday.setEndDate(holidayDto.getEndDate());
            Holiday updatedHoliday = holidayRepository.save(holiday);
            return mapToDto(updatedHoliday);
        }
        return null;
    }

    @Override
    public String deleteHoliday(Long id) {
        if (holidayRepository.existsById(id)) {
            holidayRepository.deleteById(id);
            return "Holiday with id " + id + " deleted successfully.";
        }
        return "Holiday with id " + id + " not found.";
    }
}
