package ch.zli.m223.service.ticketing;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Room;

public interface TicketingAdminService {
    public void suspendBooking(Long id);
    public Room createRoom(String name, boolean inUse);
}
