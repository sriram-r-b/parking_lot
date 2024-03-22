package parking_lot.models;

import java.util.Date;
import java.util.List;

public class Invoice extends BaseModel{
    private double amount;
    private Ticket ticket;
    private Date exitTime;

    private List<InvoiceDetail> details;

    public double getAmount() {
        return amount;
    }



    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
        this.amount=0;
        for (InvoiceDetail detail : details) {
            this.amount+=detail.getPrice();
        }
    }
}
