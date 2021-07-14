package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 게시판
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private int 	productId      	; // 물건ID
	private String 	jobKind			; // 업종
	private String 	companyNm		; // 회사명
	private String 	handoverCond   	; // 양도조건
	private String 	contents		; // 내용
	private String regId = null;               //등록자ID
	private String regName = null;             //등록자이름
	private String regDate = null;             //등록일시
	
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getJobKind() {
        return jobKind == null ? "" : jobKind;
    }
    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }
    
    public String getCompanyNm() {
        return companyNm == null ? "" : companyNm;
    }
    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }
    
    public String getHandoverCond() {
        return handoverCond == null ? "" : handoverCond;
    }
    public void setHandoverCond(String handoverCond) {
        this.handoverCond = handoverCond;
    }
    
    public String getContents() {
        return contents == null ? "" : contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
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
