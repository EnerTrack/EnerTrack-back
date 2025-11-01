package dev.ener_track.com.msvc_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcDataApplication.class, args);
	}

}
