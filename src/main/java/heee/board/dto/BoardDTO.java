package heee.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDTO {
	private int rownum;
	private int boardId;
	private String title;
	private String content;
	private int views;
	private LocalDateTime writeTime;
	private String writer;
	private LocalDateTime modifyTime;
	private String modifier;
	
}
