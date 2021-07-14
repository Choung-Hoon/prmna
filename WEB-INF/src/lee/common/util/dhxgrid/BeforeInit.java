package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;

public class BeforeInit implements Serializable, XmlExportable {
	private static final long serialVersionUID = 2413149914906577783L;

	private ArrayList fCommands;
	
	public BeforeInit() {
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
		sbuf.append("<beforeInit>\n");
		
		GridModel.exportChildrens(fCommands, sbuf, depth + 1);
		
		sbuf.append(indent);
		sbuf.append("</beforeInit>\n");
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		toXml(sbuf, 0);
		
		return sbuf.toString();
	}
}
