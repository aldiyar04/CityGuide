package kz.edu.iitu.CityGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityGuideApplication {

	public static final String API_URI_PATH = "/api/v1";

	public static void main(String[] args) {
		SpringApplication.run(CityGuideApplication.class, args);
	}

}
