package com.ajou.mse.magicaduel.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajou.mse.magicaduel.server.annotation.CheckLogin;
import com.ajou.mse.magicaduel.server.annotation.LoginUser;
import com.ajou.mse.magicaduel.server.controller.dto.LeaderBoardDto;
import com.ajou.mse.magicaduel.server.controller.dto.RankingDto;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserDto;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.service.RankingService;
import com.ajou.mse.magicaduel.server.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ranking/")
public class RankingController {

	private final RankingService rankingService;
	private final UserService userService;

	private final int rankingPerPage = 10;

	@GetMapping("leader-board")
	public LeaderBoardDto leaderBoard(@RequestParam int page) {
		int start = (page - 1) * rankingPerPage;
		int end = page * rankingPerPage - 1;

		List<UserResponseDto> users = new ArrayList<>();
		Set<String> rankingList = rankingService.getRankingSet(start, end);
		long totalCount = rankingService.getRankingSize();

		int ranking = start + 1;
		for (String id : rankingList) {
			UserDto userDto = userService.getUserInfo(Long.parseLong(id));

			users.add(new UserResponseDto(userDto, ranking++));
		}

		return new LeaderBoardDto(users, totalCount);
	}

	@GetMapping("player-ranking")
	@CheckLogin
	public RankingDto playerRanking(@LoginUser SessionUser sessionUser) {
		return rankingService.getPlayerRanking(sessionUser);
	}
}
