package parking_lot.services;

import parking_lot.models.Gate;
import parking_lot.repository.GateRepository;

public class GateService {
    private GateRepository gateRepository;

    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Gate getGateById(int gateId){
        return gateRepository.getGateById(gateId);

    }
}
