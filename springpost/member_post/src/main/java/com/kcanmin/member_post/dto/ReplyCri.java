package com.kcanmin.member_post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyCri {
	private Long lastRno = 0L;
	private int amount = 10;
	
	// URI 정보를 통해서 해석하게 하고, 컨트롤러가 이를 통제해서
	// 각각의 정보에 따른 목록의 조회에서 PATH VARIABLE들에 대한 다양한 유동적 처리.
	// 이러한 설계
	
}
