package lee.common.db;

/**
 * <p>Title: JAVA FrameWork</p>
 * <p>Description: JAVA FrameWorks</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:  LG������ Co., Ltd</p>
 *
 * NOTICE !      You can copy or redistribute this code freely,
 * but you should not remove the information about the copyright notice
 * and the author.
 
 * @author Jeon, Kang Hee    ohammer@lghitachi.co.kr
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Vector;

import org.apache.log4j.Logger;

public class JResultSet
    implements java.io.Serializable {

  Vector m_recordSet;       
  Vector m_recordSetExt;    

  Vector m_record;          
  Vector m_columnName;      

  int m_recordPos;          
  int m_recordCnt;          
  int m_columnCnt;          
  
  private Logger logger = null;
	
  public JResultSet(ResultSet rs) throws SQLException {
    m_recordSet = new Vector(500);
    m_recordSetExt = new Vector(500);
    m_columnName = new Vector(500);
    
    m_recordCnt = 0;
    m_recordPos = 0;
    m_columnCnt = 0;
    
    this.logger = Logger.getLogger( this.getClass() ); // by mhlee
    
    if (!makeSet(rs)) {
    	logger.error("[JResultSet] Make Set is false");
    }
  }

  public boolean first() {
    if (m_recordCnt <= 0)
      return false; 

    m_recordPos = 0;
    m_record = (Vector) m_recordSet.elementAt(m_recordPos);
    return true;
  }


  public boolean next() {
    if (m_recordCnt <= 0)
      return false; //���ڵ尡 �� �ǵ� ���� ���

    m_recordPos++; // Current Position�� ������Ų��.

    if (m_recordPos <= m_recordCnt) {
      m_record = (Vector) m_recordSet.elementAt(m_recordPos - 1);
      return true;
    }

    return false;
  }

  public boolean previous() {
    if (m_recordCnt <= 0)
      return false; //���ڵ尡 �� �ǵ� ���� ���

    if ( (m_recordPos < 0) || (m_recordPos > m_recordCnt))
      return false; //���ڵ���ġ�� ������ ���

    if (m_recordPos >= 1) {
      m_recordPos--;
      m_record = (Vector) m_recordSet.elementAt(m_recordPos - 1);
      return true;
    }

    return false;
  }


  public boolean last() {
    if (m_recordCnt <= 0)
      return false; //���ڵ尡 �� �ǵ� ���� ���
    m_recordPos = m_recordCnt;
    m_record = (Vector) m_recordSet.elementAt(m_recordPos - 1);
    return true;
  }


  public boolean move(int nPos) {
    if (m_recordCnt <= 0)
      return false; //���ڵ尡 �� �ǵ� ���� ���

    int nTmp = m_recordPos + nPos;
    if (nTmp > m_recordCnt || (nTmp < 0))
      return false;

    m_recordPos += nPos; // Current Position�� ������Ų��.
    m_record = (Vector) m_recordSet.elementAt(m_recordPos - 1);

    return true;
  }


  public boolean makeSet(ResultSet rs) throws SQLException {
    ResultSetMetaData rsmd = rs.getMetaData();

    if (rsmd == null)
      return false;

    String str = "";
    double d = 0;
    long l = 0;
    int n = 0;
    java.sql.Date dt = null;
    Time time = null;
    Timestamp timestamp = null;
    byte[] bt = null;
    ByteArrayInputStream bais = null;

    try {
      String colname;
      m_columnCnt = rsmd.getColumnCount(); //Į������

      m_columnName.addElement("_seq_"); // ���ڵ� ������ ���Ӽ���

      //1. ���ڵ峻�� Į�������� �˾Ƴ��� �� �� ��ŭ ��ȯ.
      for (int colPos = 1; colPos <= m_columnCnt; colPos++) {
        // �÷����� �ߺ��Ǵ��� Ȯ���Ѵ�.
        colname = (new String(rsmd.getColumnName(colPos))).toLowerCase();
        if (m_columnName.indexOf(colname) > -1) {
          throw new SQLException("�÷����� �ߺ��Ǿ���ϴ�.");
        }

        // �÷����� �ҹ��ڷ� ġȯ�Ѵ�.
        m_columnName.addElement(colname);
      }

      //2. ���ڵ� ��ȯ�ϸ�  ���� ������
      while (rs.next()) {
        m_recordCnt++; //���ڵ� ���� ������Ŵ.
        m_record = new Vector();
        Vector recordExt = new Vector();

        m_record.addElement(""+m_recordCnt); // �÷��� "_SEQ_"�� �ش��ϴ� �ʵ尪(1,2,3~)

        //3. ���ڵ峻�� Į������ ��ŭ ��ȯ.
        for (int colPos = 1; colPos <= m_columnCnt; colPos++) {
          int nType = rsmd.getColumnType(colPos);
          //Logger.debug("COLUMN TYPE=[" + nType + "]");
          //4. �÷�Ÿ�Կ� ��� ���� �ٸ��� ������.
          //   put�ϴ� ��� ��� �ϳ��� �Ű������� Null�̸� �ȵ�...

          //5. Oracle ���� Number�ʵ�� nType 2 �� Return �ǹǷ�
          //   �Ҽ������� ó�� ������ �����ֿ��� �� (2001.9.5 �ڳ���)
          //Sybase�� TEXT�׸��� Column type -1�εȴ�. �̰��� VARCHARŸ������ ����
          //          if(nType==-1) nType=12;
          if (nType == 2) {
            nType = 8;
          }

          try {
            switch (nType) {
              case Types.VARBINARY:
              case Types.BINARY:
                bt = rs.getBytes(colPos);
                if (bt == null)
                  str = "";
                else
                  str = new String(bt);

                m_record.addElement(str);
                break;

              case Types.LONGVARCHAR:
                bais = (ByteArrayInputStream) rs.getAsciiStream(colPos);
                if (bais == null)
                  str = "";
                else {
                  bt = new byte[bais.available()];
                  bais.read(bt);
                  str = new String(bt);
                }

                m_record.addElement(str);
                break;

              case Types.VARCHAR:
              case Types.CHAR:

                str = rs.getString(colPos);
                if (str == null)
                  str = "";
                m_record.addElement(str);
                break;

              case Types.DOUBLE:
              case Types.REAL:
              case Types.FLOAT:
                d = rs.getDouble(colPos);
                m_record.addElement(new Double(d).toString());
                break;

              case Types.SMALLINT:
              case Types.TINYINT:
              case Types.INTEGER:
                n = rs.getInt(colPos);
                m_record.addElement(new Integer(n).toString());
                break;

              case Types.BIGINT:
                l = rs.getLong(colPos);
                m_record.addElement(new Long(l).toString());
                break;

              case Types.DECIMAL:
                d = rs.getDouble(colPos);
                m_record.addElement(new Double(d).toString());
                break;
              case Types.NUMERIC:
                d = rs.getDouble(colPos);
                m_record.addElement(new Long( (long) d).toString());
                break;

              case Types.TIME:
                time = rs.getTime(colPos);
                if (time == null)
                  m_record.addElement("");
                else
                  m_record.addElement(time.toString());
                break;

              case Types.TIMESTAMP:
                timestamp = rs.getTimestamp(colPos);
                if (timestamp == null)
                  m_record.addElement("");
                else
                  m_record.addElement(timestamp.toString());
                break;

              case Types.DATE:
                dt = rs.getDate(colPos);
                if (dt == null)
                  m_record.addElement("");
                else
                  m_record.addElement(dt.toString());
                break;

              default:
                str = rs.getString(colPos);
                if (str == null)
                  m_record.addElement("");
                else
                  m_record.addElement(str);
                break;
            }

            recordExt.addElement(new Boolean(rs.wasNull()));

          }
          catch (Exception e) {
            logger.error("[JResultSet] nType = " + nType);
            logger.error("[JResultSet] colPos = " + colPos +
                                 " Į�� catch error ===" + e);
          }
        } //for - Į��������ŭ ��ȯ

        m_recordSet.addElement(m_record);
        m_recordSetExt.addElement(recordExt);

      } //while - ���ڵ� ������ŭ ��ȯ
      m_columnCnt++;

      return true;

    } //try finish
    catch (Exception e) {
      logger.error("[JResultSet] makeSet() Error :::::::::::::::::"+e);
      e.toString();
    }

    return false;
  }

  /**
   * byte Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	byte
   */
  public byte getByte(int columnIndex) {
    try {
      if (m_record == null)
        return -1;
      String value = (String) m_record.elementAt(columnIndex);
      return (Byte.valueOf(value)).byteValue();
    }
    catch (Exception e) {
      logger.error("[JResultSet] getByte Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * byte Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	byte
   */
  public byte getByte(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return -1;
    return getByte(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * Time Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	Time
   */
  public Time getTime(int columnIndex) {
    try {
      if (m_record == null)
        return null;
      String value = (String) m_record.elementAt(columnIndex);
      return Time.valueOf(value);
    }
    catch (Exception e) {
      logger.error("[JResultSet] getTime Error :::::::::::::::::");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Time Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	Time
   */
  public java.sql.Time getTime(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return null;
    return getTime(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * Timestamp Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	Timestamp
   */
  public Timestamp getTimestamp(int columnIndex) {
    try {
      if (m_record == null)
        return null;
      String value = (String) m_record.elementAt(columnIndex);
      return Timestamp.valueOf(value);
    }
    catch (Exception e) {
      logger.error("[JResultSet] getTimestamp Error :::::::::::::::::");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Timestamp Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	Timestamp
   */
  public java.sql.Timestamp getTimestamp(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return null;
    return getTimestamp(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * Date Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	Date
   */
  public java.sql.Date getDate(int columnIndex) {
    try {
      if (m_record == null)
        return null;
      String value = (String) m_record.elementAt(columnIndex);
      return java.sql.Date.valueOf(value);
    }
    catch (Exception e) {
      logger.error("[JResultSet] getDate Error :::::::::::::::::");
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Date Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	Date
   */
  public java.sql.Date getDate(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return null;
    return getDate(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * double Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	double
   */
  public double getDouble(int columnIndex) {
    try {
      if (m_record == null)
        return -1;

      String value = (String) m_record.elementAt(columnIndex);
      return (Double.valueOf(value)).doubleValue();
    }
    catch (Exception e) {
      logger.error("[JResultSet] getDouble Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * double Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	double
   */
  public double getDouble(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return -1;
    return getDouble(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * float Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	float
   */
  public float getFloat(int columnIndex) {
    try {
      if (m_record == null)
        return -1;
      String value = (String) m_record.elementAt(columnIndex);
      return (Double.valueOf(value)).floatValue();
    }
    catch (Exception e) {
      logger.error("[JResultSet] getFloat Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * float Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	float
   */
  public float getFloat(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return -1;
    return getFloat(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * int Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int Column Index
   * @return 	int
   */
  public int getInt(int columnIndex) {
    try {
      if (m_record == null)
        return -1;
      String value = (String) m_record.elementAt(columnIndex);
      return (Double.valueOf(value)).intValue();
    }
    catch (Exception e) {
      logger.error("[JResultSet] getInt Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * int Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	int
   */
  public int getInt(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return -1;
    return getInt(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * long Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   *
   * @param 	int 	Column Index
   * @return 	long
   */
  public long getLong(int columnIndex) {
    try {
      if (m_record == null)
        return -1;

      String value = (String) m_record.elementAt(columnIndex);
      return (Double.valueOf(value)).longValue();
      //return Long.parseLong(value);

    }
    catch (Exception e) {
      logger.error("[JResultSet] getLong Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * long Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   *
   * @param 	String	Column Name
   * @return 	long
   */
  public long getLong(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return -1;
    return getLong(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * String Ÿ�� Į���� Column Index�� �̿��Ͽ� ��´�.
   * �̶� �����ͺ��̽��� Charater Set�� System�� Chracter Set���� ��ȯ�Ѵ�.
   *
   * @param 	int 	Column Index
   * @return 	String
   */
  public String getString(int columnIndex) {
    try {
      if (m_record == null)
        return null;

      return (String) m_record.elementAt(columnIndex);
    }
    catch (Exception e) {
      logger.error("[JResultSet] getString Error :::::::::::::::::");
      e.printStackTrace();
    }
    return "";
  }

  /**
   * String Ÿ�� Į���� Column Name�� �̿��Ͽ� ��´�.
   * �̶� �����ͺ��̽��� Charater Set�� System�� Chracter Set���� ��ȯ�Ѵ�.
   *
   * @param 	String	Column Name
   * @return 	String
   */
  public String getString(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return null;
    return getString(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * ���� ��� �ִ� Vector�� ���ڵ� ������ ��ȯ��Ŵ
   *
   * @return int	rowcount
   */
  public int getRowCount() {
    try {
      return m_recordSet.size();
    }
    catch (Exception e) {
      logger.error(
          "[JResultSet] getRowCount Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * ��� Vector�� ���ڵ� �÷� ������ ��ȯ��Ŵ
   *
   * @return int	Columncount
   */
  public int getColumnCount() {
    try {
      return m_columnCnt;
    }
    catch (Exception e) {
      logger.error(
          "[JResultSet]  getColumnCount Error :::::::::::::::::");
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * �־��� �÷���ġ�� ���� null������ üũ�Ѵ�.
   *
   * @param 	int 	Column Index
   * @return 	boolean
   */
  public boolean isNull(int columnIndex) {
    try {
      if (m_record == null)
        return true;

      if (m_recordCnt <= 0)
        return true; //���ڵ尡 �� �ǵ� ���� ���

      Vector recordExt = (Vector) m_recordSetExt.elementAt(m_recordPos - 1);
      return ( (Boolean) recordExt.elementAt(columnIndex)).booleanValue();

    }
    catch (Exception e) {
      logger.error("[JResultSet] isNull Error :::::::::::::::::");
      e.printStackTrace();
    }
    return false;
  }

  /**
   * �÷����� ��ġ�� ã�� �ش� ���� null������ üũ�Ѵ�.
   *
   * @param 	String 	Column name
   * @return 	boolean
   */
  public boolean isNull(String columnName) {
    //Į���� ��ġ�� ã�Ƴ��� �޼ҵ带 �̿���...
    if (m_record == null)
      return true;
    return isNull(m_columnName.indexOf(columnName.toLowerCase()));
  }

  /**
   * �÷����� �����Ѵ�.
   *
   * @param 	int 	Column index
   * @return 	String
   */
  public String getColumnName(int index){
    return (String)m_columnName.elementAt(index) ;
  }
}