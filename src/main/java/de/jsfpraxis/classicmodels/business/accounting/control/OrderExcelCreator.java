package de.jsfpraxis.classicmodels.business.accounting.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;

public class OrderExcelCreator {
	
	public byte[] toPdf(Order order) throws IOException {
		
        Workbook workbook = new XSSFWorkbook();
        
        Sheet sheet = workbook.createSheet("Bestellung");
        sheet.setDefaultColumnWidth(20);
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Bestellnummer:");
        row1.createCell(1).setCellValue(order.getId());
        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Bestelldatum:");
        row2.createCell(1).setCellValue(order.getOrderDate().toString());
        Row row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("Lieferdatum:");
        row3.createCell(1).setCellValue(order.getRequiredDate().toString());

        Sheet detailsSheet = workbook.createSheet("Bestellpositionen");
        detailsSheet.setDefaultColumnWidth(10);
        detailsSheet.setColumnWidth(1, 40 * 256);
        
        int rowCounter = 0;
        List<OrderDetails> details = order.getOrderDetails();
        for (int i = 0; i < details.size(); i++) {
            Row row = detailsSheet.createRow(rowCounter + i);
            Cell cellCol1 = row.createCell(0);
            cellCol1.setCellValue(details.get(i).getPosition());
            Cell cellCol2 = row.createCell(1);
            cellCol2.setCellValue(details.get(i).getProduct().getProductName());
            Cell cellCol3 = row.createCell(2);
            cellCol3.setCellValue(details.get(i).getQuantityOrdered());
            Cell cellCol4 = row.createCell(3);
            cellCol4.setCellValue(details.get(i).getPriceEach().toString());
		}
		return toByteArray(workbook);
	}

	
	private byte[] toByteArray(Workbook workbook) throws IOException {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			workbook.write(out);
            return out.toByteArray();
		}
	}
	
}


