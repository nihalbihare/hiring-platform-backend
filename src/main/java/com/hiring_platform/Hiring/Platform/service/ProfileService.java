package com.hiring_platform.Hiring.Platform.service;

import com.hiring_platform.Hiring.Platform.dto.ProfileDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;

import java.util.List;

public interface ProfileService {
    Long createProfile(String email, String name) throws JobPortalException;
    ProfileDTO getProfile(Long id) throws JobPortalException;
    ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;
    List<ProfileDTO> getAllProfiles();
}

