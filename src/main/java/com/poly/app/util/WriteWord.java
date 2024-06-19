package com.poly.app.util;

import com.poly.app.dto.CartItem;
import com.poly.app.enity.Orders;
import com.poly.app.enity.Users;
import com.poly.app.enity.Voucher;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WriteWord {

	public static void exportToWord(List<CartItem> orderList, Orders orders, Users users, 
			Voucher voucher,String path) {
		try {
			XWPFDocument document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			
			SimpleDateFormat format = new SimpleDateFormat();
			format.applyPattern("HH:mm dd-MM-yyyy");
			XWPFRun run = paragraph.createRun();
			run.setBold(true);
			run.setFontSize(30);
			run.setText("Hóa Đơn Mua Hàng");
			run.setColor("FF6600");
			run.setFontFamily("Times New Roman");
			run.addBreak();

			XWPFParagraph contentShop = document.createParagraph();
			XWPFRun runShop = contentShop.createRun();
			runShop.setBold(true);
			runShop.setFontSize(12);
			runShop.setText("Cửa Hàng : HTMobile ");
			runShop.addBreak();
			runShop.setText("Liên Hệ : 099999990");
			runShop.addBreak();
			runShop.setText("Email : HTMobile@gmail.com");
			runShop.addBreak();
			runShop.setText("Địa Chỉ  : Công Viên Phần Mềm Quang Trung, Quận 12 , Thành Phố Hồ Chí Minh.");
			runShop.setFontFamily("Times New Roman");
			runShop.addBreak();

			XWPFParagraph content = document.createParagraph();
			XWPFRun runcontent = content.createRun();
			runcontent.setFontSize(10);
			runcontent.setText("Tên Khách Hàng : " + users.getFullname());
			runcontent.addBreak();
			runcontent.setText("Số Điện Thoại : " + orders.getPhone());
			runcontent.addBreak();
			runcontent.setText("Email : " + users.getEmail());
			runcontent.addBreak();
			runcontent.setText("Địa Chỉ Giao : " + orders.getAddress());
			runcontent.addBreak();
			runcontent.setText("Ngày Tạo : " + format.format(orders.getCreateday()));
			runcontent.addBreak();
			if(orders.getNotes()!=null) {
				runcontent.setText("Thông tin thêm : "+ orders.getNotes());
			}
			runcontent.setFontFamily("Times New Roman");
			runcontent.addBreak();

			int rowCount = orderList.size() + 1; // total size List Product + 1 row title
			int colCount = 5; // number of column
			XWPFTable table = document.createTable(rowCount, colCount);

			int[] columnWidths = { 1000, 3000, 2000, 2000, 2000 };
			for (int i = 0; i < colCount; i++) {
				CTTblWidth columnWidth = table.getRow(0).getCell(i).getCTTc().addNewTcPr().addNewTcW();
				columnWidth.setType(STTblWidth.DXA);
				columnWidth.setW(BigInteger.valueOf(columnWidths[i]));
			}

			// format title table
			XWPFTableRow headerRow = table.getRow(0);
			String[] headers = { "STT", "Tên Sản phẩm", "Số lượng", "Giá Tiền", "Tổng tiền" };
			for (int i = 0; i < headers.length; i++) {
				XWPFTableCell cell = headerRow.getCell(i);
				cell.setText(headers[i]);
				cell.setColor("D9D9D9");
				cell.setVerticalAlignment(XWPFVertAlign.CENTER);
				cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

				XWPFParagraph paragraphs = cell.getParagraphs().get(0);
				paragraphs.setAlignment(ParagraphAlignment.CENTER);
			}

			DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

			double sum = 0;
			double vs =0;
			// add data to table
			for (int i = 0; i < orderList.size(); i++) {
				CartItem order = orderList.get(i);
				XWPFTableRow row = table.getRow(i + 1);
				row.getCell(0).setText(String.valueOf(i + 1));
				row.getCell(1).setText(order.getName());
				row.getCell(2).setText(String.valueOf(order.getQty()));
				row.getCell(3).setText(decimalFormat.format(order.getPrice()));
				BigDecimal price = BigDecimal.valueOf(order.getPrice());
				BigDecimal quantity = BigDecimal.valueOf(order.getQty());
				BigDecimal total = price.multiply(quantity);
				double orderTotal = total.doubleValue();
				sum += orderTotal;
				row.getCell(4).setText(decimalFormat.format(total));
			}

			XWPFParagraph totalContent = document.createParagraph();
			XWPFRun runTotal = totalContent.createRun();
			runTotal.setFontSize(12);
			runShop.setBold(true);
			runTotal.setText("Tổng tiền: " + decimalFormat.format(sum));
			runTotal.addBreak();
			if (voucher != null) {
				vs = sum * voucher.getDiscount() / 100;
				runTotal.setText("Mã giảm giá  : " + voucher.getDiscount() + "% : "
						+ decimalFormat.format(vs));
			}
			runTotal.addBreak();
			runTotal.setText("Tổng tiền phải trả là : " + decimalFormat.format(sum-vs));
			run.addBreak();
			FileOutputStream outputStream = new FileOutputStream(path);
			document.write(outputStream);
			outputStream.close();
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
