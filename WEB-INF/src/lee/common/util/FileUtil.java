/*
* @(#)FileUtil.java   1.0 2010/01/20
 */
package lee.common.util;

import java.util.*;
import java.io.*;

/**
 * File 관련 유틸리티 클래스
 *
 * @author  주성진
 * @version 1.0, 2010/01/20
 */
public class FileUtil {

  private static final int BUFFER_SIZE = 1024;

  /**
   * 텍스트 포맷의 파일을 읽어 String 문자열로 반환한다.
   *
   * @param filePath 파일경로
   * @return String
   * @throws IOException
   */
  public static String readFileForText(String filePath) throws IOException {
    return new String(readFileForBinary(filePath));
  }

  /**
   * 텍스트 및 바이너리 포맷의 파일을 읽어 Bynary Data(byte array) 로 반환한다.
   *
   * @param filePath 파일경로
   * @return byte[]
   * @throws IOException
   */
  public static byte[] readFileForBinary(String filePath) throws IOException {
    InputStream is = null;
    ByteArrayOutputStream os = null;

    int size = 1024;
    byte[] buffer = new byte[size];
    int length = -1;

    try {
      is = new FileInputStream(filePath);
      os = new ByteArrayOutputStream();

      while ((length = is.read(buffer)) != -1) {
        os.write(buffer, 0, length);
      }
    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( is != null ) is.close();
      if( os != null ) os.close();
    }

    return os.toByteArray();
  }

  /**
   * Bynary Data(byte array)를 파일로 저장한다.
   *
   * @param filePath 파일경로
   * @param byte[]
   * @throws IOException
   */
  public static void writeFileForBinary(String filePath, byte[] data) throws IOException {
    ByteArrayInputStream is = null;
    OutputStream os = null;

    int size = 1024;
    byte[] buffer = new byte[size];
    int length = -1;

    try {
      is = new ByteArrayInputStream(data);
      os = new FileOutputStream(filePath);

      while ((length = is.read(buffer)) != -1) {
        os.write(buffer, 0, length);
      }
    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( is != null ) is.close();
      if( os != null ) os.close();
    }
  }

  /**
   * 하위의 모든 파일과 디렉토리를 삭제한다.
   *
   * @param   file    삭제할 파일 또는 디렉토리
   */
  public static void removeDir(File file) {
    if( file.exists() ) {
      if( file.isDirectory() ) {
        File [] list = file.listFiles();
        for(int i=0; i < list.length; i++) {
          removeDir(list[i]);
        }
        file.delete();
      } else {
        file.delete();
      }
    } // end of if( file.isDirectory() )
  } // end of if( file.exists() )

  /**
   * 파일을 삭제한다.
   *
   * @param   filename   전체경로를 포함하는 삭제할 파일명
   */
  public static void removeFile(String filename) {

    File file = new File( filename );

    if( file.exists() ) {
      file.delete();
    }
  }

  /**
   * 디렉토리로부터 파일목록을 가져온다.
   *
   * @param  path    파일을 가져올 디렉토리
   * @return File[]
   */
  public static File[] getFileListFromDir(String path) {

    File dir = new File( path );
    File[] list = null;

    if( dir.exists() ) {
      if( dir.isDirectory() ) {
        list = dir.listFiles();
      }
    }
    return list;
  }

  /**
   * fromPath 하위의 모든 경로들을 basePath 를 기준으로한 상대경로로 가져온다.
   *
   * return String[]
   */
  public static String [] getAllSubRelDirs( String fromPath, String basePath) {
    String [] toPath = null;
    ArrayList toPathArray = new ArrayList();

    File folder = new File( fromPath );
    toPathArray.add( folder.getAbsolutePath() );

    getAllSubDirs( fromPath, toPathArray );

    toPath = new String[toPathArray.size()];
    toPathArray.toArray( toPath );

    for(int i=0; i<toPath.length; i++) {
      toPath[i] = StringUtil.ltrim( toPath[i], basePath );
      toPath[i] = toPath[i].replace( '\\', '/' );
      toPath[i] = StringUtil.ltrim( toPath[i], "/" );
      if( !toPath[i].endsWith("/") ) {
        toPath[i] = toPath[i] + "/";
      }
    }

    return toPath;
  }

