package heee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import heee.board.common.Pagination;
import heee.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	// 게시판 리스트 + 페이징
	List<BoardDTO> getBoard(Pagination pagination) throws Exception;
	// 게시글 수 세기
	int getCount() throws Exception;
	// 검색 조건에 맞는 페이징 처리된 게시물 목록 조회
	List<BoardDTO> getBoardSearch(String searchType, String searchName, Pagination pagination) throws Exception;
	// 검색 결과 게시물 수 조회
	int getSearchCount(String searchType, String searchName) throws Exception;
	// 게시글 작성하기
	void insertBoard(BoardDTO dto) throws Exception;
	// 게시글 조회수 증가하기
	void viewCount(int boardId) throws Exception;
	// 게시글 상세내용 보기
	BoardDTO selectBoard(int boardId) throws Exception;
	// 게시글 수정하기
	void updateBoard(BoardDTO dto) throws Exception;
	// 게시글 삭제하기
	void deleteBoard(int boardId) throws Exception;
	
}
