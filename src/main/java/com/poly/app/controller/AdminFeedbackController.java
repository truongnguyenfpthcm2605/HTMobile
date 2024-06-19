package com.poly.app.controller;

import com.poly.app.dto.MailModel;
import com.poly.app.enity.Feedback;
import com.poly.app.service.FeedbackService;
import com.poly.app.service.MailerService;
import com.poly.app.util.SortAnPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/feedback")
@RequiredArgsConstructor
public class AdminFeedbackController {
	private final FeedbackService feedbackServiceImpl;
	private final MailerService mailerServiceImpl;
	@GetMapping("")
	private String view(Model model) {
		model.addAttribute("ListFeedback", feedbackServiceImpl.findAll(SortAnPage.getSort("createday")));
		return "adminfeedback";
	}

	@GetMapping("delete/{id}")
	private String delete(@PathVariable("id") Integer id) {
		feedbackServiceImpl.deleteById(id);
		return "redirect:/admin/feedback";
	}

	@PostMapping("search")
	private String search(Model model, @RequestParam("search") Optional<String> keyword) {
		List<Feedback> feedbacks = feedbackServiceImpl.findByKeyWordLike("%" + keyword.orElse("") + "%");
		model.addAttribute("ListFeedback", feedbacks);
		model.addAttribute("search", keyword.orElse(""));
		return "adminfeedback";
	}

	@PostMapping("active")
	private String isActive(Model model, @RequestParam("active") Boolean isActive) {
		List<Feedback> list = new ArrayList<>();
		if (feedbackServiceImpl.findByReply(isActive).isPresent()) {
			list = feedbackServiceImpl.findByReply(isActive).get();
		}
		model.addAttribute("active", isActive);
		model.addAttribute("ListFeedback", list);
		return "adminfeedback";
	}

	@PostMapping("report")
	private String report(@RequestParam("id") Integer id, @RequestParam("report") String report) {
		Feedback feedback = feedbackServiceImpl.findById(id).get();
		sendMailReport(feedback, report);
		feedback.setReply(true);
		feedbackServiceImpl.update(feedback);
		return "redirect:/admin/feedback";
	}

	private void sendMailReport(Feedback feedback, String content) {
		SimpleDateFormat simple = new SimpleDateFormat();
		simple.applyPattern("HH:mm dd-MM-yyyy");
		MailModel mailModel = new MailModel();
		mailModel.setTo(feedback.getUsers().getEmail());
		mailModel.setSubject("Trả Lời Phản Hồi Của Bạn");
		mailModel.setBody(" <div class=\"container\" style=\"width: 80%; margin: 0 auto;\">\r\n"
				+ "        <h1 style=\"color: #F29727; text-align: center;\">Phản Hồi Người Dùng</h1>\r\n"
				+ "        <hr>\r\n" + "        <h3>Tên Người Dùng : " + feedback.getUsers().getFullname() + "</h3>\r\n"
				+ "        <h3>Email : " + feedback.getUsers().getEmail() + "</h3>\r\n" + "        <h3>Ngày Phản Hồi : "
				+ simple.format(feedback.getUsers().getCreateday()) + " </h3>\r\n"
				+ "        <p style=\"font-style: italic; font-weight: 400;\">" + feedback.getDescription() + "</p>\r\n"
				+ "        <hr>\r\n"
				+ "        <h2 style=\"color: #606C5D; font-style: italic;\">Trả Lời Của Admin</h2>\r\n"
				+ "        <p style=\"font-style: italic; font-weight: 400;\">" + content + "</p>\r\n"
				+ "<h3>Ngày Trả Lời : " + simple.format(new Date()) + " </h3>\r\n" + "    </div>");
		mailerServiceImpl.queue(mailModel);
	}
}
