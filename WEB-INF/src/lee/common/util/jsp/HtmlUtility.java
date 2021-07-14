package lee.common.util.jsp;

import java.lang.reflect.*;
import java.util.Enumeration;
import java.util.Vector;
 
/**
 * @(#)
 * Copyright 1999-2000 by  LG-EDS Systems, Inc.,
 * Information Technology Group, Application Architecture Team, 
 * Application Intrastructure Part.
 * 236-1, Hyosung-2dong, Kyeyang-gu, Inchun, 407-042, KOREA.
 * All rights reserved.
 * 
 * NOTICE !      You can copy or redistribute this code freely, 
 * but you should not remove the information about the copyright notice 
 * and the author.
 * 
 * @author  WonYoung Lee, wyounglee@lgeds.lg.co.kr.
 */
public class HtmlUtility {
	/**
	 * You can't call the constructor.
	 */
	private HtmlUtility() {}
	/**
	 * Translates special strings into special HTML tag format.
	 *
	 * <xmp>
	 *
	 * & --> &amp;
	 * < --> &lt;
	 * > --> &gt;
	 * " --> &quot;
	 * ' --> &#039;
	 *-----------------------------------------------------------------
	 * <option type=radio  name=r value="xxxxxxxx"> yyyyyyy
	 * <input  type=hidden name=h value="xxxxxxxx">
	 * <input  type=text   name=t value="xxxxxxxx">
	 * <textarea name=msg rows=20 cols=53>xxxxxxx</textarea>
	 *-
	 * 위와 같은 HTML 소스를 생성할 때, xxxxxxx 부분의 문자열 중에서
	 * 아래에 있는 몇가지 특별한 문자들을 변환하여야 합니다.
	 * 만약 JSP 라면 미리 변환하여 HTML 전체 TAG를 만들거나, 혹은
	 * 아래처럼 사용하세요.
	 *-
	 * <option type=radio  name=r value="<%= HtmlUtility.translate(s) %>"> yyyyyyy
	 * <input  type=hidden name=n value="<%= HtmlUtility.translate(s) %>">
	 * <input  type=text   name=n value="<%= HtmlUtility.translate(s) %>">
	 * <textarea name=body rows=20 cols=53><%= HtmlUtility.translate(s) %></textarea>
	 *-
	 *-
	 * 또 필요하다면 yyyyyyy 부분도  translate(s)를 할 필요가 있을 겁니다.
	 * 필요할 때 마다 사용하세요.
	 *-
	 *-
	 * </xmp>
	 *
	 *
	 * see <a href="http://power.lgeds.lg.co.kr/~java/bbs/read.cgi?b=www&c=r_p&n=934284565">http://power.lgeds.lg.co.kr/~java/bbs/read.cgi?b=www&c=r_p&n=934284565</a><br>
	 * see <a href="http://developer.netscape.com/docs/manuals/htmlguid/tags22.htm#1819476">http://developer.netscape.com/docs/manuals/htmlguid/tags22.htm#1819476</a>
	 *
	 * @return the translated string.
	 * @param s java.lang.String
	 */
	public static String translate(String s) {
		if ( s == null ) return null;

		StringBuffer buf = new StringBuffer();
		char[] c = s.toCharArray();
		int len = c.length;
		for ( int i=0; i < len; i++) {
			if      ( c[i] == '&' ) buf.append("&amp;");
			else if ( c[i] == '<' ) buf.append("&lt;");
			else if ( c[i] == '>' ) buf.append("&gt;");
			else if ( c[i] == '"' ) buf.append("&quot;");
			else if ( c[i] == '\'') buf.append("&#039;");
			else buf.append(c[i]);
		}
		return buf.toString();
	}
}