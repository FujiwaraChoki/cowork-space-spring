package ch.zli.m223.model.impl;

import ch.zli.m223.model.Duration;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "duration")
public class DurationImpl implements Duration {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String duration;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDuration() {
        return duration;
    }
}
