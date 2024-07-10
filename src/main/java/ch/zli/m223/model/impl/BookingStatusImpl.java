package ch.zli.m223.model.impl;

import ch.zli.m223.model.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class BookingStatusImpl implements BookingStatus {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
