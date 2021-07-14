package lee.common.servlet;

import javax.servlet.http.*;

/**
 * Parameter Check Wrapper class
 *
 * @author infocert1@samsung.com)
 * @date : 2009/12/14
 */
public class ParameterWrapper extends HttpServletRequestWrapper {

	public ParameterWrapper(HttpServletRequest request) {
		super(request);
	}

	// getParameter method override
	public String getParameter(String name) {
		// in case of 'NUMBER-ONLY' parameter
		//System.out.println("====== getParameter Debug Message ======");
		//System.out.println("parameter name : "+name);
		/*
		if (name.equals("seq")||name.equals("num")||name.equals("idx")) {
			return numberCheck(super.getParameter(name));
		}else {
			return stringCheck(super.getParameter(name));
		}
		*/
		return stringCheck(super.getParameter(name));
	}

	// String Value Validation Check
	private String stringCheck(String str) {
	  if(str == null) return str;

		//System.out.println("====== stringCheck Debug Message ======");
		//System.out.println("before : "+str);

		str = str.replaceAll("'", "''");
		str = str.replaceAll("--", "__");
		str = str.replaceAll(";", "|");
		//str = str.replaceAll("%", "");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");

		//System.out.println("after : "+str);
		return str;
	}

	// Number Value Validation Check
	private String numberCheck(String str) {
		boolean numonly = true;
		if (str == null || str.equals(""))
			return "";

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 48 || c > 59) {
				numonly = false;
				break;
			}
		}

		//System.out.println("====== numberCheck Debug Message ======");
		//System.out.println("input value : "+str);

		if (numonly) {
			//System.out.println("NUMBER ONLY OK!");
			return str;
		}else {
			//System.out.println("IT IS NOT NUMBER!");
			return "";
		}
	}

}