package com.fanthal.PasswordManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fnthl_refreshtokens")
@NoArgsConstructor
@Data
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "token")
	private String token;

	@Column(name = "expirydate")
	private Date expireDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
