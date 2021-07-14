package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class Column implements Serializable, XmlExportable {
	private static final long serialVersionUID = 5184797242569246374L;
	
	public static final String COLTYPE_READONLY = "ro";
	public static final String COLTYPE_TEXTFIELD = "ed";
	public static final String COLTYPE_NUMBERFIELD = "edn";
	public static final String COLTYPE_TEXTAREA = "txt";
	public static final String COLTYPE_CHECKBOX = "ch";
	public static final String COLTYPE_RADIO = "ra";
	public static final String COLTYPE_SELECTBOX = "coro";
	public static final String COLTYPE_COMBOBOX = "co";
	public static final String COLTYPE_IMAGE = "img";
	public static final String COLTYPE_CALENDAR = "calendar";
	public static final String COLTYPE_READONLY_NUMBER = "ednro";
	
	public static final String COLALIGN_LEFT = "left";
	public static final String COLALIGN_CENTER = "center";
	public static final String COLALIGN_RIGHT = "right";
	
	public static final String COLSORT_NA = "na";
	public static final String COLSORT_STRING = "str";
	public static final String COLSORT_NUMBER = "int";
	public static final String COLSORT_DATE = "date";
	
	public static final int COLWIDTH_FILL = Integer.MIN_VALUE;
	
	private String fId;
	private String fType;
	private String fAlign;
	private String fSortType;
	private String fColor;
	private String fFormat;
	
	private int fWidth;
	private boolean fHidden;
	
	private String fLabel;
	
	private ArrayList fChildren;

	public Column() {
		this(null, null, null, COLWIDTH_FILL, null, null, null, null, false);
	}

	public Column(
				String id, 
				String label, 
				String type, 
				int width, 
				String align, 
				String sortType, 
				String color,
				String format,
				boolean hidden) {
		
		fId = id;
		fLabel = label;
		fType = type;
		fWidth = width;
		fAlign = align;
		fSortType = sortType;
		fColor = color;
		fFormat = format;
		fHidden = hidden;
		
		fChildren = new ArrayList();
	}
	
	public String getId() {
		return fId;
	}

	public void setId(String id) {
		fId = id;
	}

	public String getType() {
		return fType;
	}

	public void setType(String type) {
		fType = type;
	}

	public String getAlign() {
		return fAlign;
	}

	public void setAlign(String align) {
		fAlign = align;
	}

	public String getSortType() {
		return fSortType;
	}

	public void setSortType(String sortType) {
		fSortType = sortType;
	}

	public String getColor() {
		return fColor;
	}

	public void setColor(String color) {
		fColor = color;
	}

	public String getFormat() {
		return fFormat;
	}

	public void setFormat(String format) {
		if (!COLTYPE_NUMBERFIELD.equals(getType())
				&& !COLTYPE_READONLY_NUMBER.equals(getType())
				&& !COLTYPE_CALENDAR.equals(getType())) {
			throw new IllegalArgumentException(
						  "This feature is only available for "
						+ "COLTYPE_NUMBERFIELD, COLTYPE_READONLY_NUMBER "
						+ "and COLTYPE_CALENDAR.");
		}
		
		fFormat = format;
	}

	public int getWidth() {
		return fWidth;
	}

	public void setWidth(int width) {
		fWidth = width;
	}

	public boolean isHidden() {
		return fHidden;
	}

	public void setHidden(boolean hidden) {
		fHidden = hidden;
	}

	public String getLabel() {
		return fLabel;
	}

	public void setLabel(String label) {
		fLabel = label;
	}

	public void addOption(String value, String label) {
		if (!COLTYPE_COMBOBOX.equals(getType()) && !COLTYPE_SELECTBOX.equals(getType())) {
			throw new IllegalArgumentException(
						  "This feature is only available for "
						+ "COLTYPE_COMBOBOX and COLTYPE_SELECTBOX.");
		}
		
		fChildren.add(new Option(value, label));
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent);
		sbuf.append("<column");
		
		if (fId != null) {
			sbuf.append(" id=\"").append(fId).append("\"");
		}

		if (fType != null) {
			sbuf.append(" type=\"").append(fType).append("\"");
		}
		
		if (fAlign != null) {
			sbuf.append(" align=\"").append(fAlign).append("\"");
		}
		
		if (fSortType != null) {
			sbuf.append(" sort=\"").append(fSortType).append("\"");
		}
		
		if (fColor != null) {
			sbuf.append(" color=\"").append(fColor).append("\"");
		}
		
		if (fFormat != null) {
			sbuf.append(" format=\"").append(fFormat).append("\"");
		}
		
		if (fWidth != Integer.MIN_VALUE) {
			sbuf.append(" width=\"").append(fWidth).append("\"");
		} else {
			sbuf.append(" width=\"").append("*\"");
		}
		
		if (fHidden) {
			sbuf.append(" hidden=\"").append(fHidden).append("\"");
		}
		
		sbuf.append(">\n");

		if (GridModel.hasSpecialCharacter(fLabel)) {
			sbuf.append(indent).append("    ").append("<![CDATA[").append(fLabel).append("]]>\n");
		} else {
			sbuf.append(indent).append("    ").append(fLabel).append("\n");
		}
		
		GridModel.exportChildrens(fChildren, sbuf, depth + 1);
		
		sbuf.append(indent).append("</column>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}