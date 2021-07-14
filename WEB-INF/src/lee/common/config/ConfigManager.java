package lee.common.config;

import java.io.File;
import lee.common.config.*;

/**
 * mdweb.properties 설정정보
 */
public class ConfigManager {

	private static ConfigManager config;

	private String fileUpload = "";
	private String fileTemp = "";
	private String configBase = "";
	

	private ConfigManager() throws ConfigException {
		try {
			init();
		} catch (Exception e) {
			throw new ConfigException(e.getMessage(), e);
		}
	}

	/**
	 * single instance
	 * 
	 * @return ConfigManager ConfigManager instance
	 * @exception ConfigException
	 *                에러 발생시
	 */
	public static ConfigManager getInstance() throws ConfigException {
		if (config == null) {
			synchronized (ConfigManager.class) {
				// perform double-check to avoid race condition
				if (config == null) {
					config = new ConfigManager();
				}
			}
		}
		return config;
	}

	public void init() throws ConfigException {
		try {
			fileUpload = PropertyManager.getInstance().getString("file.upload");
			fileTemp = PropertyManager.getInstance().getString("file.temp");
			configBase = PropertyManager.getInstance().getString("config.base");
		} catch (Exception e) {
			System.out.println("Failed to initialize mdweb.properties!");
			e.printStackTrace();
		}
	}

	public String getFileUpload() {
		return this.fileUpload;
	}

	public String getFileTemp() {
		return this.fileTemp;
	}

	public String getConfigBase() {
		return this.configBase;
	}

	public String getPopupFileUpload() {
		return this.fileUpload + File.separator + "popup";
	}

	public String getBannerFileUpload() {
		return this.fileUpload + File.separator + "banner";
	}

	public String getProductFileUpload() {
		return this.fileUpload + File.separator + "product";
	}

	public String getBoardFileUpload() {
		return this.fileUpload + File.separator + "board";
	}
	
	public String getScholarFileUpload() {
		return this.fileUpload + File.separator + "scholar";
	}	

	public String getDocFileUpload() {
		return this.fileUpload + File.separator + "doc";
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(this.getClass().getName() + " : \n");
		buf.append("  fileUpload   : " + this.fileUpload + "\n");
		buf.append("  fileTemp     : " + this.fileTemp + "\n");
		buf.append("  configBase   : " + this.configBase + "\n");

		return buf.toString();
	}

	/**
	 * For Simple Test
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) throws ConfigException {
		System.out.println(ConfigManager.getInstance());
	}
}
