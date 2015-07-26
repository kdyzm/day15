package cn.itcast.utils;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Properties;
public class ConnUtils {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	static{
		try {
			//声明资源器类　－　
			Properties prop = new Properties();
			//获取这个文件的路径
			URL url = ConnUtils.class.getClassLoader().getResource("jdbc.properties");
			String path = url.getPath();
			//为了防止有中文或是空格
			path = URLDecoder.decode(path,"UTf-8");
			File file = new File(path);
			//加载jdbc.properties这个文件
			prop.load(new FileInputStream(file));
			//获取信息
			String driver = prop.getProperty("driver");
			Class.forName(driver);
			String jdbcurl = prop.getProperty("url");
			String nm = prop.getProperty("name");
			String pwd = prop.getProperty("pwd");
			//创建三个原生的连接，都将它们代理
			String poolSize = prop.getProperty("poolSize");
			int size = Integer.parseInt(poolSize);
			for(int i=0;i<size;i++){
				final Connection con = DriverManager.getConnection(jdbcurl,nm,pwd);
				//对con进行动态代理
				Object proxyedObj = 
						Proxy.newProxyInstance(ConnUtils.class.getClassLoader(),
									new Class[]{Connection.class},
									new InvocationHandler() {
										public Object invoke(Object proxy, Method method, Object[] args)
												throws Throwable {
											//是否是close方法
											if(method.getName().equals("close")){
												synchronized (pool) {
													pool.addLast((Connection) proxy);
													pool.notify();
												}
												return null;//如果调用的是close则不会调用被代理类的方法。
											}
											return method.invoke(con, args);
										}
									});
				//将代理对象放到pool中
				pool.add((Connection) proxyedObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		synchronized (pool) {
			if(pool.size()==0){
				try {
					pool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return getConn();
			}else{
				Connection con = pool.removeFirst();
				System.err.println("还有几个："+pool.size());
				return con;
			}
		}
	}
}
