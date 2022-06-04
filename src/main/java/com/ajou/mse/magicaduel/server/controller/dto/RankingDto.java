package com.ajou.mse.magicaduel.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RankingDto {

	private int ranking;

	@Builder
	public RankingDto(int ranking) {
		this.ranking = ranking;
	}
}
