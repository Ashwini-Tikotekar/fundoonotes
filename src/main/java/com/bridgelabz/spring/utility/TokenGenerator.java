package com.bridgelabz.spring.utility;

public interface TokenGenerator {
	String generateToken(String id);
	String VerifyToken(String id);
}
