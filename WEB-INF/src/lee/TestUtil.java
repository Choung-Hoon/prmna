package lee;

import java.util.StringTokenizer;

public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String [] delFiles = getToken("1/2/3","/") ;
	    String    del_filename = "1" ;
	    StringBuffer  filesBuffer  = new StringBuffer() ; ;
	    
	    for (int i = 0 ; i < delFiles.length ; i++ ){
	                
	        if (del_filename.equals(delFiles[i])) continue ;
	        
	        if ((filesBuffer.toString()).equals("")) filesBuffer.append(delFiles[i]);
	        else filesBuffer.append("/" + delFiles[i]);
	    
	    }
	    
	    System.out.println(filesBuffer.toString());

	}
	
	public static String[] getToken(String s, String s1) {
        if (s == null || s.length() < 0)
            s = "";
        if (s1 == null || s1.length() < 0)
            s1 = "";
        StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
        String as[] = new String[stringtokenizer.countTokens()];
        for (int i = 0; stringtokenizer.hasMoreTokens(); i++)
            as[i] = stringtokenizer.nextToken();

        return as;
    }
}
