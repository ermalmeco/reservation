package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.repositories.AgencyRepository;
import bus.reservation.system.agency.repositories.BusRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository repository;

    @Autowired
    private BusRepository busRepository;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public Agency addAgency(Agency agency) {
        logger.debug("Service call /addAgency");
        Agency result = repository.save(agency);
        logger.debug("Service result /addAgency: "+result.toString());
        return result;
    }

    public String addBussToAgency(int busId, int agencyId) {
        logger.debug("Service call /addBusToAgency");
        Bus existingBus = busRepository.findById(busId).orElse(null);
        if (existingBus != null) {
            existingBus.setAgency(agencyId);
            Bus result = busRepository.save(existingBus);
            logger.debug("Service result /addBusToAgency: "+result.toString());
            return "Bus is assigned to agency!";
        }else{
            logger.debug("Service result /addBusToAgency: Bus not found");
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
