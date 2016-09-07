package io.mattslater;

import io.mattslater.messaging.DeliveryServiceChannels;
import io.mattslater.model.Delivery;
import io.mattslater.repos.DeliveryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableBinding(Sink.class)
@EnableEurekaClient
@SpringBootApplication
public class DeliveryServiceApplication {

	@Bean
	CommandLineRunner runner(DeliveryRepository dr) {
		return args -> {
			dr.deleteAll();
			dr.save(new Delivery("555 Fake St", "310 E 82nd St"));
			dr.save(new Delivery("410 E 38th St", "14 Butt Avenue"));
			dr.save(new Delivery("1000 West End Ave", "Rivergate Vet Clinic"));
			dr.save(new Delivery("666 Bocephus Way", "123 Rue de Faux"));

			dr.findAll().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(DeliveryServiceApplication.class, args);
	}
}
