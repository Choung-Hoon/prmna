package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 게시판
 */
public class BoardComment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int bcmtId;           //댓글ID
	private int contId;           //게시물ID	
	private String cmt;            //댓글내용
	private String regId;         //등록자ID
	private String regName;       //등록자이름
	private String regDate;       //등록일시
	
	
    public int getBcmtId() {
        return bcmtId;
    }
    public void setBcmtId(int bcmtId) {
        this.bcmtId = bcmtId;
    }
    public int getContId() {
        return contId;
    }
    public void setContId(int contId) {
        this.contId = contId;
    }
    public String getCmt() {
        return cmt == null ? "" : cmt;
    }
    public void setCmt(String cmt) {
        this.cmt = cmt;
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
