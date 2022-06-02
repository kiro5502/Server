package com.ajou.mse.magicaduel.server.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajou.mse.magicaduel.server.controller.dto.BattleResultDto;
import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.util.BattleResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BattleService {

	private final UserRepository userRepository;
	private final UserService userService;
	private final RankingService rankingService;

	private final HttpSession httpSession;

	@Transactional(rollbackFor = Exception.class)
	public ResultResponseDto start() {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

		User user = userService.findById(sessionUser.getId());

		user.lose();
		user.addScore(BattleResult.LOSE.getScore());

		rankingService.setScore(user.getId(), user.getScore());

		return new ResultResponseDto(true);
	}

	@Transactional(rollbackFor = Exception.class)
	public UserResponseDto result(BattleResultDto requestDto) {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

		User user = userService.findById(sessionUser.getId());

		user.cancelLose();
		user.addScore(BattleResult.LOSE.getScore() * -1);

		switch (requestDto.getResult()) {
			case WIN:
				user.win();
				user.addScore(BattleResult.WIN.getScore());
				break;

			case LOSE:
				user.lose();
				if (user.getScore() >= 50)
					user.addScore(BattleResult.LOSE.getScore());
				break;

			case DRAW:
				user.draw();
				if (user.getScore() < 50)
					user.addScore(BattleResult.DRAW.getScore());
				break;
		}

		rankingService.setScore(user.getId(), user.getScore());

		int ranking = rankingService.getRanking(user.getId());

		return new UserResponseDto(userRepository.save(user), ranking);
	}
}
