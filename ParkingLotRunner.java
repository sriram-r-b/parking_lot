package parking_lot;

import parking_lot.controllers.InvoiceController;
import parking_lot.controllers.TicketController;
import parking_lot.dtos.GenerateInvoiceRequestDto;
import parking_lot.dtos.GenerateInvoiceResponseDto;
import parking_lot.dtos.GenerateTicketRequestDto;
import parking_lot.dtos.GenerateTicketResponseDto;
import parking_lot.factories.CalculateFeesStrategyFactory;
import parking_lot.models.*;
import parking_lot.repository.*;
import parking_lot.services.*;
import parking_lot.strategies.spot_assignment.AssignSpotStrategy;
import parking_lot.strategies.spot_assignment.NearestFirstSpotAssignmentStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotRunner {
    public static ParkingLot getParkingLot(){
        List<Spot> spotList =new ArrayList<>(){{
            add(new Spot("Spot1",SpotStatus.UNOCCUPIED,VehicleType.CAR));
            add(new Spot("Spot2",SpotStatus.UNOCCUPIED,VehicleType.EV_CAR));
            add(new Spot("Spot3",SpotStatus.UNOCCUPIED,VehicleType.BIKE));
            add(new Spot("Spot4",SpotStatus.UNOCCUPIED,VehicleType.TRUCK));
        }};
        Section section1=new Section();
        section1.setName("section1");
        section1.setSpots(spotList);
        Floor floor=new Floor();
        floor.setFloorNum(1);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(new ArrayList<>(){{add(section1);}});
        ParkingLot lot=new ParkingLot();
        lot.setFloors(new ArrayList<>(){{
            add(floor);
        }});
        


        return lot;
    }
    public static Map<Integer,Gate> getGateMap(){
        Gate gate1=new Gate();
        gate1.setGateType(GateType.ENTRY);
        gate1.setName("Entry Gate");
        gate1.setOperator(new Operator());
        gate1.setId(1);
        Gate gate2=new Gate();
        gate2.setGateType(GateType.EXIT);
        gate2.setName("Exit Gate");
        gate2.setOperator(new Operator());
        gate2.setId(2);


        Map<Integer,Gate> gateMap =new HashMap<>(){{
            put(1,gate1);
            put(2,gate2);
        }};
        return gateMap;
    }

    public static void main(String[] args) {
        Map<Integer,Gate> gateMap=getGateMap();
        ParkingLot lot=getParkingLot();
        List<Gate>gates=new ArrayList<>();
        for (Map.Entry<Integer, Gate> integerGateEntry : gateMap.entrySet()) {
            Gate currgate=integerGateEntry.getValue();
            gates.add(currgate);
        }
        lot.setGates(gates);
        Map<Integer,ParkingLot> parkingLotMap=new HashMap<>(){{
            put(0,lot);
        }};



        GateRepository gateRepository=new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository=new ParkingLotRepository(parkingLotMap);
        TicketRepository ticketRepository=new TicketRepository();
        VehicleRepository vehicleRepository=new VehicleRepository();
        GateService gateService=new GateService(gateRepository);
        AssignSpotStrategy assignSpotStrategy=new NearestFirstSpotAssignmentStrategy();
        TicketService ticketService=new TicketServiceImpl(gateService,vehicleRepository,assignSpotStrategy,ticketRepository,parkingLotRepository);
        TicketController ticketController=new TicketController(ticketService);
        Slab slab1=new Slab(VehicleType.CAR,0,2,10);
        Slab slab2=new Slab(VehicleType.CAR,2,4,15);
        Slab slab3=new Slab(VehicleType.CAR,4,8,20);
        Slab slab4=new Slab(VehicleType.CAR,8,-1,40);
        Map <Integer,Slab> slabMap =new HashMap<>(){{
            put(slab1.getId(),slab1);
            put(slab2.getId(),slab2);
            put(slab3.getId(),slab3);
            put(slab4.getId(),slab4);
        }};
        SlabRepository slabRepository = new SlabRepository(slabMap);
        InvoiceRepository invoiceRepository=new InvoiceRepository(new HashMap<>());
        CalculateFeesStrategyFactory calculateFeesStrategyFactory = new CalculateFeesStrategyFactory(slabRepository);
        InvoiceService invoiceService =new InvoiceServiceImpl(ticketService,gateService,calculateFeesStrategyFactory,invoiceRepository);
        InvoiceController invoiceController=new InvoiceController(invoiceService);



        GenerateTicketRequestDto generateTicketRequestDto =new GenerateTicketRequestDto();
        generateTicketRequestDto.setGateId(1);
        generateTicketRequestDto.setVehicleType(VehicleType.CAR.toString());
        generateTicketRequestDto.setVehicleNumber("TN 40 L 2198");
        GenerateTicketResponseDto generateTicketResponseDto=ticketController.generateTicket(generateTicketRequestDto);
        System.out.println("response: "+generateTicketResponseDto);
        int ticketID=generateTicketResponseDto.getTicket().getId();
        generateTicketRequestDto.setVehicleNumber("TN 40 L 2199");
        generateTicketResponseDto=ticketController.generateTicket(generateTicketRequestDto);
        System.out.println("response: "+generateTicketResponseDto);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("sleep exception"+e);
        }


        GenerateInvoiceRequestDto invoiceRequestDto = new GenerateInvoiceRequestDto();
        invoiceRequestDto.setTicketId(ticketID);
        invoiceRequestDto.setGateId(2);
        GenerateInvoiceResponseDto generateInvoiceResponseDto = invoiceController.generateInvoice(invoiceRequestDto);
        System.out.println("response: "+generateInvoiceResponseDto);




    }
}
