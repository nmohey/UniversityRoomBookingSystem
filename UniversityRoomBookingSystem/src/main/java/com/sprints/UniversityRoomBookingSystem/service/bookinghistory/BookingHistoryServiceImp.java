package com.sprints.UniversityRoomBookingSystem.service.bookinghistory;

import com.sprints.UniversityRoomBookingSystem.dto.BookingHistoryDto;
import com.sprints.UniversityRoomBookingSystem.model.BookingHistory;
import com.sprints.UniversityRoomBookingSystem.repository.BookingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingHistoryServiceImp implements BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;

    @Autowired
    public BookingHistoryServiceImp(BookingHistoryRepository bookingHistoryRepository) {
        this.bookingHistoryRepository = bookingHistoryRepository;
    }

    private BookingHistoryDto mapToDto(BookingHistory bookingHistory) {
        return new BookingHistoryDto(
                bookingHistory.getTimestamp(),
                bookingHistory.getStatus(),
                bookingHistory.getPerformedBy(),
                bookingHistory.getBooking()
        );
    }

    private BookingHistory mapToEntity(BookingHistoryDto bookingHistoryDto) {
        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setTimestamp(bookingHistoryDto.getTimestamp());
        bookingHistory.setStatus(bookingHistoryDto.getStatus());
        bookingHistory.setPerformedBy(bookingHistoryDto.getPerformedBy());
        bookingHistory.setBooking(bookingHistoryDto.getBooking());
        return bookingHistory;
    }

    @Override
    public BookingHistoryDto addBookingHistory(BookingHistoryDto bookingHistoryDto) {
        BookingHistory bookingHistory = mapToEntity(bookingHistoryDto);
        BookingHistory savedHistory = bookingHistoryRepository.save(bookingHistory);
        return mapToDto(savedHistory);
    }

    @Override
    public List<BookingHistoryDto> getAllBookingHistories() {
        return bookingHistoryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingHistoryDto getBookingHistoryById(Long id) {
        Optional<BookingHistory> bookingHistory = bookingHistoryRepository.findById(id);
        return bookingHistory.map(this::mapToDto).orElse(null);
    }

    @Override
    public BookingHistoryDto updateBookingHistory(Long id, BookingHistoryDto bookingHistoryDto) {
        Optional<BookingHistory> existingHistory = bookingHistoryRepository.findById(id);
        if (existingHistory.isPresent()) {
            BookingHistory history = existingHistory.get();
            history.setTimestamp(bookingHistoryDto.getTimestamp());
            history.setStatus(bookingHistoryDto.getStatus());
            history.setPerformedBy(bookingHistoryDto.getPerformedBy());
            history.setBooking(bookingHistoryDto.getBooking());
            BookingHistory updatedHistory = bookingHistoryRepository.save(history);
            return mapToDto(updatedHistory);
        }
        return null;
    }

    @Override
    public String deleteBookingHistory(Long id) {
        if (bookingHistoryRepository.existsById(id)) {
            bookingHistoryRepository.deleteById(id);
            return "Booking history with id " + id + " deleted successfully.";
        }
        return "Booking history with id " + id + " not found.";
    }
}
