package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.Admin;
import lee.bean.Board;
import lee.bean.BoardFile;
import lee.bean.BoardComment;
import lee.dao.BoardDao;
import lee.common.util.ExceptionUtil;
import lee.manager.MgrException;

/**
 * Board Manager : TB_BOARD, TB_BOARD_COMMENT, TB_BOARD_FILE
 *
 * @author hylee
 * @version 1.0
 */
public class BoardMgr extends MgrBase {

	private BoardDao boardDao;

	public BoardMgr() {
		super();
		boardDao = new BoardDao(sqlMap);
	}

	//--- board
	public int getBoardListCnt(Map map) throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = boardDao.getBoardListCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
	
	public int getSearchBoardListCnt(Map map) throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = boardDao.getSearchBoardListCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
	
	public int getBoardMaxId() throws Exception {
	    Integer rtnVal = 0;
        
        try {
            rtnVal = boardDao.getBoardMaxId();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return rtnVal.intValue();
	}
	
	public void updateBoardPlusLevel(Board board) throws Exception {
	    try {
            boardDao.updateBoardPlusLevel(board);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
	}
	
	public void updateBoardMinusLevel(Board board) throws Exception {
        try {
            boardDao.updateBoardMinusLevel(board);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
    }
	
	public Board[] getBoardList(Map map) throws Exception {
	    List<Board> arrData = null;
	    Board[] result = null;
	    
	    try {
	        arrData = (List<Board>) boardDao.getBoardList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Board[arrData.size()];
        arrData.toArray(result);
	    return result;
	}
	
	public Board[] getBoardImgList(Map map) throws Exception {
        List<Board> arrData = null;
        Board[] result = null;
        
        try {
            arrData = (List<Board>) boardDao.getBoardImgList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Board[arrData.size()];
        arrData.toArray(result);
        return result;
    }
	
	public Board[] getSearchBoardList(Map map) throws Exception {
        List<Board> arrData = null;
        Board[] result = null;
        
        try {
            arrData = (List<Board>) boardDao.getSearchBoardList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Board[arrData.size()];
        arrData.toArray(result);
        return result;
    }

	public Board getBoard(Map map) throws Exception {
        Board result = null;
        
        try {
            result = boardDao.getBoard(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
	
	public Board getNextBoard(Map map) throws Exception {
        Board result = null;
        try {
            result = boardDao.getNextBoard(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        return result;
    }
	
	public Board getPrevBoard(Map map) throws Exception {
        Board result = null;
        
        try {
            result = boardDao.getPrevBoard(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
	
	public void insertBoard(Board board) throws Exception {
	    try {
            sqlMap.startTransaction();
            boardDao.insertBoard(board);
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
	
	public void updateBoard(Board board) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.updateBoard(board);
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
	
	public void updateBoardViewCnt(Map map) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.updateBoardViewCnt(map);
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
	
	public void deleteBoard(int contId) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.deleteBoard(contId);
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
	
		
	//-- board file
	public BoardFile[] getBoardFileList(int contId) throws Exception {
        List<BoardFile> arrData = null;
        BoardFile[] result = null;
        
        try {
            arrData = (List<BoardFile>)boardDao.getBoardFileList(contId);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new BoardFile[arrData.size()];
        arrData.toArray(result);
        return result;
    }
	
	public void insertBoardFile(BoardFile boardFile) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.insertBoardFile(boardFile);
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
    
    public void updateBoardFile(BoardFile boardFile) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.updateBoardFile(boardFile);
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
    
    public void updateBoardFileDescript(BoardFile boardFile) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.updateBoardFileDescript(boardFile);
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
    
    public void deleteBoardFile(int fileId) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.deleteBoardFile(fileId);
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
    
    
	//-- board comment
    public BoardComment[] getBoardCommentList(int contId) throws Exception {
        List<BoardComment> arrData = null;
        BoardComment[] result = null;
        
        try {
            arrData = (List<BoardComment>)boardDao.getBoardCommentList(contId);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new BoardComment[arrData.size()];
        arrData.toArray(result);
        return result;
    }
    
    public void insertBoardComment(BoardComment boardComment) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.insertBoardComment(boardComment);
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
    
    public void updateBoardComment(BoardComment boardComment) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.updateBoardComment(boardComment);
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
    
    public void deleteBoardComment(int cmtId) throws Exception {
        try {
            sqlMap.startTransaction();
            boardDao.deleteBoardComment(cmtId);
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
	
    public int getNewTotalCount() throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = boardDao.getNewTotalCount();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
    
	//-- test
	public List getTestList() throws Exception {

        List result = null;

        try {
            result = boardDao.getTest();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }

}
