package com.ori.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ori.enums.UserType;


	@Entity
	@Table(name="Users")
	public class User implements Serializable{
		
		@Id
		@GeneratedValue
		@Column(name="ID")
		private long id;
		
				
		@Column(name="PASSWORD", nullable=false)
		private String password;
		
		@Column(name="TYPE", nullable=false)
		private UserType type;
		
//		@Column(name="COMPANYID")
//		private Long companyId;
		
		@Column(name="EMAIL", unique=true, nullable=false)
		private String email;
		
//		@Column(name="IS_ACTIVE")
//		private boolean isActive;
		
		
		@ManyToOne
		private Company company;
		
		@JsonIgnore
		@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private Customer customer;
		
		private String newPassword;
		
		
		public User() {
			super();
		}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the type
		 */
		public UserType getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(UserType type) {
			this.type = type;
		}

		
		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the company
		 */
		public Company getCompany() {
			return company;
		}

		/**
		 * @param company the company to set
		 */
		public void setCompany(Company company) {
			this.company = company;
		}
		
		

		/**
		 * @return the customer
		 */
		public Customer getCustomer() {
			return customer;
		}

		/**
		 * @param customer the customer to set
		 */
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", password=" + password + ", type=" + type + ", email=" + email + ", company="
					+ company + ", customer=" + customer + "]";
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		

		
		
	
 
}
