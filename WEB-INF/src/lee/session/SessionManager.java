package lee.session;

import java.util.HashMap;

import lee.bean.Admin;
import lee.bean.Member;
import lee.common.util.DateUtils;

/**
 * Session Manager
 * 
 * @author sjju
 * @version 1.0
 */
public class SessionManager implements Runnable {

	/**
	 * 디폴트 세션 유지 시간(ms)
	 */
	private static final long INTERVAL = 10 * 60 * 60 * 1000;

	/**
	 * 세션 메니저 싱글턴 인스턴스
	 */
	private static SessionManager instance = new SessionManager();

	/**
	 * 세션 유지 시간(ms)
	 */
	private long interval = 0;

	/**
	 * 현재 세션
	 */
	private HashMap sessions = null;

	/**
	 * 세션 확인 쓰레드
	 */
	private Thread thread = null;

	/**
	 * 생성자
	 * 
	 * @param siteCode
	 *            사이트 코드
	 * @param interval
	 *            세션 유지시간
	 */
	private SessionManager() {
		super();

		this.interval = INTERVAL;

		this.sessions = new HashMap();

		this.thread = new Thread(this, "SessionManager monitor thread");
		thread.start();
	}

	public static synchronized SessionManager getInstance() {
		return instance;
	}

	/**
	 * 세션 유지 시간을 설정한다.
	 * 
	 * @param interval
	 *            세션 유지시간
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * 세션 정보를 가져온다.
	 * 
	 * @param userId
	 *            세션 유지되고 있는 사용자 ID
	 * @return
	 */
	public UserSession getUserSession(String sessionId) {
		return (UserSession) sessions.get(sessionId);
	}

	/**
	 * 세션 유지되는 사용자 전체 목록을 가져온다.
	 * 
	 * @return 사용자 목록
	 */
	public synchronized UserSession[] getUserSessionList() {
		UserSession[] result = new UserSession[sessions.size()];
		return (UserSession[]) (sessions.values().toArray(result));
	}

	/**
	 * 세션을 추가한다.
	 * 
	 * @param userSession
	 *            추가할 세션 정보
	 */
	public synchronized void addSession(String sessionId, String ip, Member user) {

		if (sessionId == null || user == null)
			return;

		UserSession userSession = new UserSession();

		userSession.setSessionId(sessionId);
		userSession.setLoginIp(ip);
		userSession.setLoginDate(DateUtils.getDateString("yyyyMMddHHmmss"));
		userSession.setTimestamp(System.currentTimeMillis());
		userSession.setUserid(user.getUserid());
		userSession.setUserpw(user.getUserpw());
		userSession.setName(user.getName());

		if (sessions.get(userSession.getSessionId()) == null) {
			sessions.put(userSession.getSessionId(), userSession);
		}
	}

	/**
	 * 세션을 추가한다.
	 * 
	 * @param userSession
	 *            추가할 세션 정보
	 */
	public synchronized void addSession(String sessionId, String ip, Admin admin) {

		if (sessionId == null || admin == null)
			return;

		UserSession userSession = new UserSession();

		userSession.setSessionId(sessionId);
		userSession.setLoginIp(ip);
		userSession.setLoginDate(DateUtils.getDateString("yyyyMMddHHmmss"));
		userSession.setTimestamp(System.currentTimeMillis());
		userSession.setUserid(admin.getUserid());
		userSession.setUserpw(admin.getUserpw());
		userSession.setName(admin.getName());
		userSession.setUserType(admin.getUserType());
		userSession.setAuth(admin.getAuth());
		userSession.setOrgCd(admin.getOrgCd());
		userSession.setLocCd(admin.getLocCd()); 
		
		if (sessions.get(userSession.getSessionId()) == null) {
			sessions.put(userSession.getSessionId(), userSession);
		}
	}	
	/**
	 * 세션을 제거한다.
	 * 
	 * @param userId
	 *            세션을 제거할 사용자 ID
	 */
	public synchronized void removeSession(String sessionId) {
		if (sessions.get(sessionId) != null)
			sessions.remove(sessionId);
	}

	/**
	 * 세션을 체크하여 로그인 상태를 유지한다.
	 * 
	 * @param userId
	 *            세션 체크할 사용자 ID
	 */
	public void validateSession(String sessionId) {
		UserSession userSession = (UserSession) sessions.get(sessionId);
		if (userSession != null)
			userSession.setTimestamp(System.currentTimeMillis());
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		Object[] contained = null;
		UserSession userSession = null;

		while (true) {

			try {
				Thread.sleep(interval);
			} catch (Exception e) {
			}

			synchronized (this) {
				contained = sessions.entrySet().toArray();
				for (int i = 0; i < contained.length; i++) {
					if (contained[i] instanceof UserSession) {
						userSession = (UserSession) contained[i];
						if (System.currentTimeMillis()
								- userSession.getTimestamp() > interval) {
							sessions.remove(userSession.getSessionId());
						}
					}
				} // end for
			} // end synchronized
		} // end while
	}

}
