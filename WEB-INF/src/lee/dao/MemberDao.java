package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Board;
import lee.bean.Member;
import lee.common.sqlMap.SqlMapBase;
import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Board Data Access Object
 */
public class MemberDao extends SqlMapBase {
	SqlMapClient sqlMapClient;

	public MemberDao(SqlMapClient sqlMapClient){
		this.sqlMapClient = sqlMapClient;
	}


	public Integer getMemberListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.member.getMemberListCnt", map);
    }
    
    public List<Member> getMemberList(Map map) throws Exception{
        return (List<Member>)sqlMapClient.queryForList("lee.member.getMemberList", map);
    }
    
    
    public Integer getRetireMemberListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.member.getRetireMemberListCnt", map);
    }
    
    public List<Member> getRetireMemberList(Map map) throws Exception{
        return (List<Member>)sqlMapClient.queryForList("lee.member.getRetireMemberList", map);
    }
    
    
   
    public Member getMember(Map map)throws Exception{
        return (Member)sqlMapClient.queryForObject("lee.member.getMember", map);
    }
    
    public Member getMemberForLogin(Map map)throws Exception{
        return (Member)sqlMapClient.queryForObject("lee.member.getMemberForLogin", map);
    }
   
    public void insertMember(Member member)throws Exception{
        sqlMapClient.insert("lee.member.insertMember", member);
    }
    

    public void updateMember(Member member)throws Exception{
        sqlMapClient.update("lee.member.updateMember", member);
    }

    public void deleteMember(Map params)throws Exception{
        sqlMapClient.delete("lee.member.deleteMember", params);
    }
    
    public List<Member> getUserListForExcel(Map map) throws Exception {
        return (List<Member>) sqlMapClient.queryForList("lee.member.getUserListForExcel", map);
    }  
    public List<Member> getUserListRetireForExcel(Map map) throws Exception {
        return (List<Member>) sqlMapClient.queryForList("lee.member.getUserListRetireForExcel", map);
    }
    public Integer getNewMemberMainCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.member.getNewMemberMainCnt", map);
    }
    public Integer getRetireMemberMainCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.member.getRetireMemberMainCnt", map);
    }
    public void updateRetireMember(Map map)throws Exception{
        sqlMapClient.update("lee.member.updateRetireMember", map); 
    }
    public Member getFindID(Map map) throws Exception {
        return (Member) sqlMapClient.queryForObject("lee.member.getFindID", map);        
    }
    public Member getFindUser2(Map map) throws Exception {
        return (Member) sqlMapClient.queryForObject("lee.member.getFindUser2", map);        
    }
    public Member getFindUser3(Map map) throws Exception {
        return (Member) sqlMapClient.queryForObject("lee.member.getFindUser3", map);        
    }
    public void updateFindPWD(Map map)throws Exception{
        sqlMapClient.update("lee.member.updateFindPWD", map); 
    } 
    
    public Member getMemberJumin(Map map)throws Exception{
        return (Member)sqlMapClient.queryForObject("lee.member.getMemberJumin", map);
    }
    
}
