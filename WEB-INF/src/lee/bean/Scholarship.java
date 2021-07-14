package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

import lee.common.util.StringUtil;

/**
 * 장학사업 - tb_scholarship
 * 
 * @author mhlee
 */
public class Scholarship implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	-- 장학사업 > 장학금구분
	--
	-- 1 : 하이서울고등학교장학금
	-- 2 : 대학원장학금
	-- 3 : 자립형사립고장학금
	-- 4 : 희망파티쉐장학금
	-- 5 : 우리아이희망장학금
	-- 6 : 푸른꿈희망장학금
	-- 7 : 씨티-코스비여성CEO장학금
	*/
	private int scId;			// 장학사업ID
	private int scType;			// 장학금구분
	private String scYear;		// 해당년도(형식:YYYY)
	private int scTime;			// 회차
	private String accSdate;	// 접수시작일(형식:YYYYMMDD)
	private String accEdate;	// 접수종료일(형식:YYYYMMDD)
	private int allocCnt;		// 총배정인원수
	private int reqCnt;			// 총신청인원수
	private String delYn;		// 삭제여부(Y:삭제, N:미삭제)
	private String regId;		// 등록자ID
	private String regDate;		// 등록일시
	private String modId;		// 최종수정자ID
	private String modDate;		// 최종수정일시
	
	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public int getScType() {
		return scType;
	}

	public void setScType(int scType) {
		this.scType = scType;
	}

	public String getScYear() {
		return scYear == null ? "" : scYear;
	}

	public void setScYear(String scYear) {
		this.scYear = scYear;
	}

	public int getScTime() {
		return scTime;
	}

	public void setScTime(int scTime) {
		this.scTime = scTime;
	}

	public String getAccSdate() {
		return accSdate == null ? "" : accSdate;
	}

	public void setAccSdate(String accSdate) {
		this.accSdate = accSdate;
	}

	public String getAccEdate() {
		return accEdate == null ? "" : accEdate;
	}

	public void setAccEdate(String accEdate) {
		this.accEdate = accEdate;
	}

	public int getAllocCnt() {
		return allocCnt;
	}

	public void setAllocCnt(int allocCnt) {
		this.allocCnt = allocCnt;
	}

	public int getReqCnt() {
		return reqCnt;
	}

	public void setReqCnt(int reqCnt) {
		this.reqCnt = reqCnt;
	}

	public String getDelYn() {
		return delYn == null ? "" : delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegId() {
		return regId == null ? "" : regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
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
	
	public String getScTypeName() {
		
		String result = "";
		
		int scType = this.getScType();
		if( scType == 1 ) result = "하이서울고등학교장학금";
		else if( scType == 2 ) result = "대학원장학금";
		else if( scType == 3 ) result = "자립형사립고장학금";
		else if( scType == 4 ) result = "희망파티쉐장학금";
		else if( scType == 5 ) result = "우리아이희망장학금";
		else if( scType == 6 ) result = "푸른꿈희망장학금";
		else if( scType == 7 ) result = "씨티-코스비여성CEO장학금";
		
		return result;
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
	
}
