package parking_lot.services;

import parking_lot.models.*;

import parking_lot.repository.ParkingLotRepository;
import parking_lot.repository.TicketRepository;
import parking_lot.repository.VehicleRepository;
import parking_lot.strategies.spot_assignment.AssignSpotStrategy;

import java.util.Date;

public class TicketServiceImpl implements TicketService {
    private GateService gateService;
    private VehicleRepository vehicleRepository;
    private AssignSpotStrategy assignSpotStrategy;
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;

    public TicketServiceImpl(GateService gateService, VehicleRepository vehicleRepository, AssignSpotStrategy assignSpotStrategy, TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository) {
        this.gateService = gateService;
        this.vehicleRepository = vehicleRepository;
        this.assignSpotStrategy = assignSpotStrategy;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public TicketServiceImpl(GateService gateService){
        this.gateService=gateService;
    }


    @Override
    public Ticket generateTicket (int gateId, String vehicleNumber,String vehicleTypeStr) throws Exception{
/*        1.get gate from gate gateId
          2. do create if not exist on vehicle object
          3. using strategy pattern figure an empty spot
          4. Create a ticket and return
 */
        Gate gate = gateService.getGateById(gateId);
        VehicleType vehicleType=VehicleType.getTypeFromString(vehicleTypeStr);
        Vehicle vehicle=vehicleRepository.createIfNotExists(vehicleNumber,vehicleType);
        ParkingLot parkingLot=parkingLotRepository.getParkingLotByGateId(gateId);
        if (parkingLot==null){
            throw new Exception("Invalid gate Id");
        }
        Spot spot=assignSpotStrategy.assignSpot(vehicleType,parkingLot);
        Ticket ticket=new Ticket();
        ticket.setAssignedSpot(spot);
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket=ticketRepository.insertTicket(ticket);

        return ticket;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }


}
