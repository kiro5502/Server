package com.ajou.mse.magicaduel.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.controller.dto.RankingDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.service.RankingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking/")
public class RankingController {

	private final RankingService rankingService;

	@PostMapping("reader-board")
	public List<User> loadReaderBoard(@RequestBody RankingDto rankingDto) {
		rankingService.PlayerRanking(rankingDto.getId());
		return rankingService.ReaderBoardRanking(rankingDto.getPageStartNum(), rankingDto.getPageEndNum());
	}
}
