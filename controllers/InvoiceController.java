package parking_lot.controllers;

import parking_lot.dtos.GenerateInvoiceRequestDto;
import parking_lot.dtos.GenerateInvoiceResponseDto;
import parking_lot.dtos.Response;
import parking_lot.dtos.ResponseStatus;
import parking_lot.exceptions.InvalidRequestException;
import parking_lot.models.Invoice;
import parking_lot.models.Ticket;
import parking_lot.services.InvoiceService;

public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto invoiceRequestDto){
        GenerateInvoiceResponseDto responseDto=new GenerateInvoiceResponseDto();
        try {

            if(invoiceRequestDto.getTicketId()<0){
                throw new InvalidRequestException("ticket id cant be negative");
            }
            if (invoiceRequestDto.getGateId()<0){
                throw new InvalidRequestException("gate id cant be negative");
            }


        }catch (InvalidRequestException e){
            Response response=new Response();
            response.setErrorMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILED);
            responseDto.setResponse(response);
            return responseDto;
        }
        try {
            Invoice invoice=invoiceService.getTicket(invoiceRequestDto.getTicketId(),invoiceRequestDto.getGateId());
            Response response=new Response();
            response.setStatus(ResponseStatus.SUCCESS);
            responseDto.setResponse(response);
            responseDto.setInvoice(invoice);
            return responseDto;
        }catch (Exception e){
            Response response=new Response();
            response.setErrorMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILED);
            responseDto.setResponse(response);
            return responseDto;
        }
    }
}
