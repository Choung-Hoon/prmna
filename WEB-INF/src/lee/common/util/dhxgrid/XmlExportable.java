package lee.common.util.dhxgrid;

public interface XmlExportable {
	public String toXml();
	public void toXml(StringBuffer sbuf, int depth);
}
