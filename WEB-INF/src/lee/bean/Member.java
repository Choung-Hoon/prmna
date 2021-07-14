package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 사용자
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

    
	private String userid;         // 아이디
	private String userpw;         // 비밀번호(암호화처리)
	private String userType;       // 회원구분(1:학생회원, 2:일반회원)
	private String name;           // 이름
	private String jumin;          // 주민번호/외국인등록번호
	private String birthday;       // 생년월일(형식:YYYYMMDD)
	private String solar;          // 양력/음력구분(1:양력, 2:음력)
	private String sex;            // 성별구분(1:남, 2:여)
	private String zipcode;        // 우편번호
	private String address1;       // 기본주소
	private String address2;       // 상세주소
	private String phone;          // 전화번호
	private String mobile;         // 휴대폰번호
	private String email;          // 이메일
	private String emailYn;        // 이메일수신동의여부(Y:예, N:아니오)
	private String smsYn;          // SMS수신동의여부(Y:예, N:아니오)
	private String newsYn;         // 뉴스레터수신동의여부(Y:예, N:아니오)
	private String joinType;       // 인증구분(1:실명인증, 2:공공아이핀인증)
	private String nation;         // 내/외국인구분(1:내국인, 2:외국인)
	private String ipin;           // 아이핀번호
	private String retireYn;       // 탈퇴여부(Y:탈퇴, N:미탈퇴)
    private String retireDate;     // 탈퇴일자(형식:YYYYMMDD)
	private String delYn;          // 삭제여부(Y:삭제, N:미삭제)
	private String regDate;        // 등록일시
	private String modDate;        // 최종수정일시
	
	
	
	  /*
	  public String getAuthName() {
	    String val = this.getAuth();
	    String result = "";
	    if(val.equals("1")) {
	       result = "일반사용자";
	    } else if(val.equals("2")) {
	      result = "임원";
	    } else if(val.equals("3")) {
	      result = "관리자";
	    }
	    return result;
	  }
	*/

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
    public String getJumin() {
        return jumin == null ? "" : jumin;
    }
    public void setJumin(String jumin) {
        this.jumin = jumin;
    }
    public String getBirthday() {
        return birthday == null ? "" : birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getSolar() {
        return solar == null ? "" : solar;
    }
    public void setSolar(String solar) {
        this.solar = solar;
    }
    public String getSex() {
        return sex == null ? "" : sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getZipcode() {
        return zipcode == null ? "" : zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getAddress1() {
        return address1 == null ? "" : address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2 == null ? "" : address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getPhone() {
        return phone == null ? "" : phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobile() {
        return mobile == null ? "" : mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email == null ? "" : email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmailYn() {
        return emailYn == null ? "" : emailYn;
    }
    public void setEmailYn(String emailYn) {
        this.emailYn = emailYn;
    }
    public String getSmsYn() {
        return smsYn == null ? "" : smsYn;
    }
    public void setSmsYn(String smsYn) {
        this.smsYn = smsYn;
    }
    public String getNewsYn() {
        return newsYn == null ? "" : newsYn;
    }
    public void setNewsYn(String newsYn) {
        this.newsYn = newsYn;
    }
    public String getJoinType() {
        return joinType == null ? "" : joinType;
    }
    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }
    public String getNation() {
        return nation == null ? "" : nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getIpin() {
        return ipin == null ? "" : ipin;
    }
    public void setIpin(String ipin) {
        this.ipin = ipin;
    }
    public String getRetireYn() {
        return retireYn == null ? "" : retireYn;
    }
    public void setRetireYn(String retireYn) {
        this.retireYn = retireYn;
    }
    public String getRetireDate() {
        return retireDate == null ? "" : retireDate;
    }
    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }
    public String getDelYn() {
        return delYn == null ? "" : delYn;
    }
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
    public String getRegDate() {
        return regDate == null ? "" : regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getModDate() {
        return modDate == null ? "" : modDate;
    }
    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
    
    
    // by mhlee. 2011/09/02 *********************************************************
    // [CAUTION] 장학사업 관리에서 사용함
	public String getSolarName() {
		
		String result = "";
		
		String solar = this.getSolar();
		if( solar.equals("1") ) result = "양력";
		else if( solar.equals("2") ) result = "음력";
		
		return result;
	}     
    
	public String getSexName() {
		
		String result = "";
		
		String sex = this.getSex();
		if( sex.equals("1") ) result = "남";
		else if( sex.equals("2") ) result = "여";
		
		return result;
	}       
	
	public String getUserTypeName() {
		
		String result = "";
		
		String userType = this.getUserType();
		if( userType.equals("1") ) result = "학생회원";
		else if( userType.equals("2") ) result = "일반회원";
		
		return result;
	}       
	
	public String getEmailYnName() {
		
		String result = "";
		
		String emailYn = this.getEmailYn();
		if( emailYn.equals("Y") ) result = "예";
		else if( emailYn.equals("N") ) result = "아니오";
		
		return result;
	}  	

	public String getSmsYnName() {
		
		String result = "";
		
		String smsYn = this.getSmsYn();
		if( smsYn.equals("Y") ) result = "예";
		else if( smsYn.equals("N") ) result = "아니오";
		
		return result;
	}  
	
	public String getNewsYnName() {
		
		String result = "";
		
		String newsYn = this.getNewsYn();
		if( newsYn.equals("Y") ) result = "예";
		else if( newsYn.equals("N") ) result = "아니오";
		
		return result;
	} 
	// *********************************************************************************
}
