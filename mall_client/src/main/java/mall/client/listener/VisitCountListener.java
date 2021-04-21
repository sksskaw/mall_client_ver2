package mall.client.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mall.client.model.StatsDao;

@WebListener
public class VisitCountListener implements HttpSessionListener {

	StatsDao statsDao = null;
	
    public void sessionCreated(HttpSessionEvent se)  { 
    	
    	statsDao = new StatsDao();
        // 오늘날짜의 카운트가 없으면 1을 입력
    	// 오늘 날짜의 카운트가 있으면 +1 업데이트
    	System.out.println("새로운 세션이 생성 되었습니다.");
    	
    	// 오늘의 방문자가 없으면, 오늘 최초 방문시 stats생성
    	if(this.statsDao.selectStatsByToday() == 0) {
    		this.statsDao.insertStats();
    	} else { // 방문자가 있다면 count + 1
    		this.statsDao.updateStats();
    	}
    	
    	System.out.println("총 방문자 : " + this.statsDao.selectStatsTotal());
    	
    	
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
        
    }
	
}
