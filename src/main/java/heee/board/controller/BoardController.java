package heee.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import heee.board.common.Pagination;
import heee.board.dto.BoardDTO;
import heee.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public ModelAndView getBoard(@RequestParam(value = "searchType", defaultValue = "title") String searchType,
								@RequestParam(value = "searchName", defaultValue = "") String searchName,
					            @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		
		Pagination pagination = new Pagination(boardService.getCount(), page); 
		List<BoardDTO> list = boardService.getBoard(pagination);
		ModelAndView mv = new ModelAndView("boardList");	
	
		if (!searchName.isEmpty()) {
			// 검색 내용이 있는 경우
		    pagination.setTotalCount(boardService.getSearchCount(searchType, searchName));
		    pagination = new Pagination(pagination.getTotalCount(), page);
		    list = boardService.getBoardSearch(searchType, searchName, pagination);
		} else {
		    // 검색 내용이 없는 경우
		    pagination.setTotalCount(boardService.getCount());
		    pagination = new Pagination(pagination.getTotalCount(), page);
		    list = boardService.getBoard(pagination);
		}
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		mv.addObject("searchType", searchType);
		mv.addObject("searchName", searchName);
		mv.addObject("pagination", pagination);
		
		return mv;
	}
	
	@GetMapping("/write")
	public String writeBoard() throws Exception {
		return "boardWrite";
	}
	
	@PostMapping("/insert")
	public String insertBoard(BoardDTO dto)throws Exception {
		boardService.insertBoard(dto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/select")
	public ModelAndView selectBoard(@RequestParam int boardId) throws Exception {
		ModelAndView mv = new ModelAndView("/boardDetail");
		BoardDTO dto = boardService.selectBoard(boardId);
		mv.addObject("board", dto);
		
		return mv;
	}
	
	@GetMapping("/update")
	public String updateBoard(@RequestParam int boardId, Model model) throws Exception {
		BoardDTO dto = boardService.selectBoard(boardId);
		model.addAttribute("board", dto);
		return "boardUpdate";
	}
	
	@PostMapping("/update")
	public String updateBoard(BoardDTO dto) throws Exception {
		boardService.updateBoard(dto);
		return "redirect:/board/list";
	}
	
	@PostMapping("/delete")
	public String deleteBoard(int boardId) throws Exception {
		boardService.deleteBoard(boardId);
		return "redirect:/board/list";
	}
}
