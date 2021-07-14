package lee.common.util.dhxgrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class GridModel implements Serializable, XmlExportable {
	private static final long serialVersionUID = -1686810227320671586L;

	public static final String DEFAULT_ENCODING = "EUC-KR";

	private String fEncoding;
	
	private long fPosition;
	private long fTotalCount;
	
	private boolean fSmartRenderSupported;
	
	private GridHead fHead;
	private GridData fData;
	
	public GridModel() {
		this(DEFAULT_ENCODING, 0, 0, false);
	}
	
	public GridModel(String encoding) {
		this(encoding, 0, 0, false);
	}
	
	public GridModel(String encoding, long position, long totalCount) {
		this(encoding, position, totalCount, true);
	}
	
	public GridModel(long position, long totalCount) {
		this(DEFAULT_ENCODING, position, totalCount);
	}
	
	private GridModel(String encoding, 
						long position, 
						long totalCount, 
						boolean smartRenderSupported) {
		
		fEncoding = encoding;
		
		fPosition = position;
		fTotalCount = totalCount;
		
		fSmartRenderSupported = smartRenderSupported;
		
		fHead = null;
		fData = new GridData();
	}
	
	static void exportChildrens(ArrayList list, StringBuffer sbuf, int depth) {
		Iterator itr = list.iterator();
		
		Object o = null;
		
		while (itr.hasNext()) {
			o = itr.next();
			
			if (!(o instanceof XmlExportable)) {
				continue;
			}
			
			((XmlExportable)o).toXml(sbuf, depth);
		}
	}

	public static boolean hasSpecialCharacter(String value) {
		if (value == null) {
			return false;
		}
		
		return (value.indexOf('<') >= 0)
			 || (value.indexOf('>') >= 0)
			 || (value.indexOf('&') >= 0);
	}

	public GridHead getHead() {
		if (fHead == null) {
			fHead = new GridHead();
		}
		
		return fHead;
	}
	
	public GridData getData() {
		return fData;
	}

	public String getEncoding() {
		return fEncoding;
	}

	public void setEncoding(String encoding) {
		fEncoding = encoding;
	}

	public long getPosition() {
		return fPosition;
	}

	public void setPosition(long position) {
		fPosition = position;
	}

	public long getTotalCount() {
		return fTotalCount;
	}

	public void setTotalCount(long totalCount) {
		fTotalCount = totalCount;
	}

	public boolean isSmartRenderSupported() {
		return fSmartRenderSupported;
	}

	public void setSmartRenderSupported(boolean smartRenderSupported) {
		fSmartRenderSupported = smartRenderSupported;
	}
	
	public void toXml(StringBuffer sbuf, int depth) {
		sbuf.append(toXml());
	}
	
	public String toXml() {
		StringBuffer sbuf = new StringBuffer();
		
		sbuf.append("<?xml version=\"1.0\" encoding=\"").append(fEncoding).append("\"?>\n");
		sbuf.append("<rows");
		
		if (fSmartRenderSupported) {
			sbuf.append(" pos=\"").append(fPosition).append("\"");
			sbuf.append(" total_count=\"").append(fTotalCount).append("\"");
		}
		
		sbuf.append(">\n");
		
		if (fHead != null) {
			sbuf.append("<!-- begin head section -->\n");
			sbuf.append("<head>\n");
			exportChildrens(fHead.getChildren(), sbuf, 1);
			sbuf.append("</head>\n");
			sbuf.append("<!-- end of head section -->\n");
		}
		
		sbuf.append("<!-- begin data section -->\n");
		exportChildrens(fData.getUserData(), sbuf, 0);
		exportChildrens(fData.getRows(), sbuf, 0);
		sbuf.append("<!-- end of data section -->\n");
		
		sbuf.append("</rows>\n");
		
		return sbuf.toString();
	}
}
