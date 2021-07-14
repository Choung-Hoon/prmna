/*
* @(#)ByteUtil.java   1.0 2010/01/20
 */
package lee.common.util;

public class ByteUtil {

  /**
   * int 를 4byte 크기의 byte array 로 변환
   * @param int
   * @return 4 byte array
   */
  public static final byte[] intToByteArray(int value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value
           };
  }

  /**
   * 4byte 크기의 byte array를 int 로 변환
   * @param 4 byte array
   * @return int
   */
  public static final int byteArrayToInt(byte [] b) {
    return (b[0] << 24)
            + ((b[1] & 0xFF) << 16)
            + ((b[2] & 0xFF) << 8)
            + (b[3] & 0xFF);
  }

  /**
   * hex 문자열을 byte 로 변환
   * @param hexString (ex. FF)
   * @return byte
   */
  public static final byte hexStringToByte(String hexString) {
    int val = Integer.parseInt(hexString, 16);
    return (new Integer(val)).byteValue();
  }

  /**
   * byte 를 hex 문자열로 변환
   * @param byte
   * @return hexString (ex. FF)
   */
  public static final String byteToHexString(byte b) {
    StringBuffer buf = new StringBuffer();
    char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    int high = 0;
    int low = 0;

    high = ((b & 0xf0) >> 4);
    low = (b & 0x0f);
    buf.append(hexChars[high]);
    buf.append(hexChars[low]);

    return buf.toString();
  }

  /**
   * byte array 값을 hex 문자열로 변환
   * @param byte[]
   * @return hexString (ex. 31FF)
   */
  public static String byteArrayToHexString(byte[] data) {
    StringBuffer buf = new StringBuffer();
    char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    int len = data.length;
    int high = 0;
    int low = 0;
    for (int i = 0; i < len; i++) {
        high = ((data[i] & 0xf0) >> 4);
        low = (data[i] & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
    return buf.toString();
  }

  /**
   * hex 문자열 값을 int 로 변환
   * @param hexString (ex. FF)
   * @return int
   */
  public static final int hexStringToInt(String hexString) {
    return Integer.parseInt(hexString, 16);
  }

  /**
   * 지정된 크기의 byte array 를 반환<br/>
   * - 지정한 크기가 데이터보다 큰 경우 : 남는부분은 0x00(NULL) 값으로 채움,<br/>
   * - 지정한 크기가 데이터보다 작은 경우 : 데이터에서 지정한 크기만큼만 반환
   * @param source
   * @param length
   * @return byte[]
   */
    public static final byte[] setByte(String str, int len) {

    byte[] data = str.getBytes();
    byte[] ret = new byte[len];
    byte nb = hexStringToByte("00");

    for(int i=0; i<len; i++) {
      ret[i] = nb;
    }

    if(data.length < len) {
      for(int i=0; i<data.length; i++) {
        ret[i] = data[i];
      }
    } else {
      for(int i=0; i<len; i++) {
        ret[i] = data[i];
      }
    }
    return ret;
  }

  /**
   * 원본 byte array 을 대상 byte array의 offset 위치에 저장
   * @param 대상 byte array
   * @param 원본 byte array
   * @param 시작위치
   * @return byte[]
   */
  public static final byte[] putByte(byte[] data, byte[] str, int offset) {
    for(int i=0; i<str.length; i++) {
      data[offset + i] = str[i];
    }
    return data;
  }

  /**
   * 원본 byte array 의 offset 위치로부터 지정한 크기만큼의 byte array 를 반환
   * @param 원본 byte array
   * @param 가져올크기
   * @param 시작위치
   * @return byte[]
   */
  public static final byte[] getByte(byte[] data, int len, int offset) {
    if(data.length <= len) return data;

    byte[] ret = new byte[len];
    for(int i=0; i<ret.length; i++) {
      ret[i] = data[offset + i];
    }
    return ret;
  }

  /**
   * byte array 를 0x00 으로 초기화
   * @param byte array
   * @return byte[]
   */
  public static final byte[] initByte(byte[] data) {
    byte nb = ByteUtil.hexStringToByte("00");
    for(int i=0; i<data.length; i++) {
      data[i] = nb;
    }
    return data;
  }

}
