package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class AfterInit implements Serializable, XmlExportable {
	private static final long serialVersionUID = 2367264913656891455L;
	
	private ArrayList fCommands;
	
	public AfterInit() {
		fCommands = new ArrayList();
	}
	
	public void addCommand(String command, String params) {
		fCommands.add(new Command(command, params));
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		String indent = "";
		
		for (int i = 0; i < depth * 4; i++) {
			indent += " ";
		}
		
		sbuf.append(indent);
		sbuf.append("<afterInit>\n");
		
		GridModel.exportChildrens(fCommands, sbuf, depth + 1);
		
		sbuf.append(indent);
		sbuf.append("</afterInit>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
