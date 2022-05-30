package com.ajou.mse.magicaduel.server.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingDto {

	private List<RankingUser> users;

	@Builder
	public RankingDto(List<RankingUser> users) {
		this.users = users;
	}
}
