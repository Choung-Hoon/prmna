/*
* @(#)StringUtil.java   1.0 2011/07/01
 */
package lee.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import org.joda.time.DateTime;

public class StringUtil {
	
    private static final String FORMAT_DEFAULT = "#,##0";
	private static final String FORMAT_DOUBLE  = "#,##0.00";
	
	/** 임의 영문 난수 발생 by mhlee */
	static public String getRandomKey(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		String chars[] = "A,a,B,b,C,c,D,d,E,e,F,f,G,g,H,h,I,i,J,j,K,k,L,l,M,m,N,n,O,o,P,p,Q,q,R,r,S,s,T,t,U,u,V,v,W,w,X,x,Y,y,Z,z".split(",");
		
		for(int i=0; i<length; i++) 
		{
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		
		return buffer.toString();
	}
	
	static public String getRandomKeyOfNumber(int len){
	    StringBuffer buffer = new StringBuffer();	
	    Random random = new Random();
	    
	    for(int i=0; i<len; i++){
	        buffer.append(random.nextInt(10));
	    }
	    
	    return buffer.toString();
	}
    
	/**
	 * 지정된 확장자형식의 파일인지 확인
	 * 
	 * @param filename
	 *            파일명
	 * @param extensions
	 *            확장자
	 * @return 지정한 확장자 형식의 파일여부
	 */
	public static boolean validateFileExt(String filename, String[] extensions) {
		boolean result = false;
		if (filename == null || filename.equals("")
				|| filename.indexOf(".") < 0) {
			return result;
		}
		if (extensions != null && extensions.length > 0) {
			String ext = filename.substring(filename.lastIndexOf(".") + 1);

			for (int i = 0; i < extensions.length; i++) {
				if (ext.toLowerCase().equals(extensions[i].toLowerCase())) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	public static String[] stringTokenizer(String str, String delim) {
		if (str == null) {
			return null;
		} else {
			StringTokenizer st = new StringTokenizer(str, delim);

			ArrayList resultList = new ArrayList();
			String[] result = null;

			while (st.hasMoreTokens()) {
				resultList.add(st.nextToken().trim());
			}

			result = new String[resultList.size()];
			resultList.toArray(result);
			
			return result;
		}
	}

	/**
	 * HTML 에서 표현될 특수 문자(&amp;, \n, &nbsp;, &lt;, &gt;, &quot;) 처리
	 * 
	 * @param source
	 * @return String
	 */
	public static String toHtml(String source) {
		if (source == null) {
			return null;
		} else {
			source = replace(source, "&", "&amp;");
			source = replace(source, "<", "&lt;");
			source = replace(source, ">", "&gt;");
			source = replace(source, "\n", "<br>");
			source = replace(source, "\"", "&quot;");
			return source;
		}
	}
	
	public static String reverseHtml(String source) {
	    if (source == null) {
            return null;
        } else {
            source = replace(source, "&amp;", "&");
            source = replace(source, "&lt;", "<");
            source = replace(source, "&gt;", ">");
            source = replace(source, "<br>", "\n");
            source = replace(source, "&quot;", "\"");
            return source;
        }
	}

	/**
	 * HTML 에서 textbox 에 들어갈 특수 문자(&quot;) 처리
	 * 
	 * @param source
	 * @return String
	 */
	public static String toText(String source) {
		if (source == null) {
			return null;
		} else {
			source = replace(source, "\"", "&quot;");
			return source;
		}
	}

	public static String replace(String source, String fromStr, String toStr) {
		if (source == null)
			return null;
		int start = 0;
		int end = 0;
		StringBuffer result = new StringBuffer();
		while ((end = source.indexOf(fromStr, start)) >= 0) {
			result.append(source.substring(start, end));
			result.append(toStr);
			start = end + fromStr.length();
		}
		result.append(source.substring(start));
		return result.toString();
	}

	/**
	 * "YYYY-MM-DD" 형식의 날짜를 "MM.DD.YY" 형식의 날짜로 변환
	 * 
	 * @param source
	 *            "YYYY-MM-DD" 형식
	 * @return String "MM.DD.YY" 형식
	 */
	public static String transDateFormat(String source) {
		if (source == null)
			return null;
		if (source.length() != 10)
			return source;
		return source.substring(5, 7) + "." + source.substring(8, 10) + "."
				+ source.substring(2, 4);
	}

	/**
	 * "YYYYMMDD" 형식의 날짜를 "YYYY.MM.DD" 형식의 날짜로 변환
	 * 
	 * @param source
	 *            "YYYYMMDD" 형식
	 * @return String "YYYY.MM.DD" 형식
	 */
	public static String toDateFormat(String source) {
		if (source == null)
			return null;
		if (source.length() != 8)
			return source;
		return source.substring(0, 4) + "." + source.substring(4, 6) + "."
				+ source.substring(6, 8);
	}

	/**
	 * "YYYYMMDD" 형식의 날짜를 "YYYY-MM-DD" 형식의 날짜로 변환
	 * 
	 * @param source
	 *            "YYYYMMDD" 형식
	 * @return String "YYYY-MM-DD" 형식
	 */
	public static String toDateFormat2(String source) {
		if (source == null)
			return null;
		if (source.length() != 8)
			return source;
		return source.substring(0, 4) + "-" + source.substring(4, 6) + "-"
				+ source.substring(6, 8);
	}
	
	/**
	 * "YYYYMMDD" 형식의 날짜를 "YYYY/MM/DD" 형식의 날짜로 변환
	 * 
	 * @param source
	 *            "YYYYMMDD" 형식
	 * @return String "YYYY-MM-DD" 형식
	 */
	public static String toDateFormat3(String source) {
		if (source == null)
			return null;
		if (source.length() != 8)
			return source;
		return source.substring(0, 4) + "/" + source.substring(4, 6) + "/"
				+ source.substring(6, 8);
	}

	
	/**
	 *  "YYYY/MM/DD" 형식의 날짜를 "년월일"로 변경
	 * 
	 * @param source
	 *            "YYYYMMDD" 형식
	 * @return String "YYYY-MM-DD" 형식
	 */
	public static String toDateFormat4(String source) {
		if (source == null)
			return null;
		if (source.length() != 8)
			return source;
		return source.substring(0, 4) + "년 " + source.substring(4, 6) + "월 "
				+ source.substring(6, 8) + "일";
	}
	
	public static String ltrim(String source, String trimStr) {
		if (source != null && source.startsWith(trimStr)) {
			return source.substring(trimStr.length());
		}
		return source;
	}

	public static String rtrim(String source, String trimStr) {
		if (source != null && source.endsWith(trimStr)) {
			return source.substring(0, source.length() - trimStr.length());
		}
		return source;
	}

	public static String decryptString(String s) {
		if (s == null || s.equals(""))
			return "";

		char ac[] = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			ac[i] = s.charAt(i);
		}

		char ac1[] = new char[s.length()];
		for (int j = 0; j < s.length(); j++) {
			ac1[j] = (char) ((ac[j] + s.length()) - (j + 1) - 32);
		}

		return new String(ac1);
	}

	public static String encryptString(String s) {
		if (s == null || s.equals(""))
			return "";

		char ac[] = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			ac[i] = s.charAt(i);
		}

		char ac1[] = new char[s.length()];
		for (int j = 0; j < s.length(); j++) {
			ac1[j] = (char) ((ac[j] - s.length()) + (j + 1) + 32);
		}

		return new String(ac1);
	}

	public static String pwdDecode(String s) {
		if (s == null || s.equals(""))
			return "";

		char ac[] = new char[s.length()];
		String as[] = { "aF", "bC", "c9", "dN", "eU", "fo", "gv", "hw", "iD",
				"jE", "kO", "lc", "mS", "nj", "oV", "pn", "qQ", "rZ", "sa",
				"td", "uY", "ve", "wq", "x4", "yP", "zi", "AG", "BK", "C8",
				"DB", "Eg", "F7", "GI", "H5", "Ip", "Js", "Kt", "LW", "MX",
				"N6", "OR", "Pk", "QT", "R2", "Sy", "Tl", "Um", "Vx", "WA",
				"X1", "Yr", "ZM", "0f", "1J", "2h", "3z", "40", "5u", "63",
				"7L", "8H", "9b", null };
		for (int i = 0; i < s.length(); i++) {
			int j;
			for (j = 0; as[j] != null; j++) {
				if (s.charAt(i) != as[j].charAt(1)) {
					continue;
				} else {
					ac[i] = as[j].charAt(0);
					break;
				}
			}

			if (as[j] == null)
				ac[i] = ' ';
		}

		return new String(ac);
	}

	public static String pwdEncode(String s) {
		char ac[] = new char[s.length()];

		String as[] = { "aF", "bC", "c9", "dN", "eU", "fo", "gv", "hw", "iD",
				"jE", "kO", "lc", "mS", "nj", "oV", "pn", "qQ", "rZ", "sa",
				"td", "uY", "ve", "wq", "x4", "yP", "zi", "AG", "BK", "C8",
				"DB", "Eg", "F7", "GI", "H5", "Ip", "Js", "Kt", "LW", "MX",
				"N6", "OR", "Pk", "QT", "R2", "Sy", "Tl", "Um", "Vx", "WA",
				"X1", "Yr", "ZM", "0f", "1J", "2h", "3z", "40", "5u", "63",
				"7L", "8H", "9b", null };
		for (int i = 0; i < s.length(); i++) {
			int j;
			for (j = 0; as[j] != null; j++) {
				if (s.charAt(i) != as[j].charAt(0)) {
					continue;
				} else {
					ac[i] = as[j].charAt(1);
					break;
				}
			}

			if (as[j] == null)
				ac[i] = ' ';
		}

		return new String(ac);
	}

	/**
	 * ISO8859_1로 인코딩된 문자열을 KSC5601로 변환한다.
	 * 
	 * @param str8859
	 *            8859_1로 encoding된 String
	 * @return KSC5601로 변환된 String
	 */
	public static String toKor(String str8859) {

		if (str8859 == null)
			return null;

		String str5601 = null;

		try {
			str5601 = new String(str8859.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			str5601 = str8859;
		}

		return str5601;
	}

	/**
	 * ISO8859_1로 인코딩된 문자열을 UTF-8로 변환한다.
	 * 
	 * @param str8859
	 *            8859_1로 encoding된 String
	 * @return UTF-8로 변환된 String
	 */
	public static String toUTF8(String str8859) {

		if (str8859 == null)
			return null;

		String strUTF8 = null;

		try {
			strUTF8 = new String(str8859.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			strUTF8 = str8859;
		}

		return strUTF8;
	}

	/**
	 * KSC5601로 인코딩된 문자열을 ISO8859_1로 변환한다.
	 * 
	 * @param str5601
	 *            KSC5601로 encoding된 String
	 * @return ISO8859_1로 변환된 String
	 */
	public static String toEng(String str5601) {

		if (str5601 == null)
			return null;

		String str8859 = null;

		try {
			str8859 = new String(str5601.getBytes("KSC5601"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			str8859 = str5601;
		}

		return str8859;
	}

	/**
	 * <code>Reader</code> 로 부터 String 을 얻는다.
	 * 
	 * @param is
	 * @return String
	 * @throws IOException
	 */
	public static String getStringFromReader(Reader is) throws IOException {
		if (is == null)
			return null;

		StringWriter sw = new StringWriter();
		char[] buffer = new char[1024];
		int length = -1;

		try {
			while ((length = is.read(buffer)) != -1) {
				sw.write(buffer, 0, length);
			}
			is.close();
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (is != null)
				is.close();
		}

		return sw.toString();
	}

	/**
	 * 필터리스트를 가지고 필터링처리
	 * 
	 * @param source
	 *            필터링할 문자열
	 * @param filters
	 *            필터리스트
	 * @param delim
	 *            필터구분자
	 * @param toWord
	 *            변경할 문자열
	 * @return String 필터링된 문자열
	 */
	public static String filterString(String source, String filters,
			String delim, String toWord) {

		String[] filterList = stringTokenizer(filters, delim);
		StringBuffer result = new StringBuffer();

		if (source == null) {
			return null;
		} else {
			for (int i = 0; i < filterList.length; i++) {
				source = replace(source, filterList[i], toWord);
			}

			return source;
		}
	}

	/**
	 * 문자열중사이즈 를 체크하여 ...을 붙여준다. Case p=0 Contents Write Case p=1 Reply >
	 * 
	 * @param str
	 *            처리할 문자열
	 * @param size
	 *            자를 문자열수
	 * @param backstr
	 *            뒤에 붙을 문자
	 * @return String 처리된 문자열
	 */
	public static String toTruncate(String str, int size, String backstr) {

		String ret = "";

		if (str != null && str.length() > size) {
			ret = str.substring(0, size) + backstr;
		} else {
			ret = str;
		}
		return ret;
	}

	/**
	 * HTML문서에서 태그를 제거하고 텍스트만 추출한다.
	 * 
	 * @param html
	 *            HTML스트링
	 * @return String
	 */
	public static String removeHtmlTag(String html) {
		if (html == null || html.trim().equals(""))
			return "";

		final int NORMAL_STATE = 0;
		final int TAG_STATE = 1;
		final int START_TAG_STATE = 2;
		final int END_TAG_STATE = 3;
		final int SINGLE_QUOT_STATE = 4;
		final int DOUBLE_QUOT_STATE = 5;
		int state = NORMAL_STATE;
		int oldState = NORMAL_STATE;
		char[] chars = html.toCharArray();
		StringBuffer sb = new StringBuffer();
		char a;
		for (int i = 0; i < chars.length; i++) {
			a = chars[i];
			switch (state) {
			case NORMAL_STATE:
				if (a == '<')
					state = TAG_STATE;
				else
					sb.append(a);
				break;
			case TAG_STATE:
				if (a == '>')
					state = NORMAL_STATE;
				else if (a == '\"') {
					oldState = state;
					state = DOUBLE_QUOT_STATE;
				} else if (a == '\'') {
					oldState = state;
					state = SINGLE_QUOT_STATE;
				} else if (a == '/')
					state = END_TAG_STATE;
				else if (a != ' ' && a != '\t' && a != '\n' && a != '\r'
						&& a != '\f')
					state = START_TAG_STATE;
				break;
			case START_TAG_STATE:
			case END_TAG_STATE:
				if (a == '>')
					state = NORMAL_STATE;
				else if (a == '\"') {
					oldState = state;
					state = DOUBLE_QUOT_STATE;
				} else if (a == '\'') {
					oldState = state;
					state = SINGLE_QUOT_STATE;
				} else if (a == '\"')
					state = DOUBLE_QUOT_STATE;
				else if (a == '\'')
					state = SINGLE_QUOT_STATE;
				break;
			case DOUBLE_QUOT_STATE:
				if (a == '\"')
					state = oldState;
				break;
			case SINGLE_QUOT_STATE:
				if (a == '\'')
					state = oldState;
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 숫자를 화폐단위로 포맷팅(ex. 1000000 -> 1,000,000)
	 * 
	 * @param num
	 * @return String
	 */
	public static String toCurrencyFormat(int num) {
		String result = "";
		String format = "#,###";
		try {
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance();
			 * result = formatter.format(num);
			 */
			NumberFormat formatter = new DecimalFormat(format);
			result = formatter.format(num);
		} catch (Exception e) {
			return itos(num);
		}
		return result;
	}
	
	/**
	 * 숫자를 화폐단위로 포맷팅(ex. 1000000 -> 1,000,000)
	 * 
	 * @param num
	 * @return String
	 */
	public static String toCurrencyFormat(String num) {
		return toCurrencyFormat(stoi(num));
	}

	/**
	 * 숫자를 지정된 형식으로 포맷팅(ex. 1000000 -> 1,000,000)
	 * 
	 * @param num
	 * @param format
	 *            (ex. "0.###", "###.00", "###,##")
	 * @return String
	 */
	public static String toNumberFormat(double num, String format) {
		String result = "";
		try {
			NumberFormat formatter = new DecimalFormat(format);
			result = formatter.format(num);
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * <b>INT를 STRING으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param int     입력값
	 * @return String 값을 리턴
	 * <pre>
	 * 사용 예1 : GenUtil.itos(3) -> 결과 : "3"<br>
	 * </pre>
	 */
    public static String itos(int i) {
        return new Integer(i).toString();
    }
    
    /**
	 * <b>long를 STRING으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param long    입력값
	 * @return String 값을 리턴
	 * <pre>
     * 사용 예1 : GenUtil.itos(3) -> 결과 : "3"<br>
     * </pre>
	 */
    public static String ltos(long l) {
        return new Long(l).toString();
    }
    
    /**
	 * <b>String를 INT로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param String    입력값
	 * @return int 값을 리턴
	 * <pre>
     *  사용 예1 : GenUtil.itos("3") -> 결과 : 3<br>
     * </pre>
	 */
    public static int stoi(String s) {
        try {
            if (s == null || s.equals(""))
                return 0;
            else
                return Integer.valueOf(s).intValue();
        }catch(Exception e) {
            return 0;
        }
    }
    
    /**
	 * <b>String를 LONG으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param String    입력값
	 * @return int 값을 리턴
	 * <pre>
     * 사용 예1 : GenUtil.itos("3") -> 결과 : 3<br>
     * </pre>
	 */
    public static long stol(String s) {
        if (s == null || s == "")
            return 0L;
        else
            return Long.valueOf(s).longValue();
    }
    
    /**
     * String을 double값으로 변환한다.           <BR>
     * @param    str     double값으로 변환될 String문자열.
     * @return   변환된 int 값.
     * <pre>
     * int cnt = GenUtil.stod("10");	<BR>
     * </pre>
     */
    public static double stod(String str) {
        if (str == null)
            return 0;
        try {
            return (Double.parseDouble(str));
        } catch (java.lang.NumberFormatException e) {
            return 0L;
        }
    }
    
    /**
     * src가 null거나 ""이면 tgt값으로 치환하여 return한다
     */
    static public String NVL(String src, String tgt) {
        String res = tgt;

        if (tgt == null)
            res = "";
        if (src == null)
            return res;
        else if (src.equals(""))
            return res;
        else
            return src;
    }

    public static String NVL(String str) {
        if (str == null)
            return "";
        else
            return str;
    }
    
    /**
     * 입력받은 double형을 원하는 원하는 문자열 포맷으로 변경시켜 준다.<BR>
     * @param	num	        입력받은 double
     * @return	String 	    포맷변경된 String
     * <pre>
     * int cnt = GenUtil.dFmtToIntFmt(12345.57);	<BR>
     * cnt = 123,456
     * </pre>
     */
    public static String dFmtToIntFmt(double num) {
      return dFormat(num,"#");
    }

    public static String dFmtToIntFmt(String num) {
      return dFormat(stod(num),"#");
    }

    public static String dFormat(double num, String form) {
        DecimalFormat decimalformat = new DecimalFormat(form);
        return decimalformat.format(num);
    }

    public static String dFormat(String num, int i) {

        return dFormat(stod(num), i);
    }

    public static String dFormat(String num) {

        return dFormat(stod(num), 0);
    }
    
    /**
     * 입력받은 double형을 원하는 원하는 문자열 포맷으로 변경시켜 준다.<br>
     * @param	num	        입력받은 double
     * @param	i           소숫점자리 자리수(자리수가 적으면 반올림됨)
     * @return	String 	    포맷변경된 String
     * <pre>
     * int cnt = GenUtil.dFmtToIntFmt(12345.57,1);	<BR>
     * cnt = 123,456.6								<br>
     * int cnt = GenUtil.dFmtToIntFmt(12345.57,2);	<BR>
     * cnt = 123,456.57								<BR>
     * </pre>
     */
    public static String dFormat(double d, int i) {
        String s = "#,##0";
        if (i > 0)
            s = s + ".";
        for (int j = 0; j < i; j++) {
            if (j == 0)
                s += "0";
            else
                s += "#";
        }

        DecimalFormat decimalformat = new DecimalFormat(s);
        return decimalformat.format(d);
    }
    
    /**
	 * <b>inputData가 NULL 또는 공백문자열인 경우 true, 그렇지 않은 경우 false를 리턴한다.</b><p>
	 * 
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br>
	 * @param inputData 입력문자열
	 * @return
	 * <pre>
	 * 사용 예 : GenUtil.isEmpty("   ") -> 결과 : true<br>
	 * </pre>
	 */
	public static boolean isEmpty(final String inputData) {		
		if (inputData == null || "".equals(inputData.trim())) {
			return true;
		} else {
			return false;
		}		
	}
	
	/**
    *<p>기능 : 입력된 값을 주민사업자번호형식으로 변환한다.<br>
    *  2000년 이후 출생한 고객의 주민번호 분기를 함.
    *</p> <br>
    *@param     str     		String 
    *@return    새로운   string
    * <pre>
	*      <b> <사용법> </b>
	*      String s = GenUtil.setJuMinNO("1234567890");
	*      <b> <결과> </b>
	*      String s = "123-45-67890";
	*      <b> <사용법> </b>
	*      String s = GenUtil.setJuMinNO("1234567890123");
	*      <b> <결과> </b>
	*      String s = "123456-7890123";
	* </pre>
    */
    public static String setJuMinNO(String str){
    	
    	if (str == null)
            return str = "0";
        try {
        	if (str.startsWith("0")) 	throw new NumberFormatException();
        	else    					return setJuMinNO(Double.parseDouble(str));
        } catch (java.lang.NumberFormatException e) {
        	StringBuffer sb = new StringBuffer();
        	String s = "";
        	if (str.length() == 10) {
                s = (sb.append(str.substring(0, 3)).append("-").append(
                		str.substring(3, 5)).append("-").append(str.substring(5))).
                    toString();
            } else if (str.length() == 13) {
                s = (sb.append(str.substring(0, 6)).append("-").append(
                		str.substring(6))).
                    toString();
            } else {
                   s = str;
            }

            return s;
        }
    }
	    
	/**
    *<p>기능 : 입력된 값을 주민사업자번호형식으로 변환한다.</p> <br>
    *@param     d     		double 
    *@return    새로운   string
    * <pre>
	*      <b> <사용법> </b>
	*      String s = GenUtil.setJuMinNO(1234567890);
	*      <b> <결과> </b>
	*      String s = "123-45-67890";
	*      <b> <사용법> </b>
	*      String s = GenUtil.setJuMinNO(1234567890123);
	*      <b> <결과> </b>
	*      String s = "123456-7890123";
	* </pre>
    */
    public static String setJuMinNO(double d) {
        String s = "";
        String tmp = dFormat(d, "#########");
        StringBuffer sb = new StringBuffer();
        if (tmp.length() == 10) {
            s = (sb.append(tmp.substring(0, 3)).append("-").append(
                tmp.substring(3, 5)).append("-").append(tmp.substring(5))).
                toString();
        } else if (tmp.length() == 13) {
            s = (sb.append(tmp.substring(0, 6)).append("-").append(
                tmp.substring(6))).
                toString();
        } else {
               s = tmp;
        }

        return s;
    }
    
    /**
     *<p>기능 : 입력된 값을 계좌번호형식으로 변환한다.<br>
     *		  11자리 : 3-2-6 포맷, 12자리 : 3-2-7<br>
     *		  16자리 : 4-4-4-4		</p> <br>
     *@param     d     		double 
     *@param     creditCardGubun int  기타(0),신용카드(1),외환(2)  
     *@return    새로운   string
     * <pre>
 	 *      <b> <사용법> </b>
 	 *      String s = GenUtil.setGyejaNO(12345678901,0);
 	 *      <b> <결과> </b>
 	 *      String s = "123-45-678901";
 	 *      <b> <사용법> </b>
 	 *      String s = GenUtil.setGyejaNO(123456789012,0);
 	 *      <b> <결과> </b>
 	 *      String s = "123-45-6789012";
 	 *      <b> <사용법> 신용카드</b>
 	 *      String s = GenUtil.setGyejaNO(123456789012345,1);
 	 *      <b> <결과> </b>
 	 *      String s = "123-4567-8901-2345";
 	 *       <b> <사용법> 외환</b>
 	 *      String s = GenUtil.setGyejaNO(123456789012345,2);
 	 *      <b> <결과> </b>
 	 *      String s = "123456789012345";
 	 * </pre>
     */
    public static String setGyejaNO(double d, int creditCardGubun) {
    	String tmp = dFormat(d, "000000000");
    	return setGyejaNO(tmp,creditCardGubun);
    }
	
	
	/**
    *<p>기능 : 입력된 값을 계좌번호형식으로 변환한다.<br>
    *		  11자리 : 3-2-6 포맷, 12자리 : 3-2-7<br>
    *		  16자리 : 4-4-4-4		</p> <br>
    *@param     tmp     		String 
    *@param     creditCardGubun int  기타(0),신용카드(1),외환(2)  
    *@return    새로운   string
    * <pre>
	*      <b> <사용법> </b>
	*      String s = GenUtil.setGyejaNO("12345678901",0);
	*      <b> <결과> </b>
	*      String s = "123-45-678901";
	*      <b> <사용법> </b>
	*      String s = GenUtil.setGyejaNO("123456789012",0);
	*      <b> <결과> </b>
	*      String s = "123-45-6789012";
	*      <b> <사용법> 신용카드</b>
	*      String s = GenUtil.setGyejaNO("123456789012345",1);
	*      <b> <결과> </b>
	*      String s = "123-4567-8901-2345";
	*      <b> <사용법> 외환</b>
	*      String s = GenUtil.setGyejaNO("123456789012345",2);
	*      <b> <결과> </b>
	*      String s = "123456789012345";
	* </pre>
    */
    public static String setGyejaNO(String tmp, int creditCardGubun) {
    	
    	String s = "";

    	if(tmp == null) return "";

    	tmp = tmp.trim();

    	StringBuffer sb = new StringBuffer();

    	if (tmp.length() == 11) {
    		s = (sb.append(tmp.substring(0, 3)).append("-").append(
    		tmp.substring(3, 5)).append("-").append(tmp.substring(5))).
            toString();
    	} else if (tmp.length() == 12) {
    		s = (sb.append(tmp.substring(0, 3)).append("-").append(
            tmp.substring(3, 5)).append("-").append(tmp.substring(5))).
            toString();
    	} else if (tmp.length() == 16) {
    		if(creditCardGubun == 0) { //신용카드
    			s = (sb.append(tmp.substring(0, 4)).append("-").append(
    			tmp.substring(4, 8)).append("-").append(tmp.substring(8, 12)).append("-").append(tmp.substring(12))).
    			toString();
    		} else {  //외화
    			s = tmp;
    		}
      }else{
    	  s = tmp;
      }
      return s;
    }
	    
    /**
	 * <b> A. 거래자명/고객명 : 앞에서 2~3까지, 7~8까지 글자를 "XX" 처리(예:대한XX주식XX)
	 *	   B. 실명번호 : - 주민번호, 외국인등록번호 등 : 8~12까지 "XXXXX" 처리(예:123456-1XXXXXX)
	 *	       - 사업자번호 : 6~9까지 "XXXX" 처리(123-81-XXXXX)
	 *	   C. 계좌번호 : 3번째 1자리를 “X”, 6~7번째 "XX" 처리(예: 10X-20-XX8910)</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2011. 08. 22<br>
	 * @param originInfo    마스킹 처리할 스트링
	 * @param gubun         마스킹 구분 (NAME. 거래자명/고객명 SSN.실명번호 ACCT.계좌번호)
	 * @return
	 */
	public static String maskingPersonalInfo(String originInfo, String gubun) {
		
		StringBuffer fullMaskedInfo = new StringBuffer() ;
		
		if (originInfo == null || isEmpty(originInfo)) return "" ;
		
		// 거래자명/고객명
		if ("NAME".equals(gubun)) {
			
			char arrayChar[] = originInfo.trim().toCharArray();
	        for(int i = 0; i < arrayChar.length; i++)
	        {
	        	if ( i == 1 || i == 2 || i == 4 || i == 5) arrayChar[i] = 'X' ;
	        	fullMaskedInfo.append(arrayChar[i]);
	        }
		} 
		// 실명번호
		else if ("SSN".equals(gubun)) {
			
			fullMaskedInfo.append(setJuMinNO(originInfo));
			
			// 10자리이면 사업자번호로 판단함
			if (originInfo.length() == 10) { 
				fullMaskedInfo.replace(7,fullMaskedInfo.length(),"XXXXX");
			// 이외는 
			} else {
				fullMaskedInfo.replace(8,fullMaskedInfo.length(),"XXXXXX");
			}	
			
		} 
		// 계좌번호
		else if ("ACCT".equals(gubun)) {
			
			fullMaskedInfo.append(setGyejaNO(originInfo,1));
			fullMaskedInfo.replace(2,3,"X");
			fullMaskedInfo.replace(7,fullMaskedInfo.length(),"XXXXXX");
			
		} 
		// 기타 
		else {
			
			fullMaskedInfo.append(originInfo);
			fullMaskedInfo.replace(3,fullMaskedInfo.length(),"XXXXXXXXXXX");
		}

		return fullMaskedInfo.toString() ;
	}
	
	/**
	 * <b>숫자포맷으로 변경 후 리턴한다.</b><p>
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br>
	 * @param inputData
	 * @return
	 * <pre>
	 * 사용 예1 : GenUtil.formatNumber(12345.10) --> 결과 : "12,345.10"<br>
	 * 사용 예2 : GenUtil.formatNumber(12345.1) --> 결과 : "12,345.10"<br>
	 * </pre>   
	 */
	public static String formatNumber(double inputData) {
		DecimalFormat df = new DecimalFormat(FORMAT_DOUBLE);
		return df.format(inputData);
	}
	
	/**
	 * <b> 숫자 포맷으로 변경후 리턴한다.</b><p>
	 * 사용 예 : GenUtil.formatNumber(123456789) --> 결과 : "123,456,789"<br>
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br> 
	 * @param inputData
	 * @return
	 * <pre>
	 * 사용 예 : GenUtil.formatNumber(123456789) --> 결과 : "123,456,789"<br>
	 * </pre>   
	 */
	public static String formatNumber(long inputData) {
		DecimalFormat df = new DecimalFormat(FORMAT_DEFAULT);
		return df.format(inputData);
	}
	
	/**
	 * <b> 숫자 포맷으로 변경후 리턴한다.</b><p>
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br> 
	 * @param inputData
	 * @return
	 * <pre>
	 * 사용 예 : GenUtil.formatNumber(123456789) --> 결과 : "123,456,789"<br>
	 * </pre>   
	 */
	public static String formatNumber(int inputData) {
		return formatNumber((long)inputData);
	}
	
	/**
	 * <b> 숫자 포맷으로 변경후 리턴한다.</b><p>	
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br> 
	 * @param inputData
	 * @return
	 * <pre>
	 * 사용 예1 : GenUtil.formatNumber("123456789") --> 결과 : "123,456,789"<br>
	 * 사용 예2 : GenUtil.formatNumber("12345.10") --> 결과 : "12,345.10"<br>
	 * 사용 예3 : GenUtil.formatNumber("12345.1") --> 결과 : "12,345.10"<br>
	 * </pre>   
	 */
	public static String formatNumber(String inputData) throws Throwable {
		try {
			return formatNumber(Long.parseLong(inputData));
		}
		catch ( Exception e ) {
			try {
				return formatNumber(Double.parseDouble(inputData));
			}
			catch ( Throwable t ) {
				throw new Throwable(t.getMessage());
			}
		}
	}
	
	/**
	 * <b>숫자포맷으로 변경 후 리턴한다.</b><p>
	 * <br>
	 * 작성자 : kdh<br>
	 * 작성일자 : 2008. 07. 30<br>
	 * @param inputData, unit
	 * @return
	 * <pre>
	 * 사용 예1 : GenUtil.formatNumber(123456789.00,1000) --> 결과 : "123,456"<br>
	 * </pre>  
	 */
	public static String formatNumber(double inputData, int unit) {
		DecimalFormat df = new DecimalFormat(FORMAT_DEFAULT);
		return df.format(Math.floor(inputData / unit));
	}
	
	/**
	 * <b> 숫자 포맷으로 변경후 리턴한다.</b><p>
	 * <br>
	 * 작성자 : kdh<br>
	 * 작성일자 : 2008. 07. 30<br> 
	 * @param inputData, unit
	 * @return
	 * <pre>
	 * 사용 예1 : GenUtil.formatNumber("123456789.00",1000) --> 결과 : "123,456"<br>
	 * ) --> 결과 : "12,345.10"<br>
	 * </pre> 
	 */
	public static String formatNumber(String inputData, int unit) throws Throwable {
		try {
			return formatNumber(Double.parseDouble(inputData), unit);
		}
		catch ( Throwable t ) {
			throw new Throwable(t.getMessage());
		}
	}
	
	/**
	 * <b>STRING을 시간 포맷으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2009. 02. 09<br>
	 * @param  String    입력값
	 * @return String 값을 리턴
	 * <pre>
     * 사용 예1 : GenUtil.makeTimeType("123456") -> 결과 : "12:34:56"<br>
     * </pre>
	 */
    public static String makeTimeType(String s) {
        if (s.length() == 0)
            return "";
        if (s.length() != 6) {
            return "invalid length, ussage : 123456";
        } else {
            String s1 = s.substring(0, 2);
            String s2 = s.substring(2, 4);
            String s3 = s.substring(4, 6);
            return s1 + ":" + s2 + ":" + s3 ;
        }
    }
    
    /**
	 * <b>STRING을 날짜 포맷으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param  String    입력값
	 * @return String 값을 리턴
	 * <pre>
     * 사용 예1 : GenUtil.makeDateTimeType("20081209") -> 결과 : "2008-12-09"<br>
     * </pre>
	 */
    public static String makeDateType(String s) {
        if (s.length() == 0 || s == null)
            return "";
        if (s.length() != 8) {
            return "invalid length";
        } else {
            String s1 = s.substring(0, 4);
            String s2 = s.substring(4, 6);
            String s3 = s.substring(6, 8);
            return s1 + "-" + s2 + "-" + s3;
        }
    }
    
    /**
	 * <b>sDate를 날짜 포맷으로 변경 후 리턴한다.</b><p>	 
	 * <br>
	 * 작성자 : 조 성 희<br>
	 * 작성일자 : 2008. 07. 30<br>
	 * @param sDate 날짜
	 * @return
	 * @throws Exception 
	 * <pre>
	 * 사용 예1 : GenUtil.formatDate("20080730") -> 결과 : "2008-07-30"<br>
	 * 사용 예2 : GenUtil.formatDate("200807") -> 결과 : "2008-07"<br>
	 * </pre>
	 */
	public static String formatDate(final String sDate) throws Exception{		
		String sDate_t = "";
		
		if ( sDate == null ) {
			sDate_t = "";
		}
		else if ( sDate.length() == 6 && DateUtils.isValidDate(sDate,"yyyyMM")) {
			sDate_t = sDate.substring(0, 4) + "-" + sDate.substring(4);
		}
		else if ( sDate.length() == 8 && DateUtils.isValidDate(sDate,"yyyyMMdd")) {
			sDate_t = sDate.substring(0, 4) + "-" + sDate.substring(4, 6)
					+ "-" + sDate.substring(6);
		}
		else {
			sDate_t = sDate;
		}	
		
		return sDate_t;
	}
	
	/**
	 * <b>STRING을 날짜 시간 포맷으로 변환하여 리턴</b><p>
	 * <br>
	 * 작성자 : 이 강 기<br>
	 * 작성일자 : 2008. 12. 08<br>
	 * @param  String    입력값
	 * @return String 값을 리턴
	 * <pre>
     * 사용 예1 : GenUtil.makeDateTimeType("20081209123456") -> 결과 : "2008-12-09 12:34:56"<br>
     * </pre>
	 */
    public static String makeDateTimeType(String s) {
        if (s.length() == 0)
            return "";
        if (s.length() != 14) {
            return "invalid length";
        } else {
            String s1 = s.substring(0, 4);
            String s2 = s.substring(4, 6);
            String s3 = s.substring(6, 8);
            String s4 = s.substring(8, 10);
            String s5 = s.substring(10, 12);
            String s6 = s.substring(12, 14);
            return s1 + "-" + s2 + "-" + s3 + " " + s4 + ":" + s5 + ":" + s6;
        }
    }
}
