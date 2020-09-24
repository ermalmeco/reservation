package bus.reservation.system.agency.services;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.agency.enitities.Agency;
import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.repositories.AgencyRepository;
import bus.reservation.system.agency.repositories.BusRepository;
import bus.reservation.system.dto.mapper.AgencyMapper;
import bus.reservation.system.dto.mapper.BusMapper;
import bus.reservation.system.dto.model.agency.AgencyDto;
import bus.reservation.system.dto.model.agency.BusDto;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.exception.BRSException;
import bus.reservation.system.user.entities.User;
import bus.reservation.system.user.entities.UserRoles;
import bus.reservation.system.user.repositories.UserRepository;
import bus.reservation.system.user.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public AgencyDto addAgency(Agency agency) {
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
            User user = userRepository.findById(agency.getOwner()).orElse(null);
            if (user == null) {
                agency.setOwner(0);
            } else {
                agency.setUser(userRepository.findById(agency.getOwner()).orElse(null));
            }
            AgencyDto result = AgencyMapper.toAgencyDto(repository.save(agency));
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
            AgencyDto result = AgencyMapper.toAgencyDto(agency);// modelMapper.map(agency, AgencyDto.class);
            logger.info("Service result /getAgencyDetailsByCode success: "+ result);
            return result;
        }
        logger.debug("Service call /getAgencyDetailsByCode failed. Agency not found");
        throw BRSException.throwException(AGENCY, ENTITY_NOT_FOUND, code);
    }
}