  /**
   * 주어진 경로로 부터 하위의 모든 경로들을 ArrayList 에 넣는다.
   */
  public static void getAllSubDirs( String fromPath, ArrayList pathArray ) {
    File folder = new File( fromPath );
    File [] subFiles = folder.listFiles();

    for(int i=0; i<subFiles.length; i++) {
      if( subFiles[i].isDirectory() ) {
        pathArray.add( subFiles[i].getAbsolutePath() );

        getAllSubDirs( subFiles[i].getAbsolutePath(), pathArray );
      }
    }
  }

  /**
   * 텍스트 포맷의 파일을 줄단위로 읽어 배열로 반환한다.
   *
   * @param filePath 파일경로
   * @return String[]
   * @throws IOException
   */
  public static String[] readFileByLine(String filePath) throws IOException {

    ArrayList resultList = new ArrayList();
    String[] result = null;

    String line = "";
    BufferedReader in = null;

    try {
      in = new BufferedReader( new FileReader(filePath) );

      while( (line = in.readLine()) != null ) {
        if( !line.trim().equals("") ) {
          resultList.add( line.trim() );
        }
      }

    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( in != null ) in.close();
    }

    result = new String[resultList.size()];
    resultList.toArray(result);

    return result;
  }
  
  /**
   * 텍스트 포맷의 파일을 한글 타입으로 줄단위로 읽어 배열로 반환한다.
   *
   * @param filePath 파일경로
   * @return String[]
   * @throws IOException
   */
  public static String[] readFileToKorByLine(String filePath) throws IOException {

    ArrayList resultList = new ArrayList();
    String[] result = null;

    String line = "";
    BufferedReader in = null;

    try {
      in = new BufferedReader( new FileReader(filePath) );

      while( (line = in.readLine()) != null ) {
        if( !line.trim().equals("") ) {
          resultList.add( StringUtil.toKor(line.trim()) );
        }
      }

    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( in != null ) in.close();
    }

    result = new String[resultList.size()];
    resultList.toArray(result);

    return result;
  }

/**
   * 텍스트 포맷의 파일을 UTF8 타입으로 줄단위로 읽어 배열로 반환한다.
   *
   * @param filePath 파일경로
   * @return String[]
   * @throws IOException
   */
  public static String[] readFileToUTF8ByLine(String filePath) throws IOException {

    ArrayList resultList = new ArrayList();
    String[] result = null;

    String line = "";
    BufferedReader in = null;

    try {
      in = new BufferedReader( new FileReader(filePath) );

      while( (line = in.readLine()) != null ) {
        if( !line.trim().equals("") ) {
          resultList.add( StringUtil.toUTF8(line.trim()) );
        }
      }

    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( in != null ) in.close();
    }

    result = new String[resultList.size()];
    resultList.toArray(result);

    return result;
  }


	
  /**
   * 텍스트 포맷의 파일 마지막에 문자열을 추가한다.
   *
   * @param filePath 파일경로
   * @param str 추가할 문자열
   * @throws IOException
   */
  public static void addLineToFile(String filePath, String str) throws IOException {

    PrintWriter pw = null;

    try {
      pw = new PrintWriter(
               new BufferedWriter( new FileWriter(filePath, true) ) );
      pw.println( str );
      pw.flush();

    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( pw != null ) pw.close();
    }
  }

  /**
   * 파일을 새로 등록한다.
   *
   * @param filePath 파일경로
   * @param String[] 문자열 목록
   * @throws IOException
   */
  public static void updateFile(String filePath, String[] strList) throws IOException {

    PrintWriter pw = null;

    try {
      pw = new PrintWriter(
               new BufferedWriter( new FileWriter(filePath, false) ) );
      for(int i=0; i<strList.length; i++) {
        pw.println( strList[i] );
      }
      pw.flush();

    } catch(IOException ioe) {
      throw ioe;
    } finally {
      if( pw != null ) pw.close();
    }
  }

