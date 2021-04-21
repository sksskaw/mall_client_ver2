package mall.client.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBListener implements ServletContextListener {

	// 톰켓이 꺼질때 실행하는 메소드
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed");
    }
    
    // 톰켓이 켜질때 실행하는 메소드
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("mariaDB 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("mariaDB 로딩 실패");
			e.printStackTrace();
		}
    }
	
}
