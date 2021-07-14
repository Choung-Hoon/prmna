package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class GridHead implements Serializable {
	private static final long serialVersionUID = -6593880227612513883L;
	
	private ArrayList fChildren;
	private BeforeInit fBeforeInitCommands;
	private AfterInit fAfterInitCommands;
	
	public GridHead() {
		fChildren = new ArrayList();
	}
	
	public ArrayList getChildren() {
		return fChildren;
	}
	
	public Column addColumn(String id, String label, String type, int width) {
		return addColumn(id, label, type, width, null, null, false);
	}

	public Column addColumn(
						String id, 
						String label, 
						String type, 
						int width, 
						String align, 
						String sortType) {
		
		return addColumn(id, label, type, width, align, sortType, false);
	}

	public Column addColumn(
						String id, 
						String label, 
						String type, 
						int width, 
						String align, 
						String sortType,
						boolean hidden) {

		return addColumn(id, label, type, width, align, sortType, null, null, hidden);
	}

	public Column addColumn(
						String id, 
						String label, 
						String type, 
						int width, 
						String align, 
						String sortType,
						String color,
						String format,
						boolean hidden) {

		Column col = new Column(id, 
								label, 
								type, 
								width, 
								align, 
								sortType, 
								color, 
								format, 
								hidden);
		
		fChildren.add(col);
		
		return col;
	}
	
	public void addBeforeInitCommand(String command, String params) {
		if (fBeforeInitCommands == null) {
			fBeforeInitCommands = new BeforeInit();
			fChildren.add(fBeforeInitCommands);
		}
		
		fBeforeInitCommands.addCommand(command, params);
	}
	
	public void addAfterInitCommand(String command, String params) {
		if (fAfterInitCommands == null) {
			fAfterInitCommands = new AfterInit();
			fChildren.add(fAfterInitCommands);
		}
		
		fAfterInitCommands.addCommand(command, params);
	}
}
