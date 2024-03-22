package parking_lot.controllers;

import parking_lot.dtos.GenerateTicketRequestDto;
import parking_lot.dtos.GenerateTicketResponseDto;
import parking_lot.dtos.Response;
import parking_lot.dtos.ResponseStatus;
import parking_lot.exceptions.InvalidRequestException;
import parking_lot.models.Ticket;
import parking_lot.services.TicketService;

import javax.management.relation.InvalidRelationIdException;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto responseDto =new GenerateTicketResponseDto();
        Response response=new Response();
        try{
        if(requestDto.getGateId()<0){
            throw new InvalidRequestException("invalid gate id.");
        }
        if(requestDto.getVehicleType()==null && requestDto.getVehicleType().equals("")){
            throw new InvalidRequestException("vehicle type is mandatory");

        }
        }
        catch (InvalidRequestException e){
            response.setErrorMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILED);
            responseDto.setResponse(response);
            return responseDto;
        }

        try {
            Ticket ticket=ticketService.generateTicket(requestDto.getGateId(),requestDto.getVehicleNumber(),requestDto.getVehicleType());
            responseDto.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMessage(e.getMessage());

        }
        responseDto.setResponse(response);
        return responseDto;

    }
}
