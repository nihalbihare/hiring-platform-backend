package com.hiring_platform.Hiring.Platform.service;

import com.hiring_platform.Hiring.Platform.dto.LoginDTO;
import com.hiring_platform.Hiring.Platform.dto.ResponseDTO;
import com.hiring_platform.Hiring.Platform.dto.UserDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserService {
    public UserDTO registerUser (@Valid UserDTO userDTO) throws JobPortalException;


   public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

  public Boolean sendOtp(String email) throws  Exception;

    public Boolean verifyOtp(String email , String otp) throws JobPortalException;

   public ResponseDTO changePassword(@Valid LoginDTO loginDTO) throws JobPortalException;
   public  UserDTO getUserByEmail(String email) throws JobPortalException;

}
