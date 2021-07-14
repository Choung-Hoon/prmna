/*
 * @(#)MemberMgr.java
 */
package lee.manager;

import java.util.List;
import java.util.Map;

//import javax.xml.ws.Response;

import sun.misc.Request;


import lee.bean.Member;
import lee.dao.MemberDao;
import lee.common.util.ExceptionUtil;
import lee.manager.MgrException;


/**
 * Member
 *
 * @author msShim
 * @version 1.0
 */
public class MemberMgr extends MgrBase {

	private MemberDao memberDao;

	public MemberMgr() {
		super();
		memberDao = new MemberDao(sqlMap);
	}

	public int getMemberListCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            
            String user_id = "";
            String user_name = "";
            String search_type = "";
            String pageNum = "";
            String listSize = "";
            int limitStartNum = 0;
            int limitEndNum = 0;
            String sDate = "";
            String eDate = "";
            
            
            user_id = (String)map.get("userID");
            user_name = (String)map.get("userName");
            pageNum = (String)map.get("pageNum").toString();
            listSize = (String)map.get("listSize").toString();
            
            sDate = (String)map.get("sDate");
            eDate = (String)map.get("eDate");
            
            
            if(user_id != "")  search_type = "userid";
            if(user_name != "") search_type = "name";
            if(user_id != "" && user_name !="") search_type = "all";
            
            
            if(pageNum != ""){
                limitStartNum = Integer.parseInt(pageNum);
            }else{
                limitStartNum =0;
            }
            
            if(listSize != ""){
                limitEndNum = Integer.parseInt(listSize);
            }else{
                limitEndNum = 15;
            }
            
            if(limitStartNum < 0 ) limitStartNum= 0;
            if(limitEndNum < 0 ) limitEndNum= 10;
            
            
            
            map.put("searchType",search_type);
            map.put("limitStartNum",limitStartNum);
            map.put("limitEndNum",limitEndNum);
            
            
            
            cnt = memberDao.getMemberListCnt(map);
            
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
    
