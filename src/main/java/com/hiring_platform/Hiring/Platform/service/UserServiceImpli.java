package com.hiring_platform.Hiring.Platform.service;
import com.hiring_platform.Hiring.Platform.dto.LoginDTO;
import com.hiring_platform.Hiring.Platform.dto.NotificationDTO;
import com.hiring_platform.Hiring.Platform.dto.ResponseDTO;
import com.hiring_platform.Hiring.Platform.dto.UserDTO;
import com.hiring_platform.Hiring.Platform.entity.Otp;
import com.hiring_platform.Hiring.Platform.entity.User;
import com.hiring_platform.Hiring.Platform.entity.Utilities;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.repository.OtpRepository;
import com.hiring_platform.Hiring.Platform.repository.UserRepository;
import com.hiring_platform.Hiring.Platform.utility.Data;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService ")
@Validated
public class UserServiceImpli implements UserService {
    @Autowired
 private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private OtpRepository otpRepository;

    @Autowired
     private PasswordEncoder passwordEncoder;
    @Autowired
    private NotificationService notificationService;
    @Override
    public UserDTO registerUser(@Valid UserDTO userDTO) throws JobPortalException{
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
        if(optional.isPresent())throw new JobPortalException("USER_FOUND");
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail(), userDTO.getName()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity(); // first creating the user obj and after that convrting
        // userdto to entity after save to repo then we can also save becaues save method returns user
        // so we can store it to user and after coverting it into dto and return user
        user = userRepository.save(user);
        return user.toDTO();

    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
    if(!passwordEncoder.matches(loginDTO.getPassword() , user.getPassword())) throw
           new  JobPortalException("INVALID_CREDENTIALS");
    return user.toDTO();
    }




    @Override
    public Boolean sendOtp(String email) throws  Exception {
        User user =  userRepository.findByEmail(email).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mm,true);
        messageHelper.setTo(email);
        messageHelper.setSubject("Your otp code");
        String genOtp = Utilities.OtpGeneration();
        Otp otp = new Otp(email ,genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        messageHelper.setText(Data.getMessageBody(genOtp, user.getName()), true);
        mailSender.send(mm);
        return true;
    }


    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
       Otp otpEntity = otpRepository.findById(email).orElseThrow(() ->
                new JobPortalException("OTP_NOT_FOUND"));
       if(!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationDTO notifcationDTO = new NotificationDTO();
        notifcationDTO.setUserId(user.getId());
        notifcationDTO.setMessage("Password Reset Successfully");
        notifcationDTO.setAction("Password Reset");
        notificationService.sendNotifcation(notifcationDTO);
        return new ResponseDTO("Password change successfully");
    }

    @Override
    public UserDTO getUserByEmail(String email) throws JobPortalException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new JobPortalException("USER_NOT_FOUND")).toDTO();
    }

    @Scheduled(fixedRate = 60000)
    public void removeOtp(){
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<Otp>expiredOtps = otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOtps.isEmpty()){
            otpRepository.deleteAll(expiredOtps);
            System.out.println("Remove "+expiredOtps.size() +" Expired Otps ");
        }

    }


}
