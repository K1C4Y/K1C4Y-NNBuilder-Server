package com.pz.NNServer.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pz.NNServer.dto.AuthenticationResponse;
import com.pz.NNServer.dto.LoginRequest;
import com.pz.NNServer.dto.RegisterRequest;
import com.pz.NNServer.exceptions.SpringNNBuilderMailException;
import com.pz.NNServer.model.NotificationEmail;
import com.pz.NNServer.model.User;
import com.pz.NNServer.model.VerificationToken;
import com.pz.NNServer.repository.UserRepo;
import com.pz.NNServer.repository.VerificationTokenRepo;
import com.pz.NNServer.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


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
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generatedVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);

        return token;
    }

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
		verificationToken.orElseThrow(() -> new SpringNNBuilderMailException("Invalid token"));
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getName();
		User user = userRepo.findByName(username)
		.orElseThrow(() -> new SpringNNBuilderMailException("User name " + username + " not found"));
		user.setEnabled(true);
		userRepo.save(user);

		
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getName(),
				loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(token, loginRequest.getName());
	}
}
