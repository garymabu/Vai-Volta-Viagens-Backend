package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataProfileClient;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Service.ProfileService;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public ResponseEntity<DataProfileClient> findProfile(@RequestHeader("Authorization") String authentication) {
        Client profile = profileService.findProfile(authentication.replace("Bearer ", ""));
        return ResponseEntity.ok().body(new DataProfileClient(profile));
    }
}