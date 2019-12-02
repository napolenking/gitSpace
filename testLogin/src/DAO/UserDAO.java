package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBManager.C3P0_Util;
import DTO.User;

public class UserDAO {
	private static List<String> array;

	public static User queryIfExist(String userid, String password) {
		
		List<User> queryrs = new ArrayList<User>();
		Connection con=C3P0_Util.getConnection();;
		String sql = "SELECT * FROM db_stu.table_ad where userName=? and userPwd=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(con!=null) {
			try {
					ps = con.prepareStatement(sql);
					if(ps != null){
						ps.setString(1, userid);
						ps.setString(2, password);
						rs = ps.executeQuery();
						while (rs.next()) {
							String username = rs.getString("userName");
							String userpwd = rs.getString("userPwd");
							String useridx = rs.getString("userID");
							String userscope = rs.getString("userScope");
							String useremail = rs.getString("userEmail");
							queryrs.add(new User(username, useridx, userpwd, userscope,useremail));
						}
					}else {
						System.out.println("连接错误！");
					}
					
			}catch (SQLException e){
				e.printStackTrace();
			}finally {
				C3P0_Util.RSclose(rs);
				C3P0_Util.PSclose(ps);
				C3P0_Util.closeConnection();
			}
			
		}else {
			System.out.println("con为空!");
		};
		
			
		
		
		if (queryrs.size() == 0 ) {
			return null;
		}
		if (queryrs.size() > 1) {
			System.out.println("账号有误!");
		}
		return queryrs.get(0);
	}
	
	public static String[] registerIfExist(String email,String username) {
		Connection con=C3P0_Util.getConnection();
		String sql = "SELECT * FROM db_stu.table_ad where userEmail=? or userName=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] str = new String[2];
		if(con!=null) {
			try {
					ps = con.prepareStatement(sql);
					if(ps != null){
						ps.setString(1, email);
						ps.setString(2, username);
						rs = ps.executeQuery();
						if(rs.next()) {
							String uname =rs.getString("userName");								
							String uemail =rs.getString("userEmail");
							System.out.println(uname+":"+uemail);
							if(uname!=null) {
								str[0]=uname;
							}
							
							if(uemail !=null) {
								str[1]=uemail;
							}
							
						}else {
							System.out.println("rs.next():"+rs.next());
							str = null;
						}
					}else {
							System.out.println("PreparedStatement为空！");
						}
				}catch (SQLException e){
					e.printStackTrace();
				}finally {
					C3P0_Util.RSclose(rs);
					C3P0_Util.PSclose(ps);
					C3P0_Util.closeConnection();
				}
		}else{
			System.out.println("连接错误！");
		}
		return str;
	}	
}
