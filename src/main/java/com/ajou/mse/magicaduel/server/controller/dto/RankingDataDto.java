package com.ajou.mse.magicaduel.server.controller.dto;

import java.util.List;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingDataDto {

	private List<User> users;

	@Builder
	public RankingDataDto(List<User> users) {
		this.users = users;
	}
}
