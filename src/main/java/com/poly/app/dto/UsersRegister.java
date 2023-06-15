package com.poly.app.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UsersRegister {
	@NotEmpty(message = "Không được bỏ trống email")
	@Email
	public String email;
	@NotEmpty(message = "Không được bỏ trống họ và tên")
	public String fullname;
	@NotNull(message = "Không được bỏ trống ngày sinh")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	public Date birth;
	@NotNull(message = "Không được bỏ trống giới tính")
	public Boolean gender;
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,9}$", message = "Mật khẩu có từ 6-9 kí tự gồm chữ và số")
	public String password;
	@NotEmpty(message = "Không được bỏ trống xác nhận")
	public String confirm;
	@NotEmpty(message = "Không được bỏ trống mã code")
	public String codeKey;
	
	
}
