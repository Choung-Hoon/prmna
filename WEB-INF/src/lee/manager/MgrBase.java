/*
 * @(#)MgrBase.java
 */
package lee.manager;

import lee.common.config.ConfigManager;
import lee.common.sqlMap.SqlMapBase;

import org.apache.log4j.Logger;

/**
 * Manager Base class
 * 
 * @author sjju
 * @version 1.0
 */
public class MgrBase extends SqlMapBase {

	/*
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	protected final boolean isDebug = log.isDebugEnabled(); 
	*/
	
	protected Logger log = Logger.getLogger(this.getClass());
	protected final boolean isDebug = log.isDebugEnabled();
	
	protected ConfigManager config = null;

	
	public MgrBase() {
		try {
			config = ConfigManager.getInstance();
		} catch (Exception e) {
			this.log.error("Fail to initialize Manager! Error msg : "
					+ e.getMessage());
		}
	}
}
