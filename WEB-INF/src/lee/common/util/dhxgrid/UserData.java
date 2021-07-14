package lee.common.util.dhxgrid;

import java.io.Serializable;

public class UserData implements Serializable, XmlExportable {
	private static final long serialVersionUID = 3194028173804458038L;
	
	private String fName;
	private String fValue;
	
	public UserData(String name, String value) {
		fName = name;
		fValue = value;
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent).append("<userdata name=\"").append(fName).append("\">");
		
		if (GridModel.hasSpecialCharacter(fValue)) {
			sbuf.append("<![CDATA[").append(fValue).append("]]>");
		} else {
			sbuf.append(fValue);
		}

		sbuf.append(indent).append("</userdata>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
