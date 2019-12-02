package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0_Util {

	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	// 创建一个ThreadLocal对象，以当前线程作为key
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	public static Connection getConnection(){
		Connection conn = threadLocal.get();
			try {
				if (null == conn) {
					conn = dataSource.getConnection();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			threadLocal.set(conn);
			return conn;
	}
	// 提供一个dataSource数据源
	public static DataSource getDataSource() {
			return dataSource;
		
	}
	
	/**
	 * 关闭Connection,并移除线程中的连接
	 * @throws SQLException
	 */
	public static void closeConnection(){
		try {
			getConnection().close();
			threadLocal.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
	}
	
	
	public static void PSclose(PreparedStatement stm){
		try {
			if(stm != null){
				stm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void RSclose(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


