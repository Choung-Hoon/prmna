/*
* @(#)SendMail.java   1.0 2010/01/20
 */
package lee.common.util;

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

public class SendMailT {
  private String mailHost;
  private String mailHostID;
  private String mailHostPass;

  private String fromAddr;
  private String fromName;
  private String toAddrs;
  private String toNames;
  private String ccAddrs;
  private String ccNames;
  private String bccAddrs;
  private String bccNames;
  private String subject;
  private String textContents;
  private String htmlContents;
  private String attachPath;
  private String attachs;

  public SendMailT(String mailHost) {
    this.mailHost = mailHost;
  }

  public SendMailT(String mailHost, String mailHostID, String mailHostPass) {
    this.mailHost = mailHost;
    this.mailHostID = mailHostID;
    this.mailHostPass = mailHostPass;
  }

  /**
   * 메일을 발송한다.
   *
   * @param fromAddr      보내는 사람 메일 주소
   * @param fromName      보내는 사람 이름
   * @param toAddrs       받는 주소 목록(구분자 ;)
   * @param toNames       받는 사람 이름 목록(구분자 ;). 받는 주소와 개수가 같아야 한다.
   * @param subject       메일 제목
   * @param textContents  텍스트 본문
   * @param htmlContents  HTML 본문
   */
  public void sendMail(String fromAddr, String fromName, String toAddrs, String toNames,
                       String subject, String textContents, String htmlContents)
      throws MessagingException, UnsupportedEncodingException {
    sendMail(fromAddr, fromName, toAddrs, toNames, null, null,
             subject, textContents, htmlContents, null, null);
  }

  /**
   * 메일을 발송한다.
   *
   * @param fromAddr      보내는 사람 메일 주소
   * @param fromName      보내는 사람 이름
   * @param toAddrs       받는 주소 목록(구분자 ;)
   * @param toNames       받는 사람 이름 목록(구분자 ;). 받는 주소와 개수가 같아야 한다.
   * @param ccAddrs       참조 주소 목록(구분자 ;)
   * @param ccNames       참조 이름 목록(구분자 ;). 참조 주소와 개수가 같아야 한다.
   * @param subject       메일 제목
   * @param textContents  텍스트 본문
   * @param htmlContents  HTML 본문
   * @param attachPath    첨부파일이 저장된 위치
   * @param attachs       첨부파일명 목록(구분자 ;)
   */
  public void sendMail(String fromAddr, String fromName, String toAddrs, String toNames,
                       String ccAddrs, String ccNames, String subject,
                       String textContents, String htmlContents,
                       String attachPath, String attachs)
      throws MessagingException, UnsupportedEncodingException {
    sendMail(fromAddr, fromName, toAddrs, toNames,
             ccAddrs, ccNames, null, null, subject, textContents, htmlContents,
             attachPath, attachs);
  }

