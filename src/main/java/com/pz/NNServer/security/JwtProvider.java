package com.pz.NNServer.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pz.NNServer.exceptions.NNBuilderException;
import com.pz.NNServer.model.User;

import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;
	
	public void init() {
		try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/NNBuilder.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException |IOException e) {
			throw new NNBuilderException("Exception occurred while loading keystore", e);
		}
	}
	
	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getName())
				.signWith(getPrivateKey())
				.compact();
	}

	private Key getPrivateKey() {
		try {
			return keyStore.getKey("NNBuilder", "supersecretkey".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new NNBuilderException("Exception occoured while geting key from keystore");
		}
	}
}
