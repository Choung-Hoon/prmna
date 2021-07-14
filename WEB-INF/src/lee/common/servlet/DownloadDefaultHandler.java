/*
 * @(#)DownloadDefaultHandler.java
 */
package lee.common.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lee.common.util.StringUtil;
import lee.common.config.ConfigManager;

/**
 *
 * @author sjju
 * @version 1.0, 2011/07/01
 */
public class DownloadDefaultHandler extends DownloadHandler {

  protected ConfigManager config = null;

	public String getHandlerId() {
		return "default";
	}

	public void init() throws Exception {
	  config = ConfigManager.getInstance();
  }

	public void handle(HttpServletRequest req, HttpServletResponse res) throws Exception {

    //logRequestParameters( req );

    BufferedInputStream ins  = null;
    ServletOutputStream outs = null;

    try {

      String filePath = null;
      String fileType = req.getParameter( "fileType" );    //첨부파일구분
      String rfileName = req.getParameter( "rfileName" );  //실제파일명
      String cfileName = req.getParameter( "cfileName" );  //생성파일명
      
      if( "popup".equals( fileType ) ) {
        filePath = config.getPopupFileUpload();
      } else if( "banner".equals( fileType ) ) {
        filePath = config.getBannerFileUpload();
      } else if( "product".equals( fileType ) ) {
        filePath = config.getProductFileUpload();
      } else if( "board".equals( fileType ) ) {
        filePath = config.getBoardFileUpload();
      } else if( "scholar".equals(fileType)) { 
    	filePath = config.getScholarFileUpload();  
      } else if( "doc".equals(fileType) ) {
    	  filePath = config.getDocFileUpload();  
      }
      else {
        filePath = config.getFileUpload();
      }

      if( ( filePath == null || filePath.equals( "" ) ) &&
          ( rfileName == null || rfileName.equals( "" ) ) &&
          ( cfileName == null || cfileName.equals( "" ) ) ) {
        throw new IllegalArgumentException(
            "다운로드할 파일 경로와 이름이 올바르지 않습니다. " );
      }

      String fullPath = null;

      if( filePath == null ) filePath = "";
      if( cfileName == null || cfileName.equals( "" ) ) fullPath = filePath;
      else fullPath = filePath + File.separator + rfileName;

      File file = new File( fullPath );
//      System.out.println("############ " + rfileName);
//      rfileName = StringUtil.replace(rfileName, " ", "_");
//      System.out.println("############ " + rfileName);
      /*
      res.setContentType("application/x-msdownload;charset=euc-kr");

      if( req.getHeader( "User-Agent" ).indexOf("MSIE 5.5") != -1 ) {
          res.setHeader("Content-Type", "doesn/matter;");
          res.setHeader("Content-Disposition", "filename=" +
              java.net.URLEncoder.encode( file.getName(),"euc-kr" ) + ";" );
      } else {
          res.setHeader("Content-Type", "application/octet-stream;");
          res.setHeader("Content-Disposition", "attachment;filename=" +
              java.net.URLEncoder.encode( file.getName(),"euc-kr" ) + ";" );
      };
      */

      /* 기존 : euc-kr (개발할때...)
      res.setContentType("text/plain;charset=euc-kr");
      String filename_8859_1 = new String(cfileName.getBytes(),"ISO-8859-1"); // rfileNAme대신 수정.. by mhlee
      
      if( req.getHeader( "User-Agent" ).indexOf("MSIE 5.5") != -1 ) {
        res.setHeader("Content-Type", "doesn/matter;charset=8859_1");
        res.setHeader("Content-Disposition", "filename=" + filename_8859_1 + ";" );
      } else {
        res.setHeader("Content-Type", "application/octet-stream;charset=8859_1");
        res.setHeader("Content-Disposition", "attachment;filename=" + filename_8859_1 + ";" );
      };
	  */
	  	      
      // by mhlee. UTF-8 (서버모듈에 올릴때.....)
      res.setContentType("application/x-msdownload;charset=utf-8");

      if( req.getHeader( "User-Agent" ).indexOf("MSIE 5.5") != -1 ) {
          res.setHeader("Content-Type", "doesn/matter;");
          res.setHeader("Content-Disposition", "filename=" +
              java.net.URLEncoder.encode( cfileName,"utf-8" ) + ";" );
      } else {
          res.setHeader("Content-Type", "application/octet-stream;");
          res.setHeader("Content-Disposition", "attachment;filename=" +
              java.net.URLEncoder.encode( cfileName,"utf-8" ) + ";" );
      };     

      res.setHeader("Content-Transfer-Encoding", "binary;");
      //======================================================================
		
      ins = new  BufferedInputStream( new FileInputStream( file ) );
      outs = res.getOutputStream();

      int readInt = 0;
      while( ( readInt = ins.read() ) != -1 ) outs.write( readInt );
      outs.flush();

    } catch( Exception e ) {
      throw e;
    } finally {
      if( ins != null ) try { ins.close(); } catch( Exception e ) {}
      if( outs != null ) try { outs.close(); } catch( Exception e ) {}
    }
	}

	public void destroy() throws Exception {}

}