  /**
   * 메일을 발송한다.
   *
   * @param fromAddr      보내는 사람 메일 주소
   * @param fromName      보내는 사람 이름
   * @param toAddrs       받는 주소 목록(구분자 ;)
   * @param toNames       받는 사람 이름 목록(구분자 ;). 받는 주소와 개수가 같아야 한다.
   * @param ccAddrs       참조 주소 목록(구분자 ;)
   * @param ccNames       참조 이름 목록(구분자 ;). 참조 주소와 개수가 같아야 한다.
   * @param bccAddrs      숨은 참조 주소 목록(구분자 ;)
   * @param bccNames      숨은 참조 이름 목록(구분자 ;). 참조 주소와 개수가 같아야 한다.
   * @param subject       메일 제목
   * @param textContents  텍스트 본문
   * @param htmlContents  HTML 본문
   * @param attachPath    첨부파일이 저장된 위치
   * @param attachs       첨부파일명 목록(구분자 ;)
   */
  public void sendMail(String fromAddr, String fromName, String toAddrs, String toNames,
                       String ccAddrs, String ccNames, String bccAddrs, String bccNames,
                       String subject, String textContents, String htmlContents,
                       String attachPath, String attachs)
      throws MessagingException, UnsupportedEncodingException {
    this.fromAddr = fromAddr;
    this.fromName = fromName;
    this.toAddrs = toAddrs;
    this.toNames = toNames;
    this.ccAddrs = ccAddrs;
    this.ccNames = ccNames;
    this.bccAddrs = bccAddrs;
    this.bccNames = bccNames;
    this.subject = subject;
    this.textContents = textContents;
    this.htmlContents = htmlContents;
    this.attachPath = attachPath;
    this.attachs = attachs;
    
//    StringBuffer buf = new StringBuffer();
//    buf.append( "<pre>\n" );
//    buf.append( this.getClass().getName() + " -\n" );
//    buf.append( "  mailHost : " + this.mailHost + "\n" );
//    buf.append( "  mailHostID : " + this.mailHostID + "\n" );
//    buf.append( "  mailHostPass : " + this.mailHostPass + "\n" );
//    buf.append( "  fromAddr : " + this.fromAddr + "\n" );
//    buf.append( "  fromName : " + this.fromName + "\n" );
//    buf.append( "  toAddrs : " + this.toAddrs + "\n" );
//    buf.append( "  toNames : " + this.toNames + "\n" );
//    buf.append( "  ccAddrs : " + this.ccAddrs + "\n" );
//    buf.append( "  ccNames : " + this.ccNames + "\n" );
//    buf.append( "  bccAddrs : " + this.bccAddrs + "\n" );
//    buf.append( "  bccNames : " + this.bccNames + "\n" );
//    buf.append( "  subject : " + this.subject + "\n" );
//    buf.append( "  textContents : " + this.textContents + "\n" );
//    buf.append( "  htmlContents : " + this.htmlContents + "\n" );
//    buf.append( "  attachPath : " + this.attachPath + "\n" );
//    buf.append( "  attachs : " + this.attachs + "\n" );
//    buf.append( "</pre>" );
//    System.out.println( buf.toString() );

    // 1. process mail parameters
    String[] toAddr = StringUtil.stringTokenizer( toAddrs, ";" );
    String[] toName = StringUtil.stringTokenizer( toNames, ";" );

    String[] ccAddr = StringUtil.stringTokenizer( ccAddrs, ";" );
    String[] ccName = StringUtil.stringTokenizer( ccNames, ";" );

    String[] bccAddr = StringUtil.stringTokenizer( bccAddrs, ";" );
    String[] bccName = StringUtil.stringTokenizer( bccNames, ";" );
    File[] attach = null;

    String[] attachName = StringUtil.stringTokenizer( attachs, ";" );
    if( attachPath != null && attachName != null && attachName.length > 0 ) {
      ArrayList tmp = new ArrayList();
      for( int i = 0; i < attachName.length; i++ ) {
        File file = new File( attachPath + File.separator + attachName[i] );
        if( file.exists() && file.isFile() ) tmp.add( file );
      }
      attach = new File[ tmp.size() ];
      tmp.toArray( attach );
    }

    // 2. set mail
    Session mailSession = null;
    Properties props = new Properties();
    props.put( "mail.smtp.host", this.mailHost );
    if( this.mailHostID != null && !this.mailHostID.equals( "" ) ) {
      props.put("mail.smtp.auth", "true");
      javax.mail.Authenticator auth = new MyAuthenticator( this.mailHostID, this.mailHostPass );
      mailSession = Session.getInstance( props, auth );
    } else {
      mailSession = Session.getDefaultInstance( props, null );
    }
    mailSession.setDebug( false );

    MimeMessage msg = new MimeMessage( mailSession );

    // 2.1 set send date
    msg.setSentDate( new Date() );

    // 2.2 set from
    if( fromName != null ) {
      msg.setFrom( new InternetAddress( fromAddr, fromName ) );
    } else {
      msg.setFrom( new InternetAddress( fromAddr ) );
    }

    // 2.3 set recipient
    if( toAddr != null && toAddr.length > 0 ) {
      InternetAddress[] to = new InternetAddress[ toAddr.length ];
      for( int i = 0; i < toAddr.length; i++ ) {
        if( toName != null && toName.length >= i+1 ) {
          to[i] = new InternetAddress( toAddr[i], toName[i] );
        } else {
          to[i] = new InternetAddress( toAddr[i] );
        }
      }
      msg.setRecipients( Message.RecipientType.TO, to );
    }

    // 2.4 set cc
    if( ccAddr != null && ccAddr.length > 0 ) {
      InternetAddress[] cc = new InternetAddress[ ccAddr.length ];
      for( int i = 0; i < ccAddr.length; i++ ) {
        if( ccName != null && ccName.length >= i+1 ) {
          cc[i] = new InternetAddress( ccAddr[i], ccName[i] );
        } else {
          cc[i] = new InternetAddress( ccAddr[i] );
        }
      }
      msg.setRecipients( Message.RecipientType.CC, cc );
    }

    // 2.5 set bcc
    if( bccAddr != null && bccAddr.length > 0 ) {
      InternetAddress[] bcc = new InternetAddress[ bccAddr.length ];
      for( int i = 0; i < bccAddr.length; i++ ) {
        if( bccName != null && bccName.length >= i+1 ) {
          bcc[i] = new InternetAddress( bccAddr[i], bccName[i] );
        } else {
          bcc[i] = new InternetAddress( bccAddr[i] );
        }
      }
      msg.setRecipients( Message.RecipientType.BCC, bcc );
    }

    // 2.6 set subject
    msg.setSubject( subject, "euc-kr" );

    // 2.7 set contents

    MimeMultipart mp = new MimeMultipart();

    // 2.7.1 set textContents
    if( textContents != null ) {

      MimeBodyPart mbpT = new MimeBodyPart();
      mbpT.setDataHandler( new DataHandler( new ByteArrayDataSource(
          textContents, "text/plain; charset=euc-kr" ) ) );
      mbpT.setHeader("Content-Transfer-Encoding", "7bit");
      mp.addBodyPart(mbpT);
    }

    // 2.7.2 set htmlContents
    if( htmlContents != null ) {

      MimeBodyPart mbpH = new MimeBodyPart();
      mbpH.setDataHandler( new DataHandler( new ByteArrayDataSource(
          htmlContents, "text/html; charset=euc-kr" ) ) );
      mbpH.setHeader("Content-Transfer-Encoding", "7bit");
      mp.addBodyPart(mbpH);
    }

    // 2.7.3 set attachments
    for( int i = 0; attach != null && i < attach.length; i++ ) {
      if( attach[i].exists() ) {
        MimeBodyPart mbpA = new MimeBodyPart();
        mbpA.setDataHandler( new DataHandler( new FileDataSource( attach[i] ) ) );
        mbpA.setFileName( StringUtil.toEng( attach[i].getName() ) );
        mp.addBodyPart( mbpA );
      }
    }

    msg.setContent(mp);

    // 3. send mail
    Transport.send(msg);

    // 4. delete attach files
    //for( int i = 0; attach != null && i < attach.length; i++ ) attach[i].delete();
  }