  public static File rename( File file, String newName ) throws Exception {

      if( file == null || !file.exists() ) return null;
      if( newName == null || newName.equals( "" ) ) return file;

      File newFile = new File( file.getParent() + File.separator + newName );
      file.renameTo( newFile );

      return newFile;
    }

    public static File copy( String src, String targetDir ) throws Exception {

      File srcFile = new File( src );
      if( !srcFile.exists() ) return null;

      File tgtDir = new File( targetDir );
      if( !tgtDir.exists() ) throw new IOException( targetDir + " not exists" );
      if( !tgtDir.isDirectory() )
          throw new IOException( targetDir + " not dir" );

      File copied = null;
      if( srcFile.isDirectory() ) {
        copied = new File( targetDir + File.separator + srcFile.getName() );
        copied.mkdirs();
        String[] subs = srcFile.list();
        for( int i = 0; i < subs.length; i++ ) {
          copy( src + File.separator + subs[i], copied.getAbsolutePath() );
        }
      } else {
        copied = copyFile( srcFile, targetDir );
      }

      return copied;
    }

    public static File copyTree( String dir, String targetDir ) throws Exception {
      return copy( dir, targetDir );
    }

    public static File copyFile( File src, String targetDir ) throws Exception {

      if( !src.exists() ) return null;

      File tDir = new File( targetDir );
      if( !tDir.exists() ) {
        tDir.mkdirs();
      } else if( !tDir.isDirectory() ) {
        throw new IOException( targetDir + " not dir" );
      }

      /* KeyGenerator 를 사용하지 않고 파일을 생성하는 경우 중복되는 파일명 체크
      String fileName = src.getName();
      if( fileName != null && !fileName.equals("") && targetDir != null && !targetDir.equals("") ) {
        while( existFile( fileName, targetDir ) ) {
          fileName = renameFile( fileName );
        }
      }
      */
      // 파일확장자 정보를 가져온다.
      String ext = "";
      if( src.getName().indexOf(".") > 0 ) {
        ext = src.getName().substring(src.getName().lastIndexOf("."));
      }
      // KeyGenerator 를 사용하여 파일을 생성
      String fileName = KeyGenerator.getDecreaseKey() + ext;

      File tFile = new File( targetDir + File.separator + fileName );

      InputStream in = new FileInputStream( src );
      OutputStream out = new FileOutputStream( tFile );

      int read = 0;
      byte[] buf = new byte[ BUFFER_SIZE ];
      while( ( read = in.read( buf ) ) != -1 ) out.write( buf, 0, read );

      in.close(); out.flush(); out.close();
      fileName = "";

      return tFile;
    }

    public static boolean existFile( String fileName, String targetDir ) throws Exception {

      boolean result = false;
      File [] files = getFileListFromDir( targetDir );
      for( int i=0; files != null && i<files.length; i++ ) {
        if( files[i].getName().equals( fileName ) ) {
          result = true;
          break;
        }
      }
      return result;
    }

    public static String renameFile( String fileName ) throws Exception {

      if( fileName == null || fileName.equals("") ) return fileName;
      int lastIdx = fileName.lastIndexOf(".");


      if( lastIdx == -1 ) {
        fileName = fileName + "1";
      } else {
        fileName = fileName.substring(0,lastIdx) + "1" + fileName.substring(lastIdx);
      }

      return fileName;
    }

    public static void move( String src, String targetDir ) throws Exception {

      if( src == null || src.equals( "" ) ||
        targetDir == null || targetDir.equals( "" ) ) return;

      File srcFile = new File( src );
      if( !srcFile.exists() ) return;

      File targetPDir = new File( targetDir );
      if( !targetPDir.exists() )
          throw new IOException( "Destination dir not exists" );

      File targetFile = new File( targetDir + File.separator + srcFile.getName() );
      srcFile.renameTo( targetFile );
    }

