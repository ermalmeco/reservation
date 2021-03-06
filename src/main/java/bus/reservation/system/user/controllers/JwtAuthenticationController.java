package bus.reservation.system.user.controllers;

import java.util.Objects;

import bus.reservation.system.BusReservationSystemApplication;
import bus.reservation.system.config.JwtTokenUtil;
import bus.reservation.system.dto.model.user.UserDto;
import bus.reservation.system.dto.response.Response;
import bus.reservation.system.forms.UserSignUpForm;
import bus.reservation.system.user.entities.JwtRequest;
import bus.reservation.system.user.entities.JwtResponse;
import bus.reservation.system.user.services.JwtUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/")
public class JwtAuthenticationController {

    private static final Logger logger = LogManager.getLogger(BusReservationSystemApplication.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        logger.debug("Controller call /authenticate");
        logger.debug(authenticationRequest.toString());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        ResponseEntity<?> result = ResponseEntity.ok(new JwtResponse(token));
        logger.info("Controller result /register " + result.toString());
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response saveUser(@RequestBody @Valid UserSignUpForm user) throws Exception {
        logger.debug("Controller call /register");
        UserDto result = userDetailsService.save(user);
        logger.info("Controller result /register " + result.toString());
        return Response.ok().setPayload(result);
	}

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}