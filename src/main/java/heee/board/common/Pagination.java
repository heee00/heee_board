package heee.board.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

	private int rowCount  = 10;      // 한 페이지 당 보여줄 게시물 개수
    private int pageCount = 5;       // 한 블럭에 몇 개의 페이지 개수
    private int totalCount;          // 총 게시물 개수
    private int nowPage;             // 현재 페이지

    private int startPage = 1;       // 한 블럭의 시작 페이지
    private int endPage;             // 한 블럭의 끝 페이지

    private int totalPageCount;      // 총 페이지 개수

    private boolean isPrev = false;  // 다음 페이지로 이동하는 버튼 유무
    private boolean isNext = false;  // 이전 페이지로 이동하는 버튼 유무
    private int offset;              // 얼만큼 끊어서 가져올 것인가

    private String searchName;
    private String searchType;
    
    public Pagination() {
    	
    }
    
    public Pagination(int totalCount, int nowPage) {
    	this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    	this.startPage = startPage + (((nowPage - startPage) / pageCount) * pageCount);
    	this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    	this.isPrev = 1 < ((nowPage * 1.0) / pageCount);
    	this.isNext = endPage < totalPageCount;
    	this.offset = (nowPage - 1) * rowCount;
    }

    public Pagination getPagination() {
        return this;
    }
    
}
