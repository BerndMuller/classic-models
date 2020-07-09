package de.jsfpraxis.classicmodels.security;

import javax.security.enterprise.identitystore.PasswordHash;

/**
 * Einfache Implementierung des Interface PasswordHash.
 * 
 * <p> Hash-Funktion ist Identität.
 * 
 * @author Bernd Müller
 *
 */
public class SimpleHash implements PasswordHash {

	@Override
	public String generate(char[] password) {
		// not used
		return null;
	}

	@Override
	public boolean verify(char[] password, String hashedPassword) {
		return new String(password).equals(hashedPassword);
	}

}
