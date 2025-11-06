package dev.ener_track.com.demo.infracture.client;

import dev.ener_track.com.demo.api.dto.response.basicResponse.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://enertrack-msvc-users:8001/person")
public interface UserFeing {

    @GetMapping("/{id}")
    public PersonResponse getUserById(@PathVariable("id") String id);
}
