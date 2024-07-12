package ch.zli.m223.model.impl;

import ch.zli.m223.model.Room;
import jakarta.persistence.*;

@Entity
public class RoomImpl implements Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private boolean inUse;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getInUse() {
        return inUse;
    }
}
