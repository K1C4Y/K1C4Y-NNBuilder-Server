package com.pz.NNServer.service;

import com.pz.NNServer.dto.RegisterRequest;
import com.pz.NNServer.model.NotificationEmail;
import com.pz.NNServer.model.User;
import com.pz.NNServer.model.VerificationToken;
import com.pz.NNServer.repository.UserRepo;
import com.pz.NNServer.repository.VerificationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);

        userRepo.save(user);

        String token = generatedVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your account", user.getEmail(),"Thank you for signing up!!!" +
                "Please click on the link below to activate your account:" +
                "http://localhost:8080/api/auth/accountVerification" + token));
    }

    private String generatedVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);

        return token;
    }
}
