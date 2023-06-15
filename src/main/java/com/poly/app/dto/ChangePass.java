package com.poly.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePass {
		@NotEmpty(message = "Không bỏ trống mật khẩu")
		private String currentPass;
		@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,9}$", message = "Mật khẩu có từ 6-9 kí tự gồm chữ và số")
		private String newPass;
		@NotEmpty(message = "Không bỏ trống xác nhận")
		private String confirmpass;
		
}
