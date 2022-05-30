package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingDto {

	private Long id;
	private int pageNum;

	@Builder
	public RankingDto(User entity, int pageNum) {
		this.id = entity.getId();
		this.pageNum = pageNum;
	}
}
