package day15_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 使用最基本的方式创建数据库连接池
 * @author kdyzm
 *
 */
public class JDBCPool1 {
	private static ArrayList<Connection>pool=new ArrayList<Connection>();
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysq://localhost:3306?useUnicode=true&characterEncoding=utf-8";
			for(int i=0;i<5;i++)
			{
				Connection conn=DriverManager.getConnection(url, "root", "5a6f38");
				pool.add(conn);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn()
	{
		synchronized (pool) {
			Connection conn=pool.remove(0);
			System.out.println("还有 "+pool.size()+"个连接");
			return conn;
		}
	}
	
	public static void back(Connection conn)
	{
		System.out.println("还连接："+conn);
		pool.add(conn);
	}
}
