package bus.reservation.system;

import bus.reservation.system.agency.enitities.Bus;
import bus.reservation.system.agency.enitities.Station;
import bus.reservation.system.agency.repositories.BusRepository;
import bus.reservation.system.agency.repositories.StationRepository;
import bus.reservation.system.user.entities.Role;
import bus.reservation.system.user.repositories.RoleRepository;
import bus.reservation.system.utils.Constants;
import bus.reservation.system.utils.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusReservationSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, StationRepository stationRepository, BusRepository busRepository) {
		return args -> {
			Role admin = roleRepository.findByName(Constants.ADMIN_NAME);
			if (admin == null) {
				admin = new Role();
				admin.setId((byte) 1);
				admin.setName(Constants.ADMIN_NAME);
				roleRepository.save(admin);
			}

			Role user = roleRepository.findByName(Constants.USER_NAME);
			if (user == null) {
				user = new Role();
				admin.setId((byte) 0);
				user.setName(Constants.USER_NAME);
				roleRepository.save(user);
			}

			Station st1 = stationRepository.findByCode("ST1");
			if (st1 == null) {
				st1 = new Station();
				st1.setName("Stacioni 1");
				st1.setDetails("Qender");
				st1.setCode("ST1");
				stationRepository.save(st1);
			}

			Station st2 = stationRepository.findByCode("ST2");
			if (st2 == null) {
				st2 = new Station();
				st2.setName("Stacioni 2");
				st2.setDetails("Rruga Durresit");
				st2.setCode("ST2");
				stationRepository.save(st2);
			}

			Station st3 = stationRepository.findByCode("ST3");
			if (st3 == null) {
				st3 = new Station();
				st3.setName("Stacioni 3");
				st3.setDetails("Zogu zi");
				st3.setCode("ST3");
				stationRepository.save(st3);
			}

			Bus bus1 = busRepository.findByCode("B1");
			if (bus1 == null) {
				bus1 = new Bus();
				bus1.setName("Bus 1");
				bus1.setDetails("Bus number 1");
				bus1.setCode("B1");
				bus1.setAgency((Integer)0);
				bus1.setCapacity(50);
				busRepository.save(bus1);
			}

			Bus bus2 = busRepository.findByCode("B2");
			if (bus2 == null) {
				bus2 = new Bus();
				bus2.setName("Bus 2");
				bus2.setDetails("Bus number 2");
				bus2.setCode("B2");
				bus1.setAgency((Integer)0);
				bus1.setCapacity(50);
				busRepository.save(bus2);
			}

		};
	}
}
