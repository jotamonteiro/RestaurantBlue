package br.com.joaomonteiro.restaurantBlue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestaurantBlueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantBlueApplication.class, args);
	}

}
