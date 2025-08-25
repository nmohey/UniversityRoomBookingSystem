package com.sprints.UniversityRoomBookingSystem.service.booking;

import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.model.*;
import com.sprints.UniversityRoomBookingSystem.repository.BookingRepository;
import com.sprints.UniversityRoomBookingSystem.repository.BookingHistoryRepository;
import com.sprints.UniversityRoomBookingSystem.repository.HolidayRepository;
import com.sprints.UniversityRoomBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingHistoryRepository bookingHistoryRepository;
    private final HolidayRepository holidayRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository,
                             BookingHistoryRepository bookingHistoryRepository,
                             HolidayRepository holidayRepository,
                             UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.holidayRepository = holidayRepository;
        this.userRepository = userRepository;
    }

    private Booking mapToEntity(BookingDto dto) {
        Booking booking = new Booking();
        booking.setPurpose(dto.getPurpose());
        booking.setStart_date(dto.getStart_date());
        booking.setEnd_date(dto.getEnd_date());
        booking.setStatus(dto.getStatus());
        booking.setRoom(dto.getRoom());
        booking.setUser(dto.getUser());
        return booking;
    }

    private BookingDto mapToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setPurpose(booking.getPurpose());
        dto.setStart_date(booking.getStart_date());
        dto.setEnd_date(booking.getEnd_date());
        dto.setStatus(booking.getStatus());
        dto.setRoom(booking.getRoom());
        dto.setUser(booking.getUser());
        return dto;
    }

    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        LocalDateTime now = LocalDateTime.now();
        if (bookingDto.getStart_date().isBefore(now))
            throw new IllegalArgumentException("Cannot book in the past.");

        if (bookingDto.getStart_date().isAfter(now.plusDays(90)))
            throw new IllegalArgumentException("Cannot book more than 90 days in advance.");

        if (holidayRepository.existsByStartDate(bookingDto.getStart_date()))
            throw new IllegalArgumentException("Cannot book on holidays.");

        List<Booking> conflicts = bookingRepository.findActiveBookings(
                bookingDto.getRoom().getId(),
                bookingDto.getStart_date(),
                bookingDto.getEnd_date()
        );
        if (!conflicts.isEmpty())
            throw new IllegalArgumentException("Room is already booked at this time.");

        Booking booking = mapToEntity(bookingDto);
        booking.setStatus(BookingStatus.PENDING); // default status
        Booking saved = bookingRepository.save(booking);

        bookingHistoryRepository.save(new BookingHistory(
                saved, null, BookingStatus.PENDING, saved.getUser()
        ));

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
        return null;
    }

    @Override
    public String deleteBooking(Long id) {
        return "";
    }

    @Override
    public BookingDto updateBookingStatus(Long id, BookingStatus newStatus, User currentUser) {
        return bookingRepository.findById(id)
                .map(existing -> {
                    if ((newStatus == BookingStatus.APPROVED || newStatus == BookingStatus.REJECTED)
                            && !currentUser.getRoles().contains(Role.ADMIN)) {
                        throw new IllegalArgumentException("Only admins can approve/reject.");
                    }

                    BookingStatus oldStatus = existing.getStatus();
                    existing.setStatus(newStatus);
                    Booking updated = bookingRepository.save(existing);

                    bookingHistoryRepository.save(new BookingHistory(
                            updated, oldStatus, newStatus, currentUser
                    ));
                    return mapToDto(updated);
                }).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    @Override
    public BookingDto cancelBooking(Long id, User currentUser) {
        return bookingRepository.findById(id)
                .map(existing -> {
                    if (!existing.getUser().equals(currentUser)
                            && !currentUser.getRoles().contains(Role.ADMIN)) {
                        throw new IllegalArgumentException("Cannot cancel others' bookings.");
                    }

                    if (existing.getEnd_date().isBefore(LocalDateTime.now())) {
                        throw new IllegalArgumentException("Cannot cancel past bookings.");
                    }

                    BookingStatus oldStatus = existing.getStatus();
                    existing.setStatus(BookingStatus.CANCELLED);
                    Booking updated = bookingRepository.save(existing);

                    bookingHistoryRepository.save(new BookingHistory(
                            updated, oldStatus, BookingStatus.CANCELLED, currentUser
                    ));
                    return mapToDto(updated);
                }).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }
}
