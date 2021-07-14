package lee.common.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lee.common.config.ConfigException;
import lee.common.config.PropertyManager;
import lee.common.util.StringUtil;

/**
 * UTF-8 기반 파일 다운로드
 */
public class ExcelDownLoadServlet extends HttpServlet {

    public ExcelDownLoadServlet() {
        out = null;
    }

    public void init(ServletConfig cf) throws ServletException {
        super.init(cf);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        BufferedInputStream ins = null;
        javax.servlet.ServletOutputStream outs = null;
        
        out = new PrintWriter(new OutputStreamWriter(res.getOutputStream()),true);
        
        try {
            
            String filename = StringUtil.toUTF8( req.getParameter("filename") );
            filename = StringUtil.replace( filename, " ", "_" );  
            String filepath = PropertyManager.getInstance().getString("excel.download") + File.separator + filename; 
           
            res.setContentType("application/x-msdownload;charset=utf-8");

            if( req.getHeader( "User-Agent" ).indexOf("MSIE 5.5") != -1 ) {
                res.setHeader("Content-Type", "doesn/matter;");
                res.setHeader("Content-Disposition", "filename=" +
                    java.net.URLEncoder.encode( filename,"utf-8" ) + ";" );
            } else {
                res.setHeader("Content-Type", "application/octet-stream;");
                res.setHeader("Content-Disposition", "attachment;filename=" +
                    java.net.URLEncoder.encode( filename,"utf-8" ) + ";" );
            };

            res.setHeader("Content-Transfer-Encoding", "binary;");

            ins = new  BufferedInputStream( new FileInputStream( new File(filepath) ) );
            outs = res.getOutputStream();

            int readInt = 0;
            while( ( readInt = ins.read() ) != -1 ) outs.write( readInt );
            outs.flush();
        } catch( IOException e ) {
            throw e;
        } catch( ConfigException e) {
        	throw new IOException(e.getMessage());
          } finally {
            if( ins != null ) try { ins.close(); } catch( Exception e ) {}
            if( outs != null ) try { outs.close(); } catch( Exception e ) {}
          }       
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        doPost(req, res);
    }

    public void destory() {
        super.destroy();
    }

    private PrintWriter out;
}
