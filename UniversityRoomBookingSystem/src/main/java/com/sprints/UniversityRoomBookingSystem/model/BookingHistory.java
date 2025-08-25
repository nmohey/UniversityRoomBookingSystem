package com.sprints.UniversityRoomBookingSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String note;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User changedBy;

    public BookingHistory() {}

    public BookingHistory(Booking booking, BookingStatus oldStatus, BookingStatus newStatus, User changedBy) {
        this.timestamp = LocalDateTime.now();
        this.booking = booking;
        this.status = newStatus;
        this.changedBy = changedBy;
        this.note = (oldStatus == null ? "Initial status set to " : "Changed from " + oldStatus + " to ") + newStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public User getChangedBy() { return changedBy; }
    public void setChangedBy(User changedBy) { this.changedBy = changedBy; }
}
