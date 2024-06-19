package com.poly.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.poly.app.service.FeedbackService;
import com.poly.app.service.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.app.Impl.FeedbackServiceImpl;
import com.poly.app.Impl.MailerServiceImpl;
import com.poly.app.dto.MailModel;
import com.poly.app.enity.Feedback;
import com.poly.app.enity.Users;
import com.poly.app.service.SessionService;
import com.poly.app.util.Keyword;

@Controller
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {

	private final FeedbackService feedbackServiceImpl;
	private final MailerService mailerServiceImpl;
	private final SessionService sessionService;
	@GetMapping("views")
	public String index() {
		return "contact";
	}
	
	@GetMapping("report")
	@ResponseBody
	public ResponseEntity<String> report(@RequestParam("content") Optional<String> content) {
		Users users = sessionService.getAttribute(Keyword.acc);
		if(users!=null) {
			Feedback feedback = new Feedback();
			feedback.setCreateday(new Date());
			feedback.setDescription(content.orElse("Phản hồi lỗi"));
			feedback.setUsers(users);
			feedback.setReply(false);
			feedbackServiceImpl.save(feedback);
			sendMailContact(users, content.orElse("Phản Hồi Lỗi"));
		}
		return ResponseEntity.ok("Cảm ơn phản hồi của bạn!");
	}
	
	private void sendMailContact(Users users,String content) {
		SimpleDateFormat simple = new SimpleDateFormat();
		simple.applyPattern("HH:mm dd-MM-yyyy");
		MailModel mailModel = new MailModel();
		mailModel.setTo("sakuramyaha678@gmail.com");
		mailModel.setSubject("Phản Hồi Của Người Dùng");
		mailModel.setBody("   <div class=\"container\" style=\"width: 80%; margin: 0 auto;\">\r\n"
				+ "        <h1 style=\"color: #F29727; text-align: center;\">Phản Hồi Người Dùng</h1>\r\n"
				+ "        <hr>\r\n"      
				+ "        <h3>Tên Người Dùng : "+users.getFullname()+"</h3>\r\n"
				+ "        <h3>Email :"+users.getEmail()+" </h3>\r\n"
				+ "        <h3>Ngày Phản Hồi : "+  simple.format(new Date())  +"</h3>\r\n"
				+ "        <p style=\"font-style: italic; font-weight: 400;\">"+content+"</p>\r\n"
				+ "       \r\n"
				+ "    </div>");
		mailerServiceImpl.queue(mailModel);
	}
}
