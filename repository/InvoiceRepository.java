package parking_lot.repository;

import parking_lot.models.Invoice;

import java.util.Map;

public class InvoiceRepository {
    public InvoiceRepository(Map<Integer, Invoice> map) {
        this.map = map;
    }

    private Map<Integer, Invoice> map;

    public Map<Integer, Invoice> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Invoice> map) {
        this.map = map;
    }
    public static int ID=0;
    public Invoice  insertInvoice(Invoice invoice){
        invoice.setId(ID);
        map.put(ID++,invoice);
        return invoice;
    }
}
