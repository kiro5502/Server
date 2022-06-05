package com.ajou.mse.magicaduel.server.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajou.mse.magicaduel.server.controller.dto.BattleResultDto;
import com.ajou.mse.magicaduel.server.controller.dto.ResultResponseDto;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.controller.dto.UserResponseDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.util.BattleResult;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BattleService {

	private final UserService userService;
	private final RankingService rankingService;

	private final HttpSession httpSession;

	private final int stdScore = 50;

	@Transactional(rollbackFor = Exception.class)
	public ResultResponseDto start(SessionUser sessionUser) {
		User user = userService.findById(sessionUser.getId());

		httpSession.setAttribute(Consts.SESSION_SCORE, user.getScore());

		user.lose();

		if (user.getScore() >= stdScore)
			user.addScore(BattleResult.LOSE.getScore());

		rankingService.setScore(user.getId(), user.getScore());

		return new ResultResponseDto(true);
	}

	@Transactional(rollbackFor = Exception.class)
	public UserResponseDto result(BattleResultDto requestDto, SessionUser sessionUser) {
		User user = userService.findById(sessionUser.getId());

		int prevScore = (int) httpSession.getAttribute(Consts.SESSION_SCORE);
		httpSession.removeAttribute(Consts.SESSION_SCORE);

		user.cancelLose();
		user.addScore(prevScore - user.getScore());

		switch (requestDto.getResult()) {
			case WIN:
				user.win();
				user.addScore(BattleResult.WIN.getScore());
				break;

			case LOSE:
				user.lose();
				if (user.getScore() >= stdScore)
					user.addScore(BattleResult.LOSE.getScore());
				break;

			case DRAW:
				user.draw();
				if (user.getScore() < stdScore)
					user.addScore(BattleResult.DRAW.getScore());
				break;
		}

		rankingService.setScore(user.getId(), user.getScore());

		int ranking = rankingService.getRanking(user.getId());

		return new UserResponseDto(user, ranking);
	}
}
