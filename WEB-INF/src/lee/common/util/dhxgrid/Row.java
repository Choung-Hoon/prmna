package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable, XmlExportable {
	private static final long serialVersionUID = 7497270429399327107L;

	private String fId;
	private String fBackgroundColor;
	private String fStyleClass;
	private String fStyle;
	
	private boolean fLocked;
	private boolean fSelected;
	
	private ArrayList fCells;
	private ArrayList fUserData;

	public Row(String id) {
		fId = id;
		fCells = new ArrayList();
		fUserData = new ArrayList();
	}
	
	public void addUserData(String name, String value) {
		fUserData.add(new UserData(name, value));
	}
	
	public Cell addCell(String text) {
		Cell cell = new Cell(text);
		
		fCells.add(cell);
		
		return cell;
	}
	
	public ArrayList getCells() {
		return fCells;
	}
	
	public Cell getCellAt(int index) {
		return (Cell)fCells.get(index);
	}
	
	public int getCellCount() {
		return fCells.size();
	}
	
	public String getId() {
		return fId;
	}

	public void setId(String id) {
		fId = id;
	}

	public String getBackgroundColor() {
		return fBackgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		fBackgroundColor = backgroundColor;
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

	public boolean isLocked() {
		return fLocked;
	}

	public void setLocked(boolean locked) {
		fLocked = locked;
	}

	public boolean isSelected() {
		return fSelected;
	}

	public void setSelected(boolean selected) {
		fSelected = selected;
	}

	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent);
		sbuf.append("<row");
		
		if (fId != null) {
			sbuf.append(" id=\"").append(fId).append("\"");
		}
		
		if (fBackgroundColor != null) {
			sbuf.append(" bgColor=\"").append(fBackgroundColor).append("\"");
		}
		
		if (fStyleClass != null) {
			sbuf.append(" class=\"").append(fStyleClass).append("\"");
		}
		
		if (fStyle != null) {
			sbuf.append(" style=\"").append(fStyle).append("\"");
		}
		
		if (fLocked) {
			sbuf.append(" locked=\"true\"");
		}
		
		if (fSelected) {
			sbuf.append(" selected=\"true\"");
		}
		
		sbuf.append(">\n");
		
		GridModel.exportChildrens(fUserData, sbuf, depth + 1);
		GridModel.exportChildrens(fCells, sbuf, depth + 1);
		
		sbuf.append(indent).append("</row>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
