package com.ajou.mse.magicaduel.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.RankingDto;
import com.ajou.mse.magicaduel.server.service.RankingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking/")
public class RankingController {

	private final RankingService rankingService;

	@GetMapping("leader-board")
	public RankingDto leaderBoard(@RequestParam int page) {
		return new RankingDto(rankingService.getLeaderBoard(page));
	}

	@GetMapping("player-ranking")
	public int playerRanking() {
		return rankingService.getPlayerRanking();
	}
}
