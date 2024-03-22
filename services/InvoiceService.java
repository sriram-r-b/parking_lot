package parking_lot.services;

import parking_lot.exceptions.InvalidGateException;
import parking_lot.exceptions.InvalidTicketException;
import parking_lot.models.Invoice;
import parking_lot.models.Ticket;

public interface InvoiceService {
    public Invoice getTicket (int ticketId,int gateId) throws InvalidTicketException, InvalidGateException;
}
