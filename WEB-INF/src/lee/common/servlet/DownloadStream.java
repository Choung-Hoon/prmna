/*
 * @(#)DownloadStream.java
 */
package lee.common.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import lee.common.log.*;

public class DownloadStream extends HttpServlet {

	private static final boolean DEBUG = true;

	private HashMap handler = null;
	private Logger logger = null;

	public void init(ServletConfig cf) throws ServletException {

		super.init(cf);

		this.handler = new HashMap();
		//this.logger = LoggerFactory.getLogger(this.getClass());
	
		this.logger = Logger.getLogger( this.getClass() ); // by mhlee
		
		// 추가되는 handler가 있으면 배열에에 인스턴스를 생성해 준다.
		DownloadHandler[] handles = new DownloadHandler[] { new DownloadDefaultHandler() };

		for (int i = 0; i < handles.length; i++) {
			try {
				handles[i].init();
				addHandler(handles[i]);
			} catch (Exception e) {
				logger.error("핸들러(" + handles[i].getHandlerId()
						+ ")를 정상적으로 초기화할 수 없습니다.", e);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		// if( DEBUG ) logRequestParameters( req );

		try {

			getHandler(req.getParameter("handler")).handle(req, res);

		} catch (Exception e) {
			logger.error("파일을 다운로드 할 수 없습니다.", e);
		} finally {
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	public void destory() {
		super.destroy();

		String[] keys = new String[handler.size()];
		handler.keySet().toArray(keys);

		for (int i = 0; keys != null && i < keys.length; i++) {
			try {
				((DownloadHandler) handler.get(keys[i])).destroy();
			} catch (Exception e) {
				logger.error("핸들러(" + keys[i] + ")를 정상적으로 종료할 수 없습니다.", e);
			}
		}
	}

	private void addHandler(DownloadHandler newHandler) {
		if (newHandler == null)
			throw new IllegalArgumentException("핸들러가 올바르지 않습니다.");

		this.handler.put(newHandler.getHandlerId().toLowerCase(), newHandler);
	}

	private DownloadHandler getHandler(String handlerId) throws Exception {

		if (handlerId == null || handlerId.equals(""))
			handlerId = "default";

		DownloadHandler result = (DownloadHandler) handler.get(handlerId
				.toLowerCase());
		if (result == null) {
			throw new IllegalArgumentException("핸들러(" + handlerId
					+ ")가 존재하지 않습니다.");
		}

		return result;
	}

	private void logRequestParameters(HttpServletRequest req) {

		System.out.println("Parameters in Servlet : ");

		Enumeration en = req.getParameterNames();
		String key = null;
		while (en.hasMoreElements()) {
			key = en.nextElement().toString();
			System.out.println(key + " : " + req.getParameter(key));
		}
	}

}
