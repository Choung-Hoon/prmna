package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Board;
import lee.bean.BoardFile;
import lee.bean.BoardComment;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Board Data Access Object
 */
public class BoardDao extends SqlMapBase {
	SqlMapClient sqlMapClient;

	public BoardDao(SqlMapClient sqlMapClient){
		this.sqlMapClient = sqlMapClient;
	}

	//--- board
	public Integer getBoardListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.board.getBoardListCnt", map);
    }
	
	public Integer getSearchBoardListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.board.getSearchBoardListCnt", map);
    }
	
	public Integer getBoardMaxId() throws Exception{
	    return (Integer)sqlMapClient.queryForObject("lee.board.getBoardMaxId");
	}
	
	public void updateBoardPlusLevel(Board board) throws Exception {
	    sqlMapClient.update("lee.board.updateBoardPlusLevel", board);
	}
	
	public void updateBoardMinusLevel(Board board) throws Exception {
        sqlMapClient.update("lee.board.updateBoardMinusLevel", board);
    }
	
	public List<Board> getBoardList(Map map) throws Exception{
	    return (List<Board>)sqlMapClient.queryForList("lee.board.getBoardList", map);
	}
	
	public List<Board> getBoardImgList(Map map) throws Exception{
        return (List<Board>)sqlMapClient.queryForList("lee.board.getBoardImgList", map);
    }
	
	public List<Board> getSearchBoardList(Map map) throws Exception{
        return (List<Board>)sqlMapClient.queryForList("lee.board.getSearchBoardList", map);
    }
	
	public Board getBoard(Map map)throws Exception{
        return (Board)sqlMapClient.queryForObject("lee.board.getBoard", map);
    }
	
	public Board getNextBoard(Map map)throws Exception{
        return (Board)sqlMapClient.queryForObject("lee.board.getNextBoard", map);
    }
	
	public Board getPrevBoard(Map map)throws Exception{
        return (Board)sqlMapClient.queryForObject("lee.board.getPrevBoard", map);
    }
	
	public void insertBoard(Board board)throws Exception{
        sqlMapClient.insert("lee.board.insertBoard", board);
    }
	
	public void updateBoard(Board board)throws Exception{
        sqlMapClient.update("lee.board.updateBoard", board);
    }
		
	public void updateBoardViewCnt(Map map)throws Exception{
	    sqlMapClient.update("lee.board.updateBoardViewCnt", map);
	}
	
	public void deleteBoard(int contId)throws Exception{
        sqlMapClient.delete("lee.board.deleteBoard", contId);
    }
	
	
	//-- board file
	public List<BoardFile> getBoardFileList(int contId) throws Exception{
        return (List<BoardFile>)sqlMapClient.queryForList("lee.board.getBoardFileList", contId);
    }
	
	public void insertBoardFile(BoardFile boardFile)throws Exception{
        sqlMapClient.insert("lee.board.insertBoardFile", boardFile);
    }
    
    public void updateBoardFile(BoardFile boardFile)throws Exception{
        sqlMapClient.update("lee.board.updateBoardFile", boardFile);
    }
    
    public void updateBoardFileDescript(BoardFile boardFile)throws Exception{
        sqlMapClient.update("lee.board.updateBoardFileDescript", boardFile);
    }
    
    public void deleteBoardFile(int fileId)throws Exception{
        sqlMapClient.delete("lee.board.deleteBoardFile", fileId);
    }
    
	
	//-- board comment
    public List<BoardComment> getBoardCommentList(int contId) throws Exception{
        return (List<BoardComment>)sqlMapClient.queryForList("lee.board.getBoardCommentList", contId);
    }
       
    public void insertBoardComment(BoardComment boardComment)throws Exception{
        sqlMapClient.insert("lee.board.insertBoardComment", boardComment);
    }
    
    public void updateBoardComment(BoardComment boardComment)throws Exception{
        sqlMapClient.update("lee.board.updateBoardComment", boardComment);
    }
    
    public void deleteBoardComment(int cmtId)throws Exception{
        sqlMapClient.delete("lee.board.deleteBoardComment", cmtId);
    }
    
    public Integer getNewTotalCount()throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.board.newTotalCount");
    }
    
    //--  test	
	public List getTest() throws Exception{
	    return sqlMapClient.queryForList("lee.board.testList");
	}
}
