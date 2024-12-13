package com.kcanmin.member_post.sample;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
	Kind kind;
	
	public static void main(String[] args) {
//		Card card = new Card(Kind.CLOVER);
//		System.out.println(card.kind.ordinal());
//		System.out.println(Kind.DIAMOND.ordinal());

		Kind[] kinds = Kind.values();
		System.out.println(Arrays.toString(kinds));
		for(Kind k : kinds) {
			System.out.println(k.getName());
		}
	}
}
