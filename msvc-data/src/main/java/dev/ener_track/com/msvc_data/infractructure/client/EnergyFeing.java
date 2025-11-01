package dev.ener_track.com.msvc_data.infractructure.client;

import dev.ener_track.com.msvc_data.api.dto.response.EnergyTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-energy", url = "http://localhost:8003/")
public interface EnergyFeing {

    @GetMapping("/energy-type/{name}")
    public EnergyTypeResponse getByName(@PathVariable("name") String name);
}
