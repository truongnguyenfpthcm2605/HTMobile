package com.poly.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.poly.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.app.Impl.MailerServiceImpl;
import com.poly.app.Impl.OrdersDetailSeviceImpl;
import com.poly.app.Impl.OrdersServiceImpl;
import com.poly.app.Impl.ProductServiceImpl;
import com.poly.app.Impl.ShoppingCartServiceImpl;
import com.poly.app.dto.CartItem;
import com.poly.app.dto.MailModel;
import com.poly.app.enity.Orders;
import com.poly.app.enity.OrdersDetail;
import com.poly.app.enity.Product;
import com.poly.app.enity.Users;
import com.poly.app.enity.Voucher;
import com.poly.app.util.Keyword;
import com.poly.app.util.WriteWord;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
@RequestMapping("bill")
@RequiredArgsConstructor
public class BillController {
	private final ShoppingCartService shoppingCartServiceImpl;
	private final OrdersService ordersServiceImpl;
	private final ProductService productServiceImpl;
	private final OrdersDetailService ordersDetailSeviceImpl;
	private final SessionService sessionService;
	private final ServletContext app;
	private final MailerService mailerServiceImpl;

	@ModelAttribute("user")
	public Users getUsers() {
		return (Users) sessionService.getAttribute(Keyword.acc);
	}

	@ModelAttribute("listByProducts")
	public List<CartItem> getCartItems() {
		return shoppingCartServiceImpl.getAllCartItem();
	}

	@ModelAttribute("total")
	public double getTotal() {
		return shoppingCartServiceImpl.getTotal();
	}

	@ModelAttribute("order")
	public Orders getOrders() {
		Orders orders = new Orders();
		orders.setCreateday(new Date());
		return orders;
	}

	@ModelAttribute("discounts")
	public double getDiscount() {
		if (sessionService.getAttribute(Keyword.voucher) != null) {
			Voucher voucher = (Voucher) sessionService.getAttribute(Keyword.voucher);
			return getTotal() * voucher.getDiscount() / 100;

		}
		return 0;
	}

	@ModelAttribute("totalAll")
	public double getTotalAll() {
		if (getDiscount() > 0) {
			return getTotal() - getDiscount();
		}
		return getTotal();
	}

	@GetMapping("view")
	public String view(Model model) {
		return "bill";
	}

	@PostMapping("pay")
	public String save(@Valid @ModelAttribute("order") Optional<Orders> orders, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng Điền thông tin hợp lệ");
		} else {
			Voucher voucher = null;
			// add Orders
			Orders ordersBy = orders.get();
			ordersBy.setCreateday(new Date());
			if (sessionService.getAttribute(Keyword.voucher) != null) {
				voucher = (Voucher) sessionService.getAttribute(Keyword.voucher);
				ordersBy.setVoucher(voucher.getId());
			}
			ordersBy.setUsers(getUsers());
			ordersServiceImpl.save(ordersBy);
			// update product
			updateProduct();

			// add OrderDetail
			addOrdersDetail(ordersBy);

			// print bill
			String path = app.getRealPath("/document/Bill.docx");
			WriteWord.exportToWord(getCartItems(), ordersBy, getUsers(), voucher, path);

			// send mail ;
			Updatemailsender();
			sessionService.removeAttribute(Keyword.voucher);

			return "thanks";

		}
		return "bill";
	}

	private void updateProduct() {
		List<CartItem> list = shoppingCartServiceImpl.getAllCartItem();
		for (CartItem cartItem : list) {
			Product p = productServiceImpl.findById(cartItem.getId()).get();
			p.setQuanityfinal(p.getQuanityfinal() - cartItem.getQty());
			p.setQuantitysold(p.getQuantitysold() + cartItem.getQty());
			p.setUpdateday(new Date());
			productServiceImpl.update(p);
		}
	}

	private void addOrdersDetail(Orders orders) {
		List<CartItem> list = shoppingCartServiceImpl.getAllCartItem();
		for (CartItem cartItem : list) {
			OrdersDetail ordersDetail = new OrdersDetail(); // Tạo đối tượng OrdersDetail mới cho mỗi CartItem
			Product p = productServiceImpl.findById(cartItem.getId()).get();
			ordersDetail.setOrders(orders);
			ordersDetail.setProduct(p);
			ordersDetail.setPrice(cartItem.getPrice());
			ordersDetail.setQuantity(cartItem.getQty());
			ordersDetailSeviceImpl.save(ordersDetail); // Lưu đối tượng OrdersDetail mới vào CSDL
		}
	}

	private void Updatemailsender() {
		try {
			MailModel mailClient = new MailModel();
			mailClient.setTo(getUsers().getEmail());
			mailClient.setSubject("Mail Xác Nhận Đơn Hàng");
			mailClient.setBody(" <div class=\"container\">\r\n"
					+ "        <h1 style=\"color: #080202; font-size: 50px; font-weight: 400;\">Cảm ơn bạn đã ủng hộ chúng tôi . \r\n"
					+ "        <p style=\"font-size: 30px; color: #C38154;\">Đơn hàng của bạn đã được chúng tôi xác nhận . Nếu có thay đổi chúng tôi sẽ phản hồi qua mail của bạn  </h1>\r\n"
					+ "\r\n" + "        <div style=\"background-color:\r\n"
					+ "         #C38154; width: 60%;height: 10px; margin: 30px auto; border-radius: 10px;box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;\"></div>\r\n"
					+ "        <h1 style=\"font-size: 50px; text-align: center; color: #C38154;\">\r\n"
					+ "          HTMobile\r\n" + "        </h1>\r\n"
					+ "        <h1 style=\"color: #B31312;\">Đây là hóa đơn của bạn</h1>\r\n" + "\r\n" + "        \r\n"
					+ "    </div>");
			File fileBill = new File(app.getRealPath("/document/Bill.docx"));
			List<File> list = new ArrayList<>();
			list.add(fileBill);
			mailClient.setFiles(list);
			mailerServiceImpl.queue(mailClient);
			MailModel mailAdmin = new MailModel();
			mailAdmin.setTo("sakuramyaha678@gmail.com");
			mailAdmin.setSubject("Đơn Mua Hàng Từ  " + getUsers().getFullname());
			mailAdmin.setBody(" <div class=\"container\">\r\n"
					+ "        <h1 style=\"color: #080202; font-size: 50px; font-weight: 400;\">Hóa Đơn Mua Hàng từ Khách Hàng . \r\n"
					+ "        <p style=\"font-size: 30px; color: #C38154;\">Đơn hàng đã được xác nhận mua bởi "
					+ getUsers().getFullname() + "  </h1>\r\n" + "\r\n" + "        <div style=\"background-color:\r\n"
					+ "         #C38154; width: 60%;height: 10px; margin: 30px auto; border-radius: 10px;box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;\"></div>\r\n"
					+ "        <h1 style=\"font-size: 50px; text-align: center; color: #C38154;\">\r\n"
					+ "          HTMobile\r\n" + "        </h1>\r\n"
					+ "        <h1 style=\"color: #B31312;\">Đây là hóa đơn của  " + getUsers().getFullname()
					+ " </h1>\r\n" + "\r\n" + "        \r\n" + "    </div>");
			mailAdmin.setFiles(list);
			mailerServiceImpl.queue(mailAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