  public String toString()  {
    StringBuffer buf = new StringBuffer();
    buf.append( "<pre>\n" );
    buf.append( this.getClass().getName() + " -\n" );
    buf.append( "  mailHost : " + this.mailHost + "\n" );
    buf.append( "  mailHostID : " + this.mailHostID + "\n" );
    buf.append( "  mailHostPass : " + this.mailHostPass + "\n" );
    buf.append( "  fromAddr : " + this.fromAddr + "\n" );
    buf.append( "  fromName : " + this.fromName + "\n" );
    buf.append( "  toAddrs : " + this.toAddrs + "\n" );
    buf.append( "  toNames : " + this.toNames + "\n" );
    buf.append( "  ccAddrs : " + this.ccAddrs + "\n" );
    buf.append( "  ccNames : " + this.ccNames + "\n" );
    buf.append( "  bccAddrs : " + this.bccAddrs + "\n" );
    buf.append( "  bccNames : " + this.bccNames + "\n" );
    buf.append( "  subject : " + this.subject + "\n" );
    buf.append( "  textContents : " + this.textContents + "\n" );
    buf.append( "  htmlContents : " + this.htmlContents + "\n" );
    buf.append( "  attachPath : " + this.attachPath + "\n" );
    buf.append( "  attachs : " + this.attachs + "\n" );
    buf.append( "</pre>" );

    return buf.toString();
  }

  /* This class implements a typed DataSource from :
  *  an InputStream
  *  a byte array
  *  a String
   */
  class ByteArrayDataSource implements DataSource {
    private byte[] data; // data
    private String type; // content-type

    /* Create a datasource from an input stream */
    ByteArrayDataSource( InputStream is, String type ) {
      this.type = type;
      try {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int ch;

        while ( ( ch = is.read() ) != -1 )
          // XXX : must be made more efficient by
          // doing buffered reads, rather than one byte reads
          os.write( ch );
        data = os.toByteArray();

        } catch( IOException e ) {}
    }

    /* Create a datasource from a byte array */
    ByteArrayDataSource( byte[] data, String type ) {
      this.data = data;
      this.type = type;
    }

    /* Create a datasource from a String */
    ByteArrayDataSource(String data, String type) {
      try {
        // Assumption that the string contains only ascii
        // characters ! Else just pass in a charset into this
        // constructor and use it in getBytes()
        this.data = data.getBytes( "KSC5601" );
        //this.data = data.getBytes();
        } catch( UnsupportedEncodingException e ) {}
        //} catch (Exception uex) { }
        this.type = type;
    }

    public InputStream getInputStream() throws IOException {
      if ( data == null ) throw new IOException( "no data" );
      return new ByteArrayInputStream( data );
    }

    public OutputStream getOutputStream() throws IOException {
      throw new IOException( "cannot do this" );
    }

    public String getContentType() {
      return type;
    }

    public String getName() {
      return "dummy";
    }
  }

  class MyAuthenticator extends javax.mail.Authenticator {
    javax.mail.PasswordAuthentication pa;

    public MyAuthenticator( String mailHostID, String mailHostPass ) {
      pa = new javax.mail.PasswordAuthentication( mailHostID, mailHostPass );
    }

    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
      return pa;
    }
  }
}
