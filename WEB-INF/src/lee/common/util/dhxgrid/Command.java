package lee.common.util.dhxgrid;

import java.io.Serializable;

public class Command implements Serializable, XmlExportable {
	private static final long serialVersionUID = -6838018879903049759L;

	private String fCommand;
	private String fParams;
	
	public Command(String command, String params) {
		fCommand = command;
		fParams = params;
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent).append("<call command=\"").append(fCommand).append("\">\n");
		
		if (fParams != null) {
			sbuf.append(indent).append("    <param>");
			
			if (GridModel.hasSpecialCharacter(fParams)) {
				sbuf.append("<![CDATA[").append(fParams).append("]]>");
			} else {
				sbuf.append(fParams);
			}
			
			sbuf.append("</param>\n");
		}
		
		sbuf.append(indent).append("</call>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
