package lee.common.util.dhxgrid;

import java.io.Serializable;

public class Cell implements Serializable, XmlExportable {
	private static final long serialVersionUID = 4987298361357287110L;

	private String fType;
	private String fStyleClass;
	private String fStyle;
	
	private int fColSpan;
	private int fRowSpan;
	
	private String fText;
	
	public Cell() {
		this(null);
	}
	
	public Cell(String text) {
		fText = text;
		
		fColSpan = 0;
		fRowSpan = 0;
	}
	
	public String getType() {
		return fType;
	}

	public void setType(String type) {
		fType = type;
	}

	public String getStyleClass() {
		return fStyleClass;
	}

	public void setStyleClass(String styleClass) {
		fStyleClass = styleClass;
	}

	public String getStyle() {
		return fStyle;
	}

	public void setStyle(String style) {
		fStyle = style;
	}

	public int getColSpan() {
		return fColSpan;
	}

	public void setColSpan(int colSpan) {
		fColSpan = colSpan;
	}

	public int getRowSpan() {
		return fRowSpan;
	}

	public void setRowSpan(int rowSpan) {
		fRowSpan = rowSpan;
	}

	public String getText() {
		return fText;
	}

	public void setText(String text) {
		fText = text;
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent);
		sbuf.append("<cell");
		
		if (fType != null) {
			sbuf.append(" type=\"").append(fType).append("\"");
		}
		
		if (fStyleClass != null) {
			sbuf.append(" class=\"").append(fStyleClass).append("\"");
		}
		
		if (fStyle != null) {
			sbuf.append(" style=\"").append(fStyle).append("\"");
		}
		
		if (fColSpan > 0) {
			sbuf.append(" colspan=\"").append(fColSpan).append("\"");
		}
		
		if (fRowSpan > 0) {
			sbuf.append(" rowspan=\"").append(fRowSpan).append("\"");
		}

		sbuf.append(">");
		
		if (GridModel.hasSpecialCharacter(fText)) {
			sbuf.append("<![CDATA[").append(fText).append("]]>");
		} else {
			sbuf.append(fText);
		}
		
		sbuf.append("</cell>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
