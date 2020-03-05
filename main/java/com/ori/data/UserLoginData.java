package com.ori.data;

import com.ori.enums.UserType;

public class UserLoginData {
	
		
		private long id;
		private UserType userType;
		private Long companyId;
		
		
		
		public UserLoginData(long id, UserType userType) {
			super();
			this.id = id;
			this.userType = userType;
		}

		public UserLoginData(long id, UserType userType, Long companyId) {
			this(id, userType);
			this.companyId = companyId;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public UserType getUserType() {
			return userType;
		}

		public void setUserType(UserType userType) {
			this.userType = userType;
		}

		public Long getCompanyId() {
			return companyId;
		}

		public void setCompanyId(Long companyId) {
			this.companyId = companyId;
		}

	


}
