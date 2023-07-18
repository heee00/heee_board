package heee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import heee.board.common.Pagination;
import heee.board.dto.BoardDTO;
import heee.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> getBoard(Pagination pagination) throws Exception {
		if (pagination.getSearchType() != null && pagination.getSearchName() != null && !pagination.getSearchName().isEmpty()) {
	        // 검색 조건이 있을 경우
	        return boardMapper.getBoardSearch(pagination.getSearchType(), pagination.getSearchName(), pagination);
	    } else {
	        // 검색 조건이 없을 경우
	        return boardMapper.getBoard(pagination);
	    }
	}
	
	@Override
	public int getCount() throws Exception {
	    return boardMapper.getCount();
	}
	
	@Override
	public List<BoardDTO> getBoardSearch(String searchType, String searchName, Pagination pagination) throws Exception {
		return boardMapper.getBoardSearch(searchType, searchName, pagination);
	}
	
	@Override
	public int getSearchCount(String searchType, String searchName) throws Exception {
		if (searchType != null && searchName != null && !searchName.isEmpty()) {
			return boardMapper.getSearchCount(searchType, searchName);
		} else {
			return boardMapper.getCount();
		}
	}
	
	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		boardMapper.insertBoard(dto);
	}
	
	@Override
	public BoardDTO selectBoard(int boardId) throws Exception {
		boardMapper.viewCount(boardId);
		BoardDTO dto = boardMapper.selectBoard(boardId);
		
		return dto;
	}
	
	@Override
	public void updateBoard(BoardDTO dto) throws Exception {
		boardMapper.updateBoard(dto);
	}
	
	@Override
	public void deleteBoard(int boardId) throws Exception {
		boardMapper.deleteBoard(boardId);
	}

}
