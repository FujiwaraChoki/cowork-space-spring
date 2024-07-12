package ch.zli.m223.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.zli.m223.exception.RoomNotFoundException;
import org.springframework.stereotype.Repository;
import ch.zli.m223.model.impl.RoomImpl;

@Repository
public interface RoomRepository extends JpaRepository<RoomImpl, Long> {
    public default boolean isRoomInUse(Long id) {
        RoomImpl room = findById(id).orElseThrow(() -> new RoomNotFoundException("There is no Room with the specified ID."));

        return room.getInUse();
    }

    public default void setToInUse(Long id) {
        RoomImpl room = findById(id).orElseThrow(() -> new RoomNotFoundException("There is no Room with the specified ID."));
        room.setToInUse();

        save(room);
    }
}
