package DTO;

import java.util.HashMap;
import java.util.Map;

/**
 * 维护一个user Map集合
 * 
 * 单例模式
 * */
public class UserInfo {
	private Map<Object, Object> userMap = new HashMap<>();
	
	private static UserInfo instance = new UserInfo();
	
	private UserInfo(){
	 }
	 
	 public static UserInfo getInstance(){
		return instance;
	 }
	 
	 public void removeBean(User userBean){
		 userMap.remove(userBean.getUserID());
	 }
	 
	 public void addBean(User userBean){
		 userMap.put(userBean.getUserID(),userBean.getUserName());
	 }
	 
	 public Map<Object, Object> getBeanMap(){
		 return userMap;
	 }
}

