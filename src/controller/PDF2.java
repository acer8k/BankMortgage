package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableBody;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF2 
{
	public static void main(String[] args) throws Exception
	{
		OutputStream file = new FileOutputStream(new File("C://reset/account_Statement.pdf"));
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        Font fontbold = FontFactory.getFont("Times-Roman", 14, Font.ITALIC);
        Paragraph para=new Paragraph("Dear ",fontbold);
        
        Paragraph AccInfo = new Paragraph();
        
        PdfPTable table= new PdfPTable(4);
        
        PdfPCell cell = new PdfPCell(new Paragraph("Universal Bank Of Albany"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(8.0f);
        cell.setBackgroundColor(new BaseColor(255, 204, 0));
        Paragraph cell_fill = new Paragraph("Account Statement");
      //  Font y = new Font();
       // y.setColor(255,204,0);
        //cell_fill.setFont(y);
        PdfPCell cell1=new PdfPCell(cell_fill);
        cell1.setColspan(4);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setPadding(5.0f);
        cell1.setBackgroundColor(new BaseColor(255,51, 204));
        table.addCell(cell);
        table.addCell(cell1);
        table.addCell("Date - Time");
        table.addCell("Transaction Type");
        table.addCell("Amount");
        table.addCell("Balance Before");
        
        Class.forName ("com.mysql.jdbc.Driver"); 
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bank","and","and");
        Statement AccountStmt = conn.createStatement();
        Statement OwnersStmt = conn.createStatement();
        Statement TransactionStmt = conn.createStatement();
        
        //NEEDS ID FROM SOMEWHERE
        ResultSet Account_set = AccountStmt.executeQuery("SELECT * FROM accounts WHERE account_Id = 3"   );
        
        Account_set.next();
        AccInfo.add(Account_set.getString("type") + " Account #");
        		AccInfo.add(Account_set.getInt("account_Id") + "\nBalance: ");
        				AccInfo.add(Account_set.getDouble("balance") +"\n" );
        
        ResultSet Owners_set = OwnersStmt.executeQuery("SELECT * FROM ownerships INNER JOIN user_profiles ON ownerships.user_Id = user_profiles.user_Id WHERE account_Id = 3"   );
        
        while(Owners_set.next()){
        	para.add(Owners_set.getString("firstName") + " " + Owners_set.getString("lastName") + ",");
        }
        
        ResultSet Transaction_set = TransactionStmt.executeQuery("SELECT * FROM transactions WHERE account_Id = 3 ORDER BY transaction_Id DESC"   );
        PdfPCell cell2=new PdfPCell();
        while (Transaction_set.next()) 
        {       
        	
            
        	String date = Transaction_set.getString("date") + " " + Transaction_set.getString("time");
            cell2=new PdfPCell(new Phrase(date));
            table.addCell(cell2);
            String type =Transaction_set.getString("type");
            cell2=new PdfPCell(new Phrase(type));
            table.addCell(cell2);
            String amount =Transaction_set.getString("ammount");
            cell2=new PdfPCell(new Phrase(amount));
            table.addCell(cell2);
            String oldValue=Transaction_set.getString("oldValue");
            cell2=new PdfPCell(new Phrase(oldValue));
            table.addCell(cell2);
       }
        
        document.open();
        
        document.add(para);
        document.add(AccInfo);
        document.add(Chunk.NEWLINE);
        document.add(table);
        
        document.close();
        file.close();
        System.out.println("PDF Generated...");
        
	}
}
