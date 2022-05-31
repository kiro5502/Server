package com.ajou.mse.magicaduel.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.LeaderBoardDto;
import com.ajou.mse.magicaduel.server.controller.dto.RankingDto;
import com.ajou.mse.magicaduel.server.service.RankingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking/")
public class RankingController {

	private final RankingService rankingService;

	@GetMapping("leader-board")
	public LeaderBoardDto leaderBoard(@RequestParam int page) {
		return rankingService.getLeaderBoard(page);
	}

	@GetMapping("player-ranking")
	public RankingDto playerRanking() {
		return rankingService.getPlayerRanking();
	}
}
