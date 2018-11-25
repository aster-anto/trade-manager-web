package org.trade.web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@ToString(exclude = "password")
@Entity
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id @GeneratedValue Long sysId;

	private String name;

	private @JsonIgnore String password;

	private String[] roles;

	private @ManyToOne
	Domain domain;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	protected User() {}

	public User(String name, String password, Domain domain, String... roles) {

		this.name = name;
		this.setPassword(password);
		this.roles = roles;
		this.domain = domain;
	}

}

