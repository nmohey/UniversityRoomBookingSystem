package com.sprints.UniversityRoomBookingSystem.service.booking;

import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import com.sprints.UniversityRoomBookingSystem.model.User;

import java.util.List;

public interface BookingService {

    public BookingDto addBooking(BookingDto bookingDto);

    public List<BookingDto> getAllBookings();

    public BookingDto getBookingById(Long id);

    public BookingDto updateBooking(Long id, BookingDto bookingDto);

    public String deleteBooking(Long id);

    BookingDto updateBookingStatus(Long id, BookingStatus status, User currentUser);

    BookingDto cancelBooking(Long id, User currentUser);
}
