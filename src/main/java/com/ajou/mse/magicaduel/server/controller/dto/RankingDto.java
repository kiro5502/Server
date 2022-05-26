package com.ajou.mse.magicaduel.server.controller.dto;

import com.ajou.mse.magicaduel.server.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingDto {

	private Long id;
	private int pageStartNum;
	private int pageEndNum;

	@Builder
	public RankingDto(User entity, int pageStartNum, int pageEndNum) {
		this.id = entity.getId();
		this.pageEndNum = pageStartNum;
		this.pageEndNum = pageEndNum;
	}
}
