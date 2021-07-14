package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

import lee.common.config.ConstantUtil;
import lee.common.util.DateUtils;

/**
 * 게시판
 */
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;

	private int contId = 0;                    //게시물 ID
	private int bdId = 0;                      //게시판ID
	private int ref;                       //참조
	private String step = null;                //단계
	private int level;                     //등급
	private String cate = null;                //분류    (커뮤니티 자주묻는질문 --> 1:하이서울장학금 고등학교분야, 2:하이서울장학금 대학원분야, 3:기타 장학금 분야)
	private String title = null;               //제목
	private String content = null;             //내용
	private String noticeYn = "N";             //공지글여부(Y:공지글, N:일반글)
	private String showYn = "Y";               //노출여부(Y:노출, N:노출안함)
	private int hitCnt = 0;                    //조회수
	private String linkSrc = null;             //링크출처
	private String linkUrl = null;             //링크URL
	private String ansText = null;             //답변내용
	private String ansId = null;               //답변자ID
	private String ansName = null;             //답변자이름
	private String ansDate = null;             //답변자일시
	private String ansYn = "N";                //답변상태(Y:답변완료, N:미답변)
	private String hrStdType = null;           //인재 학생구분(1.고등학생, 2.대학생)
	private String hrGrade = null;             //인재학년
	private String hrSubject = null;           //인재학과
	private String hrEnv = null;               //인재 가정환경(1:기초생활수급권자, 2:저소득가정, 3:한부모가정, 4:조손가정, 5:소년소녀가장, 6:다문화가정, 7:긴급위기가정, 여러개인 경우 콤마로 구분)',
	private String evtSdate = null;            //이벤트 시작일(형식:YYYYMMDD)
	private String evtEdate = null;            //이벤트 종료일(형식:YYYYMMDD)
	private String evtResultUrl = null;        //이벤트 결과페이지 URL
	private String regId = null;               //등록자ID
	private String regName = null;             //등록자이름
	private String regDate = null;             //등록일시
	private String modId = null;               //최종수정자ID
	private String modDate = null;             //최종수정일시
	private int boardFileCnt = 0;              //첨부파일갯수
	private int boardCommentCnt = 0;           //덧글갯수
	
	private String thumnailRname;              //썸네일 이미지 - 실제 파일명
	private String thumnailCname;              //썸네일 이미지 - 생성 파일명
	private String newYn;                      // 1일 기준 new 여부 
	

	public int getContId() {
        return contId;
    }
    public void setContId(int contId) {
        this.contId = contId;
    }
    public int getBdId() {
        return bdId;
    }
    public void setBdId(int bdId) {
        this.bdId = bdId;
    }
    public int getRef() {
        return ref;
    }
    public void setRef(int ref) {
        this.ref = ref;
    }
    public String getStep() {
        return step == null ? "" : step;
    }
    public void setStep(String step) {
        this.step = step;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getCate() {
        return cate == null ? "" : cate;
    }
    public void setCate(String cate) {
        this.cate = cate;
    }
    public String getTitle() {
        return title == null ? "" : title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content == null ? "" : content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getNoticeYn() {
        return noticeYn == null ? "" : noticeYn;
    }
    public void setNoticeYn(String noticeYn) {
        this.noticeYn = noticeYn;
    }
    public String getShowYn() {
        return showYn == null ? "" : showYn;
    }
    public void setShowYn(String showYn) {
        this.showYn = showYn;
    }
    public int getHitCnt() {
        return hitCnt;
    }
    public void setHitCnt(int hitCnt) {
        this.hitCnt = hitCnt;
    }
    public String getLinkSrc() {
        return linkSrc == null ? "" : linkSrc;
    }
    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }
    public String getLinkUrl() {
        return linkUrl == null ? "" : linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getAnsText() {
        return ansText == null ? "" : ansText;
    }
    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }
    public String getAnsId() {
        return ansId == null ? "" : ansId;
    }
    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }
    public String getAnsName() {
        return ansName == null ? "" : ansName;
    }
    public void setAnsName(String ansName) {
        this.ansName = ansName;
    }
    public String getAnsDate() {
        return ansDate == null ? "" : ansDate;
    }
    public void setAnsDate(String ansDate) {
        this.ansDate = ansDate;
    }
    public String getAnsYn() {
        return ansYn == null ? "" : ansYn;
    }
    public void setAnsYn(String ansYn) {
        this.ansYn = ansYn;
    }
    public String getHrStdType() {
        return hrStdType == null ? "" : hrStdType;
    }
    public void setHrStdType(String hrStdType) {
        this.hrStdType = hrStdType;
    }
    public String getHrGrade() {
        return hrGrade == null ? "" : hrGrade;
    }
    public void setHrGrade(String hrGrade) {
        this.hrGrade = hrGrade;
    }
    public String getHrSubject() {
        return hrSubject == null ? "" : hrSubject;
    }
    public void setHrSubject(String hrSubject) {
        this.hrSubject = hrSubject;
    }
    public String getHrEnv() {
        return hrEnv == null ? "" : hrEnv;
    }
    public void setHrEnv(String hrEnv) {
        this.hrEnv = hrEnv;
    }
    public String getEvtSdate() {
        return evtSdate == null ? "" : evtSdate;
    }
    public void setEvtSdate(String evtSdate) {
        this.evtSdate = evtSdate;
    }
    public String getEvtEdate() {
        return evtEdate == null ? "" : evtEdate;
    }
    public void setEvtEdate(String evtEdate) {
        this.evtEdate = evtEdate;
    }
    public String getEvtResultUrl() {
        return evtResultUrl == null ? "" : evtResultUrl;
    }
    public void setEvtResultUrl(String evtResultUrl) {
        this.evtResultUrl = evtResultUrl;
    }
    public String getRegId() {
        return regId == null ? "" : regId;
    }
    public void setRegId(String regId) {
        this.regId = regId;
    }
    public String getRegName() {
        return regName == null ? "" : regName;
    }
    public void setRegName(String regName) {
        this.regName = regName;
    }
    public String getRegDate() {
        return regDate == null ? "" : regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getModId() {
        return modId == null ? "" : modId;
    }
    public void setModId(String modId) {
        this.modId = modId;
    }
    public String getModDate() {
        return modDate == null ? "" : modDate;
    }
    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
    public int getBoardFileCnt() {
        return boardFileCnt;
    }
    public void setBoardFileCnt(int boardFileCnt) {
        this.boardFileCnt = boardFileCnt;
    }
    public int getBoardCommentCnt() {
        return boardCommentCnt;
    }
    public void setBoardCommentCnt(int boardCommentCnt) {
        this.boardCommentCnt = boardCommentCnt;
    }
    public String getThumnailRname() {
        return thumnailRname == null ? "" : thumnailRname;
    }
    public void setThumnailRname(String thumnailRname) {
        this.thumnailRname = thumnailRname;
    }
    public String getThumnailCname() {
        return thumnailCname == null ? "" : thumnailCname;
    }
    public void setThumnailCname(String thumnailCname) {
        this.thumnailCname = thumnailCname;
    }
    public String getNewYn() {
        return newYn == null ? "N" : newYn;
    }
    public void setNewYn(String newYn) {
        this.newYn = newYn;
    }
    public String toString() {
        Class thisClass = this.getClass();
        StringBuffer buf = new StringBuffer();

        buf.append("<").append(thisClass.getName()).append(">\n");

        try {
            BeanInfo info = Introspector.getBeanInfo(thisClass);
            PropertyDescriptor pd[] = info.getPropertyDescriptors();
            Method method = null;

            for (int i = 0; pd != null && i < pd.length; i++) {
                if (pd[i].getName().equals("class"))
                    continue;
                method = pd[i].getReadMethod();
                if (method != null) {
                    buf.append("\t<").append(pd[i].getName()).append(">");
                    buf.append(method.invoke(this, new Object[0]));
                    buf.append("</").append(pd[i].getName()).append(">\n");
                }
            }

        } catch (Exception e) {
            buf.append(e.getMessage());
        }
        buf.append("</").append(thisClass.getName()).append(">\n");

        return buf.toString();
    }   
    
    public String getCateName(){
        String rtnValue = "";
        
        if(this.cate.equals("1"))
            rtnValue = "하이서울장학금 고등학교분야";
        else if(this.cate.equals("2"))
            rtnValue = "하이서울장학금 대학분야";
        else if(this.cate.equals("3"))
            rtnValue = "하이서울장학금 대학원분야";
        else if(this.cate.equals("4"))
            rtnValue = "기타 장학금 분야";
        
        return rtnValue;
    }
    
    public String getProgressStatusName(){
        String rtnValue = "";
        
        //if( DateUtils.daysBetween(DateUtils.getToday("yyyyMMdd"), this.evtSdate) > 0)
        //    rtnValue = "<span style='color:#0000FF;'>준비중</span>";
        //else 
        if( DateUtils.daysBetween(this.evtEdate, DateUtils.getToday("yyyyMMdd")) > 0)
            rtnValue = "<span style='color:#FF0000;'>마감</span>";
        else 
            rtnValue = "<span>진행중</span>";
        
        return rtnValue;
    }
    
    public String getProgressStatusImg(){
        String rtnValue = "";
        
        if( DateUtils.daysBetween(this.evtEdate, DateUtils.getToday("yyyyMMdd")) > 0)
            rtnValue = "<a href=\"javascript:boardView('" + this.contId + "');\" class=\"board_list_btn2\">마감</a>";
        else 
            rtnValue = "<a href=\"javascript:boardView('" + this.contId + "');\" class=\"board_list_btn1\">진행중</a>";
        
        return rtnValue;
    }
    
    public String getAnswerStatusName(){
        String rtnValue = "";
        
        if( this.ansYn.equals("Y") )
            rtnValue = "<span>답변완료</span>";
        else 
            rtnValue = "<span style='color:#FF0000;'>미답변</span>";
        
        return rtnValue;
    }
    
    public String getHrStdTypeName(){
        String rtnValue = "";
        HashMap[] list = ConstantUtil.BOARD_STUDENT_TYPE;
        
        for(int a=0; a<list.length; a++){
            if(list[a].get("key").equals(this.hrStdType))
                rtnValue = list[a].get("value").toString();        
        }
        
        return rtnValue;
    }
    
    public String getHrEnvName(String separator){
        String rtnValue = "";
        HashMap[] list = ConstantUtil.BOARD_HOME_ENVIRONMENT;
        
        for(int a=0; a<list.length; a++){
            if(this.hrEnv.indexOf(list[a].get("key").toString()) > -1){
                if(rtnValue != "")
                    rtnValue += separator;
                rtnValue += list[a].get("value").toString();        
            }
        }
                
        return rtnValue;
    }
    
    public String getBoardIDName(){
        String rtnValue = "";
        
        switch (this.bdId) {
            case 1001:  rtnValue = "<strong>[캠페인.이벤트 결과발표]</strong>";  break;
            case 1002:  rtnValue = "<strong>[장학수기]</strong>";  break;
            case 1003:  rtnValue = "<strong>[감사의 편지]</strong>";  break;
            case 1004:  rtnValue = "<strong>[보도자료]</strong>";  break;
            case 2001:  rtnValue = "<strong>[희망파티쉐 멘토링공지사항]</strong>";  break;
            case 2002:  rtnValue = "<strong>[장학생 선발 공고]</strong>";  break;
            case 2003:  rtnValue = "<strong>[재단 공지사항]</strong>";  break;
            case 3001:  rtnValue = "<strong>[희망파티쉐 활동게시판]</strong>";  break;
            case 3002:  rtnValue = "<strong>[포토 뉴스]</strong>";  break;
            case 4001:  rtnValue = "<strong>[언론에서 본 SSF]</strong>";  break;
            case 5001:  rtnValue = "<strong>[피플 인터뷰]</strong>";  break;
            case 6001:  rtnValue = "<strong>[자주 묻는 질문]</strong>";  break;
            case 7001:  rtnValue = "<strong>[희망파티쉐 멘토링게시판]</strong>";  break;
            case 7002:  rtnValue = "<strong>[Q&amp;A]</strong>";  break;
            case 8001:  rtnValue = "<strong>[인재검색]</strong>";  break;
            case 9001:  rtnValue = "<strong>[캠페인공지]</strong>";  break;
            default:    break;
        }
        
        return rtnValue;
    }
    
    public String getFrontBoardIDUrl(){
        String rtnValue = "";
        
        switch (this.bdId) {
            case 1001:  rtnValue = "/com/campaign_result_view.jsp";       break;
            case 1002:  rtnValue = "/com/bursary_flag_view.jsp";          break;
            case 1003:  rtnValue = "/com/grateful_letter_view.jsp";       break;
            case 1004:  rtnValue = "/news/release_copy_view.jsp";         break;
            case 2001:  rtnValue = "/ssb/mentoring_notice_view.jsp";      break;
            case 2002:  rtnValue = "/news/scholarship_student_view.jsp";  break;
            case 2003:  rtnValue = "/news/foundation_notice_view.jsp";    break;
            case 3001:  rtnValue = "/ssb/activity_board_view.jsp";        break;
            case 3002:  rtnValue = "/news/photo_news_view.jsp";           break;
            case 4001:  rtnValue = "/news/press_ssf_view.jsp";            break;
            case 5001:  rtnValue = "/news/people_interview_view.jsp";     break;            
            case 6001:  rtnValue = "/com/community_faq_list.jsp";         break;
            case 7001:  rtnValue = "/ssb/mentoring_board_view.jsp";       break;
            case 7002:  rtnValue = "/com/question_answer_view.jsp";       break;
            case 8001:  rtnValue = "/com/talent_peoples_view.jsp";        break;
            case 9001:  rtnValue = "/com/campaign_event_view.jsp";        break;
            default:    break;
        }
        return rtnValue;
    }
}
