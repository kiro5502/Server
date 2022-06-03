package com.ajou.mse.magicaduel.server.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LeaderBoardDto {

	private List<UserResponseDto> users;
	private long totalCount;

	@Builder
	public LeaderBoardDto(List<UserResponseDto> users, long totalCount) {
		this.users = users;
		this.totalCount = totalCount;
	}
}
