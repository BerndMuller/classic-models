package de.jsfpraxis.classicmodels.business.accounting.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;

@RequestScoped
public class OrderPdfCreator {
	
	/**
	 * Erzeugt für eine Bestellung ein PDF.
	 * 
	 * <p>
	 * Das PDF ist leider sehr spartanisch
	 * 
	 * 
	 * @param order Die bestellung
	 * @return PDF als Byte-Array
	 * @throws IOException bei Problemen
	 */
	public byte[] toPdf(Order order) throws IOException {
		
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);
			
			try (PDPageContentStream cont = new PDPageContentStream(document, page)) {
				cont.setFont(PDType1Font.TIMES_ROMAN, 14);
				cont.beginText();
				cont.setLeading(14.5f);
				cont.newLineAtOffset(25, 700);
				cont.showText("Bestellnummer: " + order.getId());
				cont.newLine();
				cont.showText("Bestelldatum: " + order.getOrderDate());
				cont.newLine();
				cont.showText("Lieferdatum: " + order.getRequiredDate());
				cont.endText();
				
				List<OrderDetails> details = order.getOrderDetails();
				float ty = 400;
				for (int i = 0; i < details.size(); i++) {
					cont.beginText();
					cont.newLineAtOffset(25, ty - (i * 30));
					cont.showText(details.get(i).getPosition().toString());
					cont.newLineAtOffset(30, 0);
					cont.showText(details.get(i).getProduct().getProductName());
					cont.newLineAtOffset(350, 0);				
					cont.showText(details.get(i).getQuantityOrdered().toString());
					cont.newLineAtOffset(50, 0);
					cont.showText(details.get(i).getPriceEach().toString());
					cont.endText();
				}
			}
			document.getDocumentInformation().setTitle("Der Titel");
			document.getDocumentInformation().setSubject("Das Subject");
			document.getDocumentInformation().setAuthor("Bernd");
			return toByteArray(document);
		}
	}

	
	byte[] simplePdf() throws IOException {
		
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);
			
			try (PDPageContentStream cont = new PDPageContentStream(document, page)) {
				cont.beginText();
				cont.setFont(PDType1Font.TIMES_ROMAN, 12);
				cont.setLeading(14.5f);
				cont.newLineAtOffset(25, 700);
				cont.showText("snippet 1");
				cont.newLine();
				cont.showText("fragment fragment fragment fragment fragment ");
				cont.newLine();
				cont.showText("snippet 2");
				cont.newLine();
				cont.endText();
				
				cont.beginText();
				cont.newLineAtOffset(25, 400);
				cont.showText("zweiter Text");
				cont.endText();
				
			}
			document.getDocumentInformation().setTitle("Der Titel");
			document.getDocumentInformation().setSubject("Das Subject");
			document.getDocumentInformation().setAuthor("Bernd");
			
			return toByteArray(document);
		}
		
	}
	
		
	private byte[] toByteArray(PDDocument pdDocument) throws IOException {
	    //return new PDStream(pdDocument).toByteArray(); // geht nicht, warum?
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            pdDocument.save(out);
            return out.toByteArray();
		}
	}
	
}
