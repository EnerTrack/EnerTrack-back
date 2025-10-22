package dev.ener_track.com.demo.infracture.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8001/person/")
public interface UserFeing {

    @GetMapping("/{id}")
    public String getUserById(String id);
}
