package com.hiring_platform.Hiring.Platform.api;


import com.hiring_platform.Hiring.Platform.dto.ProfileDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/profiles")
@Validated
public class ProfileApi {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<ProfileDTO>(profileService.getProfile(id), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileDTO>> getAllProfile() throws JobPortalException {
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) throws JobPortalException {
        return new ResponseEntity<ProfileDTO>(profileService.updateProfile(profileDTO), HttpStatus.OK);
    }
}
