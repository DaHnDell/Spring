package com.kcanmin.member_post.dto;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Criteria extends HttpServlet{
	private int page = 1; // 시작 페이지 
	private int amount = 10; // 한 페이지당 보여줄 게시글의 갯수
	private int category = 2; // 페이지 이동 중 항상 동반해야 할 vo, 추가적으로 조회를 할 때에도 필요함.
	private String type;
	private String keyword;
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", amount=" + amount + ", type = " + type + ", keword = " + keyword + "]";
	}
	
	public int getOffset() {
		return (page - 1) * amount;
	}
	
	// request 분석 후 필드 초기화
	// 게시판에 들어가는 쿼리스트링은 항상 get 방식으로 가는 게 맞음.
	
	public Criteria (HttpServletRequest req) {
		// 수집값이 있냐 없냐에 따른 기본값 저장
//		String[] fieldNames = {"page", "amount", "category", "type", "keyword"};
		if(req == null) return;
		Field[] fields = getClass().getDeclaredFields();
		for(Field field : fields) {
			String tmp = req.getParameter(field.getName());
//			System.out.print(field.getType() + ":::" );
//			System.out.print((field.getType() == String.class) + ":::");
//			System.out.println(field.getType() == int.class);
			if(tmp != null && !tmp.equals("")) {
				try {
					Object obj = tmp;
					if(field.getType() == int.class) {
						obj = Integer.parseInt(tmp);
					}
					field.set(this, obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// QueryStyring Generator (will be used at el)
	public static void main(String[] args) {
		System.out.println(new Criteria().getQs());
	}
	
	// Page Include
	public String getQs2() {
		return "page=" + page + "&" + getQs();
	}
	
	
	// Page disInclude
	public String getQs() {
		Field[] fields = getClass().getDeclaredFields();
		String[] strs = new String[4];
		Stream.of(fields).filter(f->!f.getName().equals("page")).map(f -> {
			String nullString = null;
			try {
				nullString = f.getName() + "=" + (f.get(this) == null ? "" : f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return nullString;
		}).collect(Collectors.toList()).toArray(strs);
		return String.join("&", strs);
	}
	
	public String[] getTypeArr() {
		return type.split("");
	}
}
