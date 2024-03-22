package parking_lot.services;

import parking_lot.exceptions.InvalidGateException;
import parking_lot.exceptions.InvalidTicketException;
import parking_lot.factories.CalculateFeesStrategyFactory;
import parking_lot.models.*;
import parking_lot.repository.GateRepository;
import parking_lot.repository.InvoiceRepository;
import parking_lot.repository.SlabRepository;
import parking_lot.strategies.pricing.CalculateFeesStrategy;
import parking_lot.strategies.pricing.WeekdayStrategy;
import parking_lot.strategies.pricing.WeekendStrategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketService ticketService;
    private GateService gateService;
    private CalculateFeesStrategyFactory factory;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculateFeesStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.factory = factory;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice getTicket(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException {
        Ticket ticket=this.ticketService.getTicketById(ticketId);
        if (ticket==null){
            throw new InvalidTicketException("Invalid Ticket");
        }
        Gate gate = this.gateService.getGateById(gateId);
        if (gate==null){
            throw new InvalidGateException("Invalid Gate Id");
        }
        if (gate.getGateType().equals(GateType.ENTRY)){
            throw new InvalidGateException("Invalid Gate type");
        }
        Date entryTime=ticket.getEntryTime();
        Date exitTime=new Date();
        CalculateFeesStrategy calculateFeesStrategy=factory.getCalculateFeesStrategy(exitTime);
        double amount=calculateFeesStrategy.calculate(entryTime,exitTime,ticket.getVehicle().getVehicleType());
        InvoiceDetail invoiceDetail=new InvoiceDetail();
        invoiceDetail.setName("Parking Fees");
        invoiceDetail.setPrice(amount);
        Invoice invoice=new Invoice();
        invoice.setDetails(new ArrayList<>(){{add(invoiceDetail);}});
        invoice.setTicket(ticket);
        invoice.setExitTime(exitTime);
        return invoiceRepository.insertInvoice(invoice);
    }
}
