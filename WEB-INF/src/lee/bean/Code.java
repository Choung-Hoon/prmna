package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 공통코드 - tb_code
 * 
 * @author mhlee
 */
public class Code implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	-- 공통코드 > 분류코드
	--
	-- SCHOOLE : 초등학교 (8자리)
	-- SCHOOLM : 중학교   (8자리)
	-- SCHOOLH : 고등학교 (8자리)
	-- SCHOOLU : 대학교   (6자리)
	-- SUBJECT : 학과     (6자리)
	*/
	
	private String cateCode;	// 분류코드값
	private String cateName;	// 분류코드명
	private String code;		// 코드값
	private String name;		// 코드명
	private String pcode1;		// 대분류코드값(중/소분류인 경우 사용)
	private String pcode2;		// 중분류코드값(소분류만 사용)
	
	public String getCateCode() {
		return cateCode == null ? "" : cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName == null ? "" : cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCode() {
		return code == null ? "" : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcode1() {
		return pcode1 == null ? "" : pcode1;
	}

	public void setPcode1(String pcode1) {
		this.pcode1 = pcode1;
	}

	public String getPcode2() {
		return pcode2 == null ? "" : pcode2;
	}

	public void setPcode2(String pcode2) {
		this.pcode2 = pcode2;
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
