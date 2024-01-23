package com.fanthal.PasswordManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fnthl_password")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Password {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
