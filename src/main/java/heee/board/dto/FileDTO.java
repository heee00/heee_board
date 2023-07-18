package heee.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDTO {

	private int fileId;
	private int boardId;
	private String fileName;
	private String filePath;
	private long fileSize;
	private String creatorId;
	private LocalDateTime createTime;
}
