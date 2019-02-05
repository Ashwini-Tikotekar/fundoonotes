package com.bridgelabz.spring.utility;

public interface TokenGenerator {
	String generateToken(String id);
	int VerifyToken(String id);
}
