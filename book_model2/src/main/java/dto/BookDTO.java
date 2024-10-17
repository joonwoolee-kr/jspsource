package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class BookDTO {
	// 테이블과 동일한 모양
	private int code;
	private String title;
	private String writer;
	private int price;
	private String description;
}
