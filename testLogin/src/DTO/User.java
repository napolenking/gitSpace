package DTO;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{
	protected String userName = null;
	protected String userID = null;
	protected String userPwd = null;
	protected String userScope = null;
	protected String userEmail = null;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String userID, String userPwd, String userScope, String userEmail) {
		super();
		this.userName = userName;
		this.userID = userID;
		this.userPwd = userPwd;
		this.userScope = userScope;
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userID=" + userID + ", userPwd=" + userPwd + ", userScope=" + userScope
				+ ", userEmail=" + userEmail + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserScope() {
		return userScope;
	}
	public void setUserScope(String userScope) {
		this.userScope = userScope;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		UserInfo.getInstance().addBean(this);
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		UserInfo.getInstance().removeBean(this);
		
	}
	
}
