package lee.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 게시판
 */
public class BoardFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private int bfileId;          //게시물파일ID
	private int contId;           //게시물ID
	private String fileType;      //파일구분(1:첨부파일, 2:메인이미지, 3:썸네일이미지, 4:이미지)
	private String rname;          //실제파일명
	private String cname;          //생성파일명
	private String regDate;       //등록일시
	private String descript;           // 설명
	
    public int getBfileId() {
        return bfileId;
    }
    public void setBfileId(int bfileId) {
        this.bfileId = bfileId;
    }
    public int getContId() {
        return contId;
    }
    public void setContId(int contId) {
        this.contId = contId;
    }
    public String getFileType() {
        return fileType == null ? "" : fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getRname() {
        return rname == null ? "" : rname;
    }
    public void setRname(String rname) {
        this.rname = rname;
    }
    public String getCname() {
        return cname == null ? "" : cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getRegDate() {
        return regDate == null ? "" : regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getDescript() {
        return descript == null ? "" : descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
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
