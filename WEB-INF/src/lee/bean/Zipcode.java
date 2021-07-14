package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 우편번호
 */
public class Zipcode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String zipcode;
	private String sido;
	private String gubun;
	private String dong;
	private String bunji;
	private String seq;

	public String getZipcode() {
		return zipcode == null ? "" : zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getZipcode1() {
		return getZipcode().substring(0,3);
	}

	public String getZipcode2() {
		return getZipcode().substring(4);
	}

	public String getAddress() {
		return getSido() + " " + getGubun() + " " + getDong() + " " + getBunji();
	}

	public String getSelAddress() {
		return getSido() + " " + getGubun() + " " + getDong();
	}
	
	public String getSido() {
		return sido == null ? "" : sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGubun() {
		return gubun == null ? "" : gubun;		
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getDong() {
		return dong == null ? "" : dong;			
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunji() {
		return bunji == null ? "" : bunji;		
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	public String getSeq() {
		return seq == null ? "" : seq;			
	}

	public void setSeq(String seq) {
		this.seq = seq;
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
