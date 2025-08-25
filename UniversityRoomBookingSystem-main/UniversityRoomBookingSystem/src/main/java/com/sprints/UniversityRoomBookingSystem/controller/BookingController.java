import com.sprints.UniversityRoomBookingSystem.dto.BookingDto;
import com.sprints.UniversityRoomBookingSystem.model.BookingStatus;
import com.sprints.UniversityRoomBookingSystem.model.User;
import com.sprints.UniversityRoomBookingSystem.service.booking.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDto bookingDto) {
        try {
            BookingDto savedBooking = bookingService.addBooking(bookingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            BookingDto booking = bookingService.getBookingById(id);
            if (booking == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Booking not found"));
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestParam BookingStatus status,
                                          @RequestAttribute("currentUser") User currentUser) {
        try {
            BookingDto updated = bookingService.updateBookingStatus(id, status, currentUser);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id,
                                           @RequestAttribute("currentUser") User currentUser) {
        try {
            BookingDto cancelled = bookingService.cancelBooking(id, currentUser);
            return ResponseEntity.ok(cancelled);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
