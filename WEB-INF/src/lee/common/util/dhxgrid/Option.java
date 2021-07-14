package lee.common.util.dhxgrid;

import java.io.Serializable;

public class Option implements Serializable, XmlExportable {
	private static final long serialVersionUID = -1614260648715825864L;
	
	private String fValue = null;
	private String fLabel = null;

	public Option(String value, String label) {
		fValue = value;
		fLabel = label;
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent);
		sbuf.append("<option value=\"").append(fValue).append("\">");
		sbuf.append(fLabel);
		sbuf.append("</option>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}