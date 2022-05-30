package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingUser {

	private String nickname;
	private Integer score;
	private Integer win;
	private Integer lose;
	private Integer draw;

	@Builder
	public RankingUser(String nickname, Integer score, Integer win, Integer lose, Integer draw) {
		this.nickname = nickname;
		this.score = score;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
	}
}
