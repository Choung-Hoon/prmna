/*
 * @(#)MgrFactory.java
 */
package lee.manager;


/**
 * Manager 팩토리
 *
 * @author sjju
 * @version 1.0
 */
public class MgrFactory {

	public static MgrFactory getMgrFactory() {
		return new MgrFactory();
	}

	/**
	 * 관리자
	 * @return
	 */
	public AdminMgr getAdminMgr() {
		return new AdminMgr();
	}
	
	/**
	 * 장학사업, 장학사업 배정인원, 장학사업 대상자, 장학금신청, 장학금신청 가족사항, 장학금신청 대학원
	 * @return
	 */
	public ScholarMgr getScholarMgr() {
		return new ScholarMgr();
	}
	
	/**
	 * 설문, 설문참여, 배너(팝업존)
	 * @return
	 */
	public ServiceMgr getServiceMgr() {
		return new ServiceMgr();
	}

	
	/**
	 * 게시판, 게시물파일, 댓글
	 * @return
	 */
	public BoardMgr getBoardMgr() {
		return new BoardMgr();
	}

	/**
	 * 우편번호, 공통코드
	 * @return
	 */
	public CommonMgr getCommonMgr() {
		return new CommonMgr();
	}
	
	
	/**
     * 사용자
     * @return
     */
    public MemberMgr getMemberMgr() {
        return new MemberMgr();
    }
    
    /**
     * 엑셀
     * @return
     */
    public ExcelDataMgr getExcelDataMgr() {
    	
        return new ExcelDataMgr();
    }
    
    /**
     * 상품
     * @return
     */
    public ProductMgr getProductMgr() {
    	
        return new ProductMgr();
    }
           
}