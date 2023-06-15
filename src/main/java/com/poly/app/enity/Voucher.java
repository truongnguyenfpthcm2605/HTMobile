package com.poly.app.enity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
@Entity
@Table(name = "voucher")
public class Voucher {
	@Id
	@Pattern(regexp = "[A-Z0-9]{6}", message = "ID phải có 6 kí tự là chữ IN HOA và số")
	private String id;
	@NotEmpty(message = "Khồng được bỏ trống Name")
	private String name;
	@Min(value = 1, message = "Số phải lớn hơn hoặc bằng 1%")
    @Max(value = 100, message = "Số phải nhỏ hơn hoặc bằng 100%")
	private Integer discount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date createday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date endday;
	private Boolean active;
}
