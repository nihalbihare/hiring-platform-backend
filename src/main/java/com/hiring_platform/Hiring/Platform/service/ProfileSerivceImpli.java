package com.hiring_platform.Hiring.Platform.service;

import com.hiring_platform.Hiring.Platform.dto.ProfileDTO;
import com.hiring_platform.Hiring.Platform.entity.Profile;
import com.hiring_platform.Hiring.Platform.entity.Utilities;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("profileService")

public class ProfileSerivceImpli implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email, String name) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setName(name); // ✅ Now we store the name in MongoDB

        profile.setExperience(new ArrayList<>());
        profile.setCertification(new ArrayList<>());

        profileRepository.save(profile);
        return profile.getId();
    }


    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()->
                new JobPortalException("Profile_not_found")).toDto();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        profileRepository.findById(profileDTO.getId()).orElseThrow(()->
                new JobPortalException("Profile_not_found"));
                profileRepository.save(profileDTO.toEntity()); // if found update or else save
        return profileDTO;

    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream().map((x)->x.toDto()).toList();
    }

}
