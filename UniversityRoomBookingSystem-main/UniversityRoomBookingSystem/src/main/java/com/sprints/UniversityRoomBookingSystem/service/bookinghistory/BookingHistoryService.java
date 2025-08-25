package com.sprints.UniversityRoomBookingSystem.service.bookinghistory;

import com.sprints.UniversityRoomBookingSystem.dto.BookingHistoryDto;

import java.util.List;

public interface BookingHistoryService {

    public BookingHistoryDto addBookingHistory(BookingHistoryDto bookingHistoryDto);

    public List<BookingHistoryDto> getAllBookingHistories();

    public BookingHistoryDto getBookingHistoryById(Long id);

    public BookingHistoryDto updateBookingHistory(Long id, BookingHistoryDto bookingHistoryDto);

    public String deleteBookingHistory(Long id);
}
