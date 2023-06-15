package com.poly.app.enity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String password;
	private String fullname;
	private String email;
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private Boolean active;
	private Date createday;
	private Date updateday;
	private String img;
	private Boolean role;
	
	@OneToMany(mappedBy = "users")
	List<Orders> orders;
	
	@OneToMany(mappedBy = "users")
	List<Favorite> favorites;
	
	@OneToMany(mappedBy = "users")
	List<Share> shares;
	
	@OneToMany(mappedBy = "users")
	List<Feedback> feedbacks ;
}
