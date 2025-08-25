package com.sprints.UniversityRoomBookingSystem.service.holiday;

import com.sprints.UniversityRoomBookingSystem.dto.HolidayDto;

import java.util.List;

public interface HolidayService {

    public HolidayDto addHoliday(HolidayDto holidayDto);

    public List<HolidayDto> getAllHolidays();

    public HolidayDto getHolidayById(Long id);

    public HolidayDto updateHoliday(Long id, HolidayDto holidayDto);

    public String deleteHoliday(Long id);
}
