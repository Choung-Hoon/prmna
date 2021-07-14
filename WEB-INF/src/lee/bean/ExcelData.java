package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 로드된 엑셀 데이터 
 * 
 * @author mhlee
 */
public class ExcelData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Naming rule : 테이블의 컬럼에는 언더바가 있을 경우, 예로 user_type 컬럼이면 빈즈에서는 userType으로 설정 
	 */
	private int excelId   	 = 0  ;		// 엑셀데이터ID
	private int loadId    	 = 0  ;		// 로드순번
	private String f1        = ""  ;	// 날짜        
	private String f2        = ""  ;    // 업종        
	private String f3        = ""  ;    // 지역        
	private String f4        = ""  ;    // 법인/면허     
	private String f5        = ""  ;    // 자본금       
	private String f6        = ""  ;    // 시평        
	private String f7        = ""  ;    // 좌수        
	private String f8        = ""  ;    // 잔액        
	private String f9        = ""  ;    // 2008 ==> 2013변경      
	private String f10       = ""  ;    // 2009      
	private String f11       = ""  ;    // 2010      
	private String f12       = ""  ;    // 2011      
	private String f13       = ""  ;    // 3년합계      
	private String f14       = ""  ;    // 2012      
	private String f15       = ""  ;    // 양도가       
	private String f16       = ""  ;    // 협회        
	private String f17       = ""  ;    // 기타        
	private String f18       = ""  ;    // 업체        
	private String f19       = ""  ;    // 연락처       
	private String regId     = null  ;  // 등록자ID     
	private String regDate   = null  ;  // 등록일시     
	
	/*2014.02.16 추가 반영*/
	private String fstYr     = ""  ;  // 2008     
	private String scndYr    = ""  ;  // 2009 
	private String sumOf5yr  = ""  ;  // 5년 합계
	
	public int getExcelId() {
		return  excelId;
	}

	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}
	
	public int getLoadId() {
		return loadId;
	}

	public void setLoadId(int loadId) {
		this.loadId = loadId;
	}

	public String getF1() {
		return f1 == null ? "" : f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}
		
	public String getF2() {
		return f2 == null ? "" : f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3 == null ? "" : f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4 == null ? "" : f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	public String getF5() {
		return f5 == null ? "" : f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
	}


	public String getF6() {
		return f6 == null ? "" : f6;
	}

	public void setF6(String f6) {
		this.f6 = f6;
	}

	public String getF7() {
		return f7 == null ? "" : f7;
	}

	public void setF7(String f7) {
		this.f7 = f7;
	}	

	public String getF8() {
		return f8 == null ? "" : f8;
	}

	public void setF8(String f8) {
		this.f8 = f8;
	}


	public String getF9() {
		return f9 == null ? "" : f9;
	}

	public void setF9(String f9) {
		this.f9 = f9;
	}

	public String getF10() {
		return f10 == null ? "" : f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	public String getF11() {
		return f11 == null ? "" : f11;
	}
	
	public void setF11(String f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return f12 == null ? "" : f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	public String getF13() {
		return f13 == null ? "" : f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	public String getF14() {
		return f14 == null ? "" : f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	public String getF15() {
		return f15 == null ? "" : f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	public String getF16() {
		return f16 == null ? "" : f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
	}

	public String getF17() {
		return f17 == null ? "" : f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
	}	

	public String getF18() {
		return f18 == null ? "" : f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
	}

	public String getF19() {
		return f19 == null ? "" : f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
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
	
	public String getFstYr() {
        return fstYr == null ? "" : fstYr;
    }
    
	public void setFstYr(String fstYr) {
        this.fstYr = fstYr;
    }
	
	public String getScndYr() {
        return scndYr == null ? "" : scndYr;
    }
    
	public void setScndYr(String scndYr) {
        this.scndYr = scndYr;
    }
	
	public String getSumOf5yr() {
        return sumOf5yr == null ? "" : sumOf5yr;
    }
    
	public void setSumOf5yr(String sumOf5yr) {
        this.sumOf5yr = sumOf5yr;
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
