/*
* @(#)ConstantUtil.java   1.0 2011/07/01
 */
package lee.common.config;

import java.util.HashMap;

import lee.common.util.StringUtil;

public class ConstantUtil {
    //--  게시판 분류   
    public static final String BOARD_CODE_CAMPAIGN_RESULT = "1001";     //캠페인결과발표
    public static final String BOARD_CODE_BURSARY_FLAG = "1002";        //장학수기
    public static final String BOARD_CODE_GRATEFUL_LETTER = "1003";     //감사의편지
    public static final String BOARD_CODE_RELEASE_COPY = "1004";        //보도자료
    public static final String BOARD_CODE_MENTORING_NOTICE = "2001";    //멘토링공지사항
    public static final String BOARD_CODE_SCHOLARSHIP_STUDENT = "2002"; //장학생선발공고
    public static final String BOARD_CODE_FOUNDATION_NOTICE = "2003";   //재단공지사항
    public static final String BOARD_CODE_ADMIN_NOTICE = "2004";        //관리자공지사항    
    public static final String BOARD_CODE_ACTIVITY_BOARD = "3001";      //활동게시판
    public static final String BOARD_CODE_PHOTO_NEWS = "3002";          //포토뉴스
    public static final String BOARD_CODE_PRESS_SSF = "4001";           //언론에서본SSF
    public static final String BOARD_CODE_PEOPLE_INTERVIEW = "5001";    //피플인터뷰
    public static final String BOARD_CODE_COMMUNITY_FAQ = "6001";       //커뮤니티 자주묻는질문
    public static final String BOARD_CODE_DONATE_FAQ = "6002";          //기탁안내 자주묻는질문
    public static final String BOARD_CODE_MENTORING_BOARD = "7001";     //멘토링게시판
    public static final String BOARD_CODE_QUESTION_ANSWER = "7002";     //질문과대답
    public static final String BOARD_CODE_TALENT_PEOPLES = "8001";      //인재등록/검색
    public static final String BOARD_CODE_CAMPAIGN_EVENT = "9001";      //캠페인/이벤트
    
    // 학생 구분
    public static final HashMap[] BOARD_STUDENT_TYPE = {
        new HashMap<String,String>() {{put("key", "0"); put("value", "전체");}},
        new HashMap<String,String>() {{put("key", "1"); put("value", "고등학생");}},
        new HashMap<String,String>() {{put("key", "2"); put("value", "대학생");}}
    };
    
    // 가정환경 구분
    public static final HashMap[] BOARD_HOME_ENVIRONMENT = {
        new HashMap<String,String>() {{put("key", "1"); put("value", "기초생활수급권자");}},
        new HashMap<String,String>() {{put("key", "2"); put("value", "저소득가정");}},
        new HashMap<String,String>() {{put("key", "3"); put("value", "한부모가정");}},
        new HashMap<String,String>() {{put("key", "4"); put("value", "조손가정");}},
        new HashMap<String,String>() {{put("key", "5"); put("value", "소년소녀가장");}},
        new HashMap<String,String>() {{put("key", "6"); put("value", "다문화가정");}},
        new HashMap<String,String>() {{put("key", "7"); put("value", "긴급위기가정");}},
    };
    
    static boolean isinit =  false ;
    static private PropertyManager config = null;
    
    public static String SERVER_HOST = "" ;
    public static String HTML_TITLE = "" ;
    
    static {
        
        try
        {
        	
        	if(!isinit){

	            config = PropertyManager.getInstance();
	            SERVER_HOST  = config.getString("server.host");
	            HTML_TITLE   =  config.getString("server.html.title");
	            System.out.println(" ======> SERVER_HOST  : " + SERVER_HOST 		);
	           
        	}    
        
        }catch(Exception e){

        	System.out.println("ConstantUtil Property Loading Error : "+e.toString());

        }
    }
}
