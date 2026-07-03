package com.solix.supportai.loader;

import com.solix.supportai.model.SupportTicket;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelTicketLoader {

    public List<SupportTicket> loadTickets() {

        List<SupportTicket> tickets = new ArrayList<>();

        try {

            InputStream inputStream =
                    new ClassPathResource("Tickets.xlsx").getInputStream();


            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet("Tickets");

            System.out.println("Sheet Name : " + sheet.getSheetName());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                var row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                SupportTicket ticket = new SupportTicket();

                ticket.setTicketId(getCellValue(row.getCell(0)));
                ticket.setAssignedTo(getCellValue(row.getCell(1)));
                ticket.setReportedBy(getCellValue(row.getCell(2)));
                ticket.setCompany(getCellValue(row.getCell(3)));
                ticket.setReportedDate(getCellValue(row.getCell(4)));
                ticket.setAgeInDays(getCellValue(row.getCell(5)));
                ticket.setModifiedDate(getCellValue(row.getCell(6)));
                ticket.setProductService(getCellValue(row.getCell(7)));
                ticket.setSubject(getCellValue(row.getCell(8)));
                ticket.setSupportComments(getCellValue(row.getCell(9)));
                ticket.setPriority(getCellValue(row.getCell(10)));
                ticket.setSubStatus(getCellValue(row.getCell(11)));
                ticket.setStatus(getCellValue(row.getCell(12)));
                ticket.setSeverity(getCellValue(row.getCell(13)));

                tickets.add(ticket);
            }
            workbook.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return tickets;

    }
    private String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {

        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

}