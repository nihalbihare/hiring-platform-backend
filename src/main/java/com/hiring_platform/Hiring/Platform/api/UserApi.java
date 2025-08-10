package com.hiring_platform.Hiring.Platform.api;


import com.hiring_platform.Hiring.Platform.dto.LoginDTO;
import com.hiring_platform.Hiring.Platform.dto.ResponseDTO;
import com.hiring_platform.Hiring.Platform.dto.UserDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class  UserApi {
    @Autowired
    private UserService userService ;
    @PostMapping("/register")
public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws JobPortalException {
   userDTO = userService.registerUser(userDTO);
   return new ResponseEntity<>(userDTO , HttpStatus.CREATED);
}
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.loginUser(loginDTO) , HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDTO>
    changePass(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(loginDTO) , HttpStatus.OK);
    }
    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO>
    sendOtp(@PathVariable @Email(message = "{user.email.invalid}") String email) throws  Exception {
       userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("OTP SEND SUCCESSFULLY "), HttpStatus.OK);
    }
    @GetMapping ("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOtp
            (@PathVariable @Email(message = "{user.email.invalid}") String email,
             @PathVariable @Pattern(regexp = "^[0-9]{6}" ,message = "{otp.invalid}") String otp) throws JobPortalException{

         userService.verifyOtp(email ,otp);
        return new ResponseEntity<>(new ResponseDTO("OTP VERIFIED SUCCESSFULLY "), HttpStatus.OK);
    }
}