    public static void moveTree( String src, String targetDir ) throws Exception {
      move( src, targetDir );
    }

    public static void makeAndMove( String src, String target ) throws Exception {

      if( src == null || src.equals( "" ) ||
          target == null || target.equals( "" ) ) return;

      File srcFile = new File( src );
      if( !srcFile.exists() ) return;

      File targetPDir = new File( target );
      if( !targetPDir.exists() ) targetPDir.mkdirs();

      File targetFile = new File( target + File.separator + srcFile.getName() );
      srcFile.renameTo( targetFile );
    }

    public static void makeAndMoveTree( String src, String target ) throws Exception {
      makeAndMove( src, target );
    }

    public static void remove( String path ) throws Exception {

      File deleteFile = new File( path );

      if( !deleteFile.exists() ) return;

      if( deleteFile.isDirectory() ) {
        String[] subs = deleteFile.list();
        for( int i = 0; i < subs.length; i++ ) {
          removeTree( path + File.separator + subs[i] );
        }
        if( !deleteFile.delete() )
            throw new IOException( "Fail to remove " + path );
      } else {
        if( !deleteFile.delete() )
            throw new IOException( "Fail to remove " + path );
      }
    }

    public static void removeTree( String path ) throws Exception {
      remove( path );
    }

    public static void removeEmpty( String path ) throws Exception {

      File deleteFile = new File( path );

      if( !deleteFile.exists() ) return;

      if( deleteFile.isDirectory() ) {
        String[] subs = deleteFile.list();
        for( int i = 0; i < subs.length; i++ ) {
          removeEmpty( path + File.separator + subs[i] );
        }
        if( deleteFile.list().length == 0 && !deleteFile.delete() )
            throw new IOException( "Fail to remove " + path );
      } else {
        if( deleteFile.length() == 0 && !deleteFile.delete() )
            throw new IOException( "Fail to remove " + path );
      }
    }

    public static long size( String src ) throws Exception {

      long result = 0;

      File file = new File( src );
      if( !file.exists() ) return 0;

      if( file.isFile() ) {
        result = file.length();
      } else {
        File[] subs = file.listFiles();
        for( int i = 0; i < subs.length; i++ ) {
          if( subs[i].isFile() ) result += subs[i].length();
        }
      }

      return result;
    }

    public static long sizeTree( String src ) throws Exception {

      long result = 0;

      File file = new File( src );
      if( !file.exists() ) return 0;

      if( file.isFile() ) {
        result = file.length();
      } else if( file.isDirectory() ) {
        String[] subs = file.list();
        for( int i = 0; subs != null && i < subs.length; i++ ) {
          result += sizeTree( src + File.separator + subs[i] );
        }
      }

      return result;
    }

    public static int countFile( String src ) throws Exception {

      int result = 0;

      File file = new File( src );
      if( !file.exists() ) return 0;

      if( file.isFile() ) {
        result = 1;
      } else if( file.isDirectory() ) {
        File[] subs = file.listFiles();
        for( int i = 0; i < subs.length; i++ ) {
          if( subs[i].isFile() ) result++;
        }
      }

      return result;
    }

    public static int countFileTree( String src ) throws Exception {

      int result = 0;

      File file = new File( src );
      if( !file.exists() ) return 0;

      if( file.isFile() ) {
        result = 1;
      } else if( file.isDirectory() ) {
        String[] subs = file.list();
        for( int i = 0; i < subs.length; i++ ) {
          result += countFileTree( src + File.separator + subs[i] );
        }
      }

      return result;
    }

    public static void main( String[] args ) {

      try {
        System.out.println( FileUtil.sizeTree( "D:/Temp/public_html" ) );
      } catch( Exception e ) {
        e.printStackTrace();
      }

      System.exit(0);
    }
}
