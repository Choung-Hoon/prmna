/*
 * @(#)MD5Cryptor.java
 *
 */
package lee.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 암호화 구현 class
 *
 * @author
 * @version 1.0.0
 */
public final class MD5Cryptor {

  public static String getMD5Crypt( String unCrypt )
      throws NoSuchAlgorithmException {

    int index;
    byte[] digest;

    MessageDigest md5 = MessageDigest.getInstance( "MD5" );
    StringBuffer digestBuffer = new StringBuffer();

    digestBuffer.setLength( 0 );
    digest = md5.digest( unCrypt.getBytes() );

    for( index = 0; index < digest.length; ++index ) {
      digestBuffer.append(
          Integer.toHexString( digest[index] & 0xff ).toUpperCase() );
    }

    return digestBuffer.toString();
  }

  public static void main(String[] args)
  {
	  try {
		  System.out.println( MD5Cryptor.getMD5Crypt("gangdong25") );
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
}
