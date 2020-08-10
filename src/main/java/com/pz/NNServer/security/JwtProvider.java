package com.pz.NNServer.security;

import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
//	private KeyStore keyStore;
	private Key key;
	
	@PostConstruct
	public void init() {
//		try {
//			keyStore = KeyStore.getInstance("JKS");
//			InputStream resourceAsStream = getClass().getResourceAsStream("/NNBuilder.jks");
//			keyStore.load(resourceAsStream, "secret".toCharArray());
//		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
//			throw new NNBuilderException("Exception occurred while loading keystore");
//		}
		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	public String generateToken(Authentication authentication) {
		org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(key)
				.compact();
	}
	
//	private PrivateKey getPrivateKey() {
//		try {
//			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
//		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
//			throw new NNBuilderException("Exception occured while retrieving public key from keystore");
//		}
//	}	
}
