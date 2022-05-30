package com.ajou.mse.magicaduel.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.RankingDataDto;
import com.ajou.mse.magicaduel.server.controller.dto.RankingDto;
import com.ajou.mse.magicaduel.server.service.RankingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking/")
public class RankingController {

	private final RankingService rankingService;

	@PostMapping("leader-board")
	public RankingDataDto loadReaderBoard(@RequestBody RankingDto rankingDto) {
		return rankingService.leaderBoardRanking(rankingDto.getPageNum());
	}

	@PostMapping("player-ranking")
	public int playerRankingLoad(@RequestBody RankingDto rankingDto) {
		return rankingService.getRanking(rankingDto.getId());
	}
}
