package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 관리자(재단/학교/구청)
 * 
 * @author mhlee
 */
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Naming rule : 테이블의 컬럼에는 언더바가 있을 경우, 예로 user_type 컬럼이면 빈즈에서는 userType으로 설정 
	 */
	private String userid;		// 아이디
	private String userpw;		// 비밀번호(암호화처리)
	private String userType;	// 관리자구분(1:관리자(슈퍼), 2:관리자(일반), 3:정액회원, 4:일반회원)
	private String name;		// 이름
	private String dept;		// 부서
	private String phone;		// 연락처
	private String email;		// 이메일
	private String locCd;		// 구청별 학교지역코드
	private String orgCd;		// 기관코드(학교코드)
	private String orgName;		// 기관명(학교명/구청명)
	private String auth;		// 권한(1:장학관리사업, 2:게시판관리, 3:회원관리, 4:사이트관리, 5:운영관리, 6:통계관리, 여러개인 경우 콤마로 구분)
	private String regId;		// 등록자ID
	private String regDate;		// 등록일시
	private String modId;		// 최종수정자ID
	private String modDate;		// 최종수정일시
	private String accessDate;	// 최근 접속일시(로그아웃)
	private String ipList;		// 접속허용 IP 목록
		
	private String validStDate;	// 유효시작일
	private String validEndDate;// 유효종료일
	
	/*2014.03.01 추가 함*/
	private String cmpnNm;		// 회사명
	private String cmpnUrl;		// url
	private String cmpnAddr;	// 주소
	private String cmpnZip;		// 우편번호
	private String cmpnFax;		// 팩스
	private String cmpnTel;		// 전화	
	private String userPhone;	// 핸드폰번호
		
	public String getUserid() {
		return userid == null ? "" : userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw == null ? "" : userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUserType() {
		return userType == null ? "" : userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept == null ? "" : dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPhone() {
		return phone == null ? "" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email == null ? "" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgCd() {
		return orgCd == null ? "" : orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgName() {
		return orgName == null ? "" : orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAuth() {
		return auth == null ? "" : auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getRegId() {
		return regId == null ? "" : regId;
	}
	
	public String getValidStDate() {
		return validStDate == null ? "" : validStDate;
	}
	
	public String getValidEndDate() {
		return validEndDate == null ? "" : validEndDate;
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
	
	public String getAccessDate() {
		return accessDate == null ? "" : accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}	

	public String getLocCd() {
		return locCd == null ? "" : locCd;
	}

	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}

	public String getIpList() {
		return ipList == null ? "" : ipList;
	}

	public void setIpList(String ipList) {
		this.ipList = ipList;
	}
	
	public String setValidStDate(String validStDate) {
		return this.validStDate = validStDate;
	}
	
	public String setValidEndDate(String validEndDate) {
		return this.validEndDate = validEndDate;
	}
	
	/*2014.03.01 6개 필드 추가함*/
	public String getCmpnNm() {
		return cmpnNm == null ? "" : cmpnNm;
	}

	public void setCmpnNm(String cmpnNm) {
		this.cmpnNm = cmpnNm;
	}

	public String getCmpnUrl() {
		return cmpnUrl == null ? "" : cmpnUrl;
	}

	public void setCmpnUrl(String cmpnUrl) {
		this.cmpnUrl = cmpnUrl;
	}

	public String getCmpnAddr() {
		return cmpnAddr == null ? "" : cmpnAddr;
	}

	public void setCmpnAddr(String cmpnAddr) {
		this.cmpnAddr = cmpnAddr;
	}

	public String getCmpnZip() {
		return cmpnZip == null ? "" : cmpnZip;
	}

	public void setCmpnZip(String cmpnZip) {
		this.cmpnZip = cmpnZip;
	}

	public String getCmpnFax() {
		return cmpnFax == null ? "" : cmpnFax;
	}

	public void setCmpnFax(String cmpnFax) {
		this.cmpnFax = cmpnFax;
	}

	public String getCmpnTel() {
		return cmpnTel == null ? "" : cmpnTel;
	}
	
	public void setCmpnTel(String cmpnTel) {
		this.cmpnTel = cmpnTel;
	}
	
	public String getUserPhone() {
		return userPhone == null ? "" : userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	/** tb_code 테이블에 없고 하드코딩해야 할 경우에 쓰는 샘플 메소드 */
	public String getUserTypeName() {
		String val = this.getUserType();
		String result = "";
		
		if(val.equals("1")) {
		   result = "관리자";
		} else if(val.equals("3")) {
		  result = "필드보임";
		} else if(val.equals("4")) {
		  result = "필드숨김";
		}
		
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
