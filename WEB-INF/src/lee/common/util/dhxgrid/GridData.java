package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class GridData implements Serializable {
	private static final long serialVersionUID = 767532217446641832L;

	private ArrayList fRows;
	private ArrayList fUserData;
	
	public GridData() {
		fRows = new ArrayList();
		fUserData = new ArrayList();
	}
	
	public ArrayList getRows() {
		return fRows;
	}
	
	public Row getRowAt(int index) {
		return (Row)fRows.get(index);
	}
	
	public int getRowCount() {
		return fRows.size();
	}
	
	public ArrayList getUserData() {
		return fUserData;
	}
	
	public void addUserData(String name, String value) {
		fUserData.add(new UserData(name, value));
	}
	
	public Row addRow(String rowId) {
		Row row = new Row(rowId);
		
		fRows.add(row);
		
		return row;
	}
}
