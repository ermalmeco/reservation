package bus.reservation.system.user.services;

import java.util.ArrayList;

import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.user.repositories.UserRepository;
import bus.reservation.system.utils.Constants;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        bus.reservation.system.user.entities.User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getEmail(), user.getEncryptedPassword(),
                new ArrayList<>());
    }

    public bus.reservation.system.user.entities.User save(bus.reservation.system.user.entities.User user) throws Exception {
        bus.reservation.system.user.entities.User newUser = new bus.reservation.system.user.entities.User();
        bus.reservation.system.user.entities.User exUser = userRepository.findByEmail(user.getEmail());
        if (exUser == null) {
            if (user.getPassword() != null) {
                user.setEncryptedPassword(bcryptEncoder.encode(user.getPassword()));
            }

            Role userRole;
            if (user.getRole() == 1) {
                userRole = roleRepository.findByName(Constants.ADMIN_NAME);
            } else {
                userRole = roleRepository.findByName(Constants.USER_NAME);
            }
            user.setRole(userRole.getId());
            return userRepository.save(user);
        }else{
            throw new Exception("User exists");
        }
    }

}