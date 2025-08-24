package com.sprints.UniversityRoomBookingSystem.service.booking;

import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.model.Booking;
import com.sprints.UniversityRoomBookingSystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    private Booking mapToEntity(BookingDto dto) {
        Booking booking = new Booking();
        booking.setPurpose(dto.getPurpose());
        booking.setStart_date(dto.getStart_date());
        booking.setEnd_date(dto.getEnd_date());
        booking.setStatus(dto.getStatus());
        booking.setRoom(dto.getRoom());
        return booking;
    }

    private BookingDto mapToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setPurpose(booking.getPurpose());
        dto.setStart_date(booking.getStart_date());
        dto.setEnd_date(booking.getEnd_date());
        dto.setStatus(booking.getStatus());
        dto.setRoom(booking.getRoom());
        return dto;
    }

    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        Booking booking = mapToEntity(bookingDto);
        Booking saved = bookingRepository.save(booking);
        return mapToDto(saved);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    @Override
    public BookingDto updateBooking(Long id, BookingDto bookingDto) {
        return bookingRepository.findById(id)
                .map(existing -> {
                    existing.setPurpose(bookingDto.getPurpose());
                    existing.setStart_date(bookingDto.getStart_date());
                    existing.setEnd_date(bookingDto.getEnd_date());
                    existing.setStatus(bookingDto.getStatus());
                    existing.setRoom(bookingDto.getRoom());
                    Booking updated = bookingRepository.save(existing);
                    return mapToDto(updated);
                })
                .orElse(null);
    }

    @Override
    public String deleteBooking(Long id) {
        return bookingRepository.findById(id).map(existing -> {
            bookingRepository.delete(existing);
            return "Booking with id " + id + " deleted successfully.";
        }).orElse("Booking with id " + id + " not found.");
    }
}
