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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

			Pageable paging = PageRequest.of(0,1);
			List<Station> lst1 = stationRepository.findByCode("ST1",paging);
			if (lst1.size() == 0) {
				Station st1 = new Station();
				st1.setName("Stacioni 1");
				st1.setDetails("Qender");
				st1.setCode("ST1");
				stationRepository.save(st1);
			}

			List<Station> lst2 = stationRepository.findByCode("ST2",paging);
			if (lst2.size() == 0) {
				Station st2 = new Station();
				st2.setName("Stacioni 2");
				st2.setDetails("Rruga Durresit");
				st2.setCode("ST2");
				stationRepository.save(st2);
			}

			List<Station> lst3 = stationRepository.findByCode("ST3",paging);
			if (lst3.size() == 0) {
				Station st3 = new Station();
				st3.setName("Stacioni 3");
				st3.setDetails("Zogu zi");
				st3.setCode("ST3");
				stationRepository.save(st3);
			}

			List<Station> lst4 = stationRepository.findByCode("ST4",paging);
			if (lst4.size() == 0) {
				Station st4 = new Station();
				st4 = new Station();
				st4.setName("Stacioni 4");
				st4.setDetails("21 Dhjetori");
				st4.setCode("ST4");
				stationRepository.save(st4);
			}

			List<Station> lst5 = stationRepository.findByCode("ST5",paging);
			if (lst5.size() == 0) {
				Station st5 = new Station();
				st5.setName("Stacioni 5");
				st5.setDetails("Rruga e kavajes");
				st5.setCode("ST5");
				stationRepository.save(st5);
			}

			List<Station> lst6 = stationRepository.findByCode("ST6",paging);
			if (lst6.size() == 0) {
				Station st6 = new Station();
				st6.setName("Stacioni 6");
				st6.setDetails("Shqiponja");
				st6.setCode("ST6");
				stationRepository.save(st6);
			}

			List<Station> lst7 = stationRepository.findByCode("ST7",paging);
			if (lst7.size() == 0) {
				Station st7 = new Station();
				st7.setName("Stacioni 7");
				st7.setDetails("Ushtari i panjohur");
				st7.setCode("ST7");
				stationRepository.save(st7);
			}

			List<Station> lst8 = stationRepository.findByCode("ST8",paging);
			if (lst8.size() == 0) {
				Station st8 = new Station();
				st8.setName("Stacioni 8");
				st8.setDetails("7 Xhuxhat");
				st8.setCode("ST8");
				stationRepository.save(st8);
			}

			List<Station> lst9 = stationRepository.findByCode("ST9",paging);
			if (lst9.size() == 0) {
				Station st9 = new Station();
				st9.setName("Stacioni 9");
				st9.setDetails("Ish stacioni i trenit");
				st9.setCode("ST9");
				stationRepository.save(st9);
			}

			List<Station> lst10 = stationRepository.findByCode("ST10",paging);
			if (lst10.size() == 0) {
				Station st10 = new Station();
				st10.setName("Stacioni 10");
				st10.setDetails("Vasil shanto");
				st10.setCode("ST10");
				stationRepository.save(st10);
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
