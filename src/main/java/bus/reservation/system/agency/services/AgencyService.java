package bus.reservation.system.agency.services;

import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.repositories.AgencyRepository;
import bus.reservation.system.agency.repositories.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository repository;

    @Autowired
    private BusRepository busRepository;

    public Agency addAgency(Agency agency) {
        return repository.save(agency);
    }

    public String addBussToAgency(int busId, int agencyId) {
        Bus existingBus = busRepository.findById(busId).orElse(null);
        if (existingBus != null) {
            existingBus.setAgency(agencyId);
            busRepository.save(existingBus);
            return "Bus is assigned to agency!";
        }else{
            return  "Smth went wrong, please check!";
        }
    }

    public String deleteAgency(int agencyId) {
        repository.deleteById(agencyId);
        return "Agency deleted!";
    }

    public Agency updateAgency(Agency agency) {
        Agency exAgency = repository.findById(agency.getId()).orElse(null);
        if (exAgency != null) {
            exAgency.setDetails(agency.getDetails());
            exAgency.setCode(agency.getCode());
            exAgency.setName(agency.getName());
            exAgency.setOwner(agency.getOwner());
            return agency;
        }
        return agency;
    }
}
