package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.repositories.AgencyRepository;
import bus.reservation.system.agency.repositories.BusRepository;
import bus.reservation.system.dto.mapper.AgencyMapper;
import bus.reservation.system.dto.mapper.BusMapper;
import bus.reservation.system.dto.mapper.TripMapper;
import bus.reservation.system.dto.model.agency.AgencyDto;
import bus.reservation.system.dto.model.agency.BusDto;
import bus.reservation.system.dto.model.agency.TripDto;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.forms.CreateAgencyForm;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.UserRepository;
import bus.reservation.system.user.services.UserService;
import bus.reservation.system.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bus.reservation.system.exception.EntityType.*;
import static bus.reservation.system.exception.ExceptionType.*;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository repository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    public AgencyDto addAgency(CreateAgencyForm agency) {
        logger.debug("Service call /addAgency");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto exUser = userService.findUserByEmail(userDetails.getUsername());
        Boolean isAdmin = false;
        for (UserRoles role: exUser.getUserRoles()) {
            if (role.getRoleId() == 1) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            Agency newAgency = new Agency();
            String owner = userDetails.getUsername();
            User ownerDetails = userRepository.findByEmail(owner);
            Agency existingAgency = repository.findByOwner(ownerDetails.getId());
            if (existingAgency != null){
                logger.info("Service result /addAgency. User can have only one Agency. Your`s is with code: "+ existingAgency.getCode());
                throw BRSException.throwException(AGENCY, DUPLICATE_ENTITY,existingAgency.getCode());
            }
            newAgency.setOwner(ownerDetails.getId());
            newAgency.setUser(ownerDetails);
            newAgency.setName(agency.getName());
            newAgency.setDetails(agency.getDetails());

            String agencyCode = Utils.generateCode().toUpperCase();
            while (repository.findByCode(agencyCode) != null){
                agencyCode = Utils.generateCode().toUpperCase();
            }
            newAgency.setCode(agencyCode);

            AgencyDto result = AgencyMapper.toAgencyDto(repository.save(newAgency));
            logger.info("Service result /addAgency: " + result.toString());
            return result;
        }
        logger.info("Service result /addAgency. Something went wrong. You are not admin!");
        throw BRSException.throwException(USER,NOT_ADMIN,"");
    }

    public BusDto addBussToAgency(String busCode, String agencyCode) {
        logger.debug("Service call /addBussToAgency");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto exUser = userService.findUserByEmail(userDetails.getUsername());
        if (true) {//check isAdmin
            Bus existingBus = busRepository.findByCode(busCode);
            if (existingBus != null) {
                Agency agency = repository.findByCode(agencyCode);
                existingBus.setAgency(agency.getId());
                existingBus.setBusAgencyObj(agency);
                BusDto result = BusMapper.toBusDto(busRepository.save(existingBus));
                logger.info("Service result /addBussToAgency: " + result.toString());
                return result;
            } else {
                logger.info("Service result /addBusToAgency: Bus not found");
                throw BRSException.throwException(BUS, ENTITY_NOT_FOUND, busCode);
            }
        }
        logger.info("Service result /addAgency. Something went wrong. You are not admin!");
        throw BRSException.throwException(USER, NOT_ADMIN, "");
    }

    public AgencyDto getAgencyDetailsByCode(String code) {
        logger.debug("Service call /getAgencyDetailsByCode");
        Agency agency = repository.findByCode(code);
        if (agency != null) {
            AgencyDto result = AgencyMapper.toAgencyDto(agency);
            logger.info("Service result /getAgencyDetailsByCode success: "+ result);
            return result;
        }
        logger.debug("Service call /getAgencyDetailsByCode failed. Agency not found");
        throw BRSException.throwException(AGENCY, ENTITY_NOT_FOUND, code);
    }

    public List<AgencyDto> getAllAgencies(){
        logger.debug("Service call /getAllAgencies");
        List<AgencyDto> result = repository.findAll()
                .stream()
                .map(agency -> AgencyMapper.toAgencyDto(agency))
                .collect(Collectors.toList());
        logger.info("Result Service /getAllAgencies: "+result.toString());
        return result;
    }
}
