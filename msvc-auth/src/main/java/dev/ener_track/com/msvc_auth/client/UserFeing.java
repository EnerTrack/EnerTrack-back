package dev.ener_track.com.msvc_auth.client;

import dev.ener_track.com.msvc_auth.api.dto.request.VerifyEmail;
import dev.ener_track.com.msvc_auth.api.dto.response.VerifyLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-users", url = "http://enertrack-msvc-users:8001/users")
public interface UserFeing {

    @PostMapping(path = "/validate-users")
    public ResponseEntity<VerifyLogin> validateLogin(@RequestBody VerifyEmail request);

}