    public Member[] getMemberList(Map map) throws Exception {
        List<Member> arrData = null;
        Member[] result = null;
        
        try {
            String user_id = "";
            String user_name = "";
            String search_type = "";
            String pageNum = "";
            String listSize = "";
            int limitStartNum = 0;
            int limitEndNum = 0;
            String sDate = "";
            String eDate = "";
            
            
            user_id = (String)map.get("userID");
            user_name = (String)map.get("userName");
            pageNum = (String)map.get("pageNum").toString();
            listSize = (String)map.get("listSize").toString();
            
            sDate = (String)map.get("sDate");
            eDate = (String)map.get("eDate");
            
            
            if(user_id != "")  search_type = "userid";
            if(user_name != "") search_type = "name";
            if(user_id != "" && user_name !="") search_type = "all";
            
            
            if(pageNum != ""){
                limitStartNum = Integer.parseInt(pageNum);
            }else{
                limitStartNum =0;
            }
            
            if(listSize != ""){
                limitEndNum = Integer.parseInt(listSize);
            }else{
                limitEndNum = 15;
            }
            
            if(limitStartNum < 0 ) limitStartNum= 0;
            if(limitEndNum < 0 ) limitEndNum= 10;
            
            
            map.put("searchType",search_type);
            map.put("limitStartNum",limitStartNum);
            map.put("limitEndNum",limitEndNum);
            
            
            arrData = (List<Member>) memberDao.getMemberList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Member[arrData.size()];
        arrData.toArray(result);
        return result;
    }
    
    
   
    public Member getMember(Map map) throws Exception {
        Member result = null;
        
        try {
            result = memberDao.getMember(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
    
    
    public Member getMemberForLogin(Map map) throws Exception {
        Member result = null;
        
        try {
            result = memberDao.getMemberForLogin(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
       
    
    public int getRetireMemberListCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            
            String user_id = "";
            String user_name = "";
            String search_type = "";
            String pageNum = "";
            String listSize = "";
            int limitStartNum = 0;
            int limitEndNum = 0;
            String sDate = "";
            String eDate = "";
            
            
            user_id = (String)map.get("userID");
            user_name = (String)map.get("userName");
            pageNum = (String)map.get("pageNum").toString();
            listSize = (String)map.get("listSize").toString();
            
            sDate = (String)map.get("sDate");
            eDate = (String)map.get("eDate");
            
            
            if(user_id != "")  search_type = "userid";
            if(user_name != "") search_type = "name";
            if(user_id != "" && user_name !="") search_type = "all";
            
            
            if(pageNum != ""){
                limitStartNum = Integer.parseInt(pageNum) - 1;
            }else{
                limitStartNum =0;
            }
            
            if(listSize != ""){
                limitEndNum = Integer.parseInt(listSize);
            }else{
                limitEndNum = 15;
            }
            
            if(limitStartNum < 0 ) limitStartNum= 0;
            if(limitEndNum < 0 ) limitEndNum= 0;
            
            
            
            map.put("searchType",search_type);
            map.put("limitStartNum",limitStartNum);
            map.put("limitEndNum",limitEndNum);
            
            cnt = memberDao.getRetireMemberListCnt(map);
            
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
    
    public Member[] getRetireMemberList(Map map) throws Exception {
        List<Member> arrData = null;
        Member[] result = null;
        
        try {
            String user_id = "";
            String user_name = "";
            String search_type = "";
            String pageNum = "";
            String listSize = "";
            int limitStartNum = 0;
            int limitEndNum = 0;
            String sDate = "";
            String eDate = "";
            
            
            user_id = (String)map.get("userID");
            user_name = (String)map.get("userName");
            pageNum = (String)map.get("pageNum").toString();
            listSize = (String)map.get("listSize").toString();
            
            sDate = (String)map.get("sDate");
            eDate = (String)map.get("eDate");
            
            
            if(user_id != "")  search_type = "userid";
            if(user_name != "") search_type = "name";
            if(user_id != "" && user_name !="") search_type = "all";
            
            
            if(pageNum != ""){
                limitStartNum = Integer.parseInt(pageNum) - 1;
            }else{
                limitStartNum =0;
            }
            
            if(listSize != ""){
                limitEndNum = Integer.parseInt(listSize);
            }else{
                limitEndNum = 15;
            }
            
            if(limitStartNum < 0 ) limitStartNum= 0;
            if(limitEndNum < 0 ) limitEndNum= 0;
            
            
            
            map.put("searchType",search_type);
            map.put("limitStartNum",limitStartNum);
            map.put("limitEndNum",limitEndNum);
            
            
            arrData = (List<Member>) memberDao.getRetireMemberList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Member[arrData.size()];
        arrData.toArray(result);
        return result;
    }
  
    public void insertMember(Member member) throws Exception {
        try {
            sqlMap.startTransaction();
            memberDao.insertMember(member);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
 
    public void updateMember(Member member) throws Exception {
        try {
            sqlMap.startTransaction();
            memberDao.updateMember(member);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
       
    public void deleteMember(Map map) throws Exception {
        try {
            sqlMap.startTransaction();
            memberDao.deleteMember(map);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
    public List<Member> getUserListForExcel(Map map) throws Exception {

        List<Member> result = null;

        try {
            result = memberDao.getUserListForExcel(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
    
    public List<Member> getUserListRetireForExcel(Map map) throws Exception {

        List<Member> result = null;

        try {
            result = memberDao.getUserListRetireForExcel(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
    
    public int getNewMemberMainCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            cnt = memberDao.getNewMemberMainCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
    
    public int getRetireMemberMainCnt(Map map) throws Exception {
        Integer cnt = 0;
        try {
            cnt = memberDao.getRetireMemberMainCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return cnt.intValue();
    }
    
    public void updateRetireMember(Map map) throws Exception {
        try {
            sqlMap.startTransaction();
            memberDao.updateRetireMember(map);  
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
    
    /*
        임시 비번으로 변경하기.
    */
    
    public void updateFindPWD(Map map) throws Exception {
        try {
            sqlMap.startTransaction();
            memberDao.updateFindPWD(map);  
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
    /*
        아이디 알아오기
   */
    
    public Member getFindID(Map map) throws Exception {
        Member result = null;
        
        try {
            
            result = memberDao.getFindID(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return result;
    } 
    /*
        아이디 알아오기 2
     */
    
    public Member getFindUser2(Map map) throws Exception {
        Member result = null;
        
        try {
            
            result = memberDao.getFindUser2(map);
        } catch (Exception e) { 
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return result;
    }
    /*
        아이디 알아오기 3
    */
   
   public Member getFindUser3(Map map) throws Exception {
       Member result = null;
       
       try {
           
           result = memberDao.getFindUser3(map);
       } catch (Exception e) {
           log.error(ExceptionUtil.getStackTrace(e));
           throw new MgrException(e);
       }
       return result;
   }

   
   public Member getMemberJumin(Map map) throws Exception {
       Member result = null;
       
       try {
           result = memberDao.getMemberJumin(map);
       } catch (Exception e) {
           log.error(ExceptionUtil.getStackTrace(e));
           throw new MgrException(e);
       }
       
       return result;
   }
   
   
    
}
