package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingDto {

	private int ranking;

	@Builder
	public RankingDto(int ranking) {
		this.ranking = ranking;
	}
}
