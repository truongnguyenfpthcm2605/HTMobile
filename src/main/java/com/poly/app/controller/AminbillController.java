package com.poly.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.poly.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.app.Impl.MailerServiceImpl;
import com.poly.app.Impl.OrdersDetailSeviceImpl;
import com.poly.app.Impl.OrdersServiceImpl;
import com.poly.app.Impl.VoucherServiceImpl;
import com.poly.app.dto.CartItem;
import com.poly.app.dto.MailModel;
import com.poly.app.enity.Orders;
import com.poly.app.enity.OrdersDetail;
import com.poly.app.enity.Users;
import com.poly.app.enity.Voucher;
import com.poly.app.util.WriteWord;

import jakarta.servlet.ServletContext;


@Controller
@RequestMapping("admin/bill")
@RequiredArgsConstructor
public class AminbillController {
	private final OrdersService ordersServiceImpl;
	private final OrdersDetailService ordersDetailSeviceImpl;
	private  final VoucherService voucherServiceImpl;
	private final SessionService sessionService;
	private final ServletContext app;
	private final MailerService mailerServiceImpl;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("listBill", ordersServiceImpl.findAll());
		return "adminbill";
	}

	@GetMapping("view/{id}")
	public String view(Model model, @PathVariable("id") Integer id) {
		sessionService.setAttribute("id", id);
		Orders orders = ordersServiceImpl.findById(id).get();
		List<OrdersDetail> list = getDetails(id);
		Voucher voucher = getVoucher(orders);
		double total = getTotal(list);
		model.addAttribute("total",total );
		model.addAttribute("totalAll", total);
		if(voucher!=null) {
			model.addAttribute("voucher", voucher);
			model.addAttribute("mgg",total*voucher.getDiscount()/100 );
			model.addAttribute("totalAll", total- total*voucher.getDiscount()/100 );
		}
		printBill(convertToCartItem(list), orders, orders.getUsers(), voucher);
		model.addAttribute("orders", orders);
		model.addAttribute("lisDetails", list);
		
		return "viewbill";
	}

	@PostMapping("search")
	private String search(Model model, @RequestParam("search") Optional<String> keyword) {
		List<Orders> list = ordersServiceImpl.findByKeywords("%" + keyword.orElse("") + "%").get();
		model.addAttribute("listBill", list);
		return "adminbill";
	}

	@PostMapping("status")
	private String sort(Model model, @RequestParam("status") String status) {
		model.addAttribute("listBill", ordersServiceImpl.findByStatus(status).get());
		model.addAttribute("key", status);
		return "adminbill";
	}

	@PostMapping("month")
	private String getMonth(Model model, @RequestParam("months") Integer month) {
		model.addAttribute("listBill", ordersServiceImpl.findByMoths(month).get());
		model.addAttribute("month", month);
		return "adminbill";
	}

	@PostMapping("updatestatus")
	private String updateStatud(@RequestParam("update") String status, @RequestParam("id") Integer id) {
		Orders orders = ordersServiceImpl.findById(id).get();
		orders.setStatus(status);
		ordersServiceImpl.update(orders);
		senmail(orders);
		return "redirect:/admin/bill";
	}

	private List<OrdersDetail> getDetails(Integer id) {
		List<OrdersDetail> lisDetails = ordersDetailSeviceImpl.findByOrders(id).get();
		return lisDetails;
	}
	
	private Double getTotal(List<OrdersDetail> liDetails) {
		double sum  =0;
		for (OrdersDetail ordersDetail : liDetails) {
			sum+=ordersDetail.getPrice()*ordersDetail.getQuantity();
		}
		return sum;
	}
	
	private Voucher getVoucher(Orders orders) {
		Voucher voucher =null;
		if(orders.getVoucher()!=null) {
			voucher = voucherServiceImpl.findById(orders.getVoucher()).get();
		}
		return voucher;
	}
	
	private List<CartItem> convertToCartItem(List<OrdersDetail> ordersDetails){
		List<CartItem> listCart = new ArrayList<>();
		for (OrdersDetail ordersDetail : ordersDetails) {
			CartItem cart = new CartItem();
			cart.setName(ordersDetail.getProduct().getTitle());
			cart.setPrice(ordersDetail.getPrice());
			cart.setQty(ordersDetail.getQuantity());
			listCart.add(cart);
		}
		return listCart;
	}
	
	private void printBill(List<CartItem> list, Orders orders, Users users, Voucher voucher) {
			String path = app.getRealPath("/document/updateBiil.docx");
			WriteWord.exportToWord(list, orders, users, voucher, path);		
	}

	private void senmail(Orders orders) {
		try {
			MailModel mail = new MailModel();
			mail.setTo(orders.getUsers().getEmail());
			mail.setSubject("Cập Nhật Trạng Thái Đơn Hàng");
			mail.setBody(" <div class=\"container\">\r\n"
					+ "        <h1 style=\"color: #080202; font-size: 50px; font-weight: 400;\">Cảm ơn bạn đã ủng hộ chúng tôi . \r\n"
					+ "        <p style=\"font-size: 30px; color: #C38154;\">Đơn hàng của bạn đã được chúng tôi xác nhận . Nếu có thay đổi chúng tôi sẽ phản hồi qua mail của bạn  </h1>\r\n"
					+ "\r\n" + "        <div style=\"background-color:\r\n"
					+ "         #C38154; width: 60%;height: 10px; margin: 30px auto; border-radius: 10px;box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;\"></div>\r\n"
					+ "        <h1 style=\"font-size: 50px; text-align: center; color: #C38154;\">\r\n"
					+ "          HTMobile\r\n" + "        </h1>\r\n"
					+ "        <h1 style=\"color: #B31312;\">Cập nhật tráng thái đơn hàng của bạn : "+ orders.getStatus()  +"</h1>\r\n" + "\r\n" + "        \r\n"
					+ "    </div>");
			List<File> list = new ArrayList<>();
			File file = new File(app.getRealPath("/document/updateBiil.docx"));
			list.add(file);
			mail.setFiles(list);
			mailerServiceImpl.queue(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
