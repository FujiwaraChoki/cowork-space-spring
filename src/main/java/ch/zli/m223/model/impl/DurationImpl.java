package ch.zli.m223.model.impl;

import ch.zli.m223.model.Duration;
import jakarta.persistence.*;

@Entity(name = "duration")
public class DurationImpl implements Duration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
