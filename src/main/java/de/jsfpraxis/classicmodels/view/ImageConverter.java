package de.jsfpraxis.classicmodels.view;

import java.util.Base64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Converter für JPGs nach Base64. Kein (!) JSF-Converter. 
 * 
 * @author Bernd Müller
 *
 */
@Named
@RequestScoped
public class ImageConverter {
	
	public String asBase64(byte[] image) {
		if (image == null) {
			return null;
		} else {
			byte[] imageEncoded = Base64.getEncoder().encode(image);
			String prefix = "data:image/jpg;base64,"; // geht auch für PNG
			return prefix + new String(imageEncoded);
			
		}
	}
}
