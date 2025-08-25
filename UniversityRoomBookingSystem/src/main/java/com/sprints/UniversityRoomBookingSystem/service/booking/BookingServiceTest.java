/*package com.sprints.UniversityRoomBookingSystem.service.booking;

import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.model.*;
import com.sprints.UniversityRoomBookingSystem.repository.BookingRepository;
import com.sprints.UniversityRoomBookingSystem.repository.BookingHistoryRepository;
import com.sprints.UniversityRoomBookingSystem.repository.HolidayRepository;
import com.sprints.UniversityRoomBookingSystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingHistoryRepository bookingHistoryRepository;

    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingServiceImp bookingService;

    @Test
    void testAddBooking_Success() {
        // Setup
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Room room = new Room();
        room.setId(1L);
        room.setName("Room A");
        room.setCapacity(20);

        BookingDto dto = new BookingDto();
        dto.setUser(user);
        dto.setRoom(room);
        dto.setPurpose("Meeting");
        dto.setStart_date(LocalDateTime.now().plusHours(1));
        dto.setEnd_date(LocalDateTime.now().plusHours(2));

        when(bookingRepository.findActiveBookings(anyLong(), any(), any())).thenReturn(new ArrayList<>());
        when(holidayRepository.findByStartDateBetween(any(), any())).thenReturn(new ArrayList<>());
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        // Execute
        BookingDto saved = bookingService.addBooking(dto);

        // Verify
        assertEquals("Meeting", saved.getPurpose());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testAddBooking_PastBooking_ThrowsException() {
        User user = new User();
        user.setId(1L);

        Room room = new Room();
        room.setId(1L);

        BookingDto dto = new BookingDto();
        dto.setUser(user);
        dto.setRoom(room);
        dto.setPurpose("Past Meeting");
        dto.setStart_date(LocalDateTime.now().minusDays(1));
        dto.setEnd_date(LocalDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> bookingService.addBooking(dto));
    }
}*/
