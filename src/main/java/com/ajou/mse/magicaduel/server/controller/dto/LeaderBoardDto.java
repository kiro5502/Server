package com.ajou.mse.magicaduel.server.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LeaderBoardDto {

	private List<UserResponseDto> users;

	@Builder
	public LeaderBoardDto(List<UserResponseDto> users) {
		this.users = users;
	}
}
