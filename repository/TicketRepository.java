package parking_lot.repository;

import parking_lot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Integer, Ticket> map;
    private static int Id=0;

    public TicketRepository(){
        this.map=new HashMap<>();
    }

    public TicketRepository(Map<Integer, Ticket> map) {
        this.map = map;
    }
    public Ticket insertTicket(Ticket ticket){
        ticket.setId(Id);
        map.put(Id++,ticket);
        return ticket;

    }

    public Ticket getTicketById(int ticketId) {
     return map.get(ticketId);
    }
}
