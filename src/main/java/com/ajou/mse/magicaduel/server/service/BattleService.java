package com.ajou.mse.magicaduel.server.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajou.mse.magicaduel.server.controller.dto.BattleInfoDto;
import com.ajou.mse.magicaduel.server.domain.battleInfo.BattleInfo;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.error.exception.NotFoundException;
import com.ajou.mse.magicaduel.server.util.BattleResult;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BattleService {

	private final UserRepository userRepository;

	private final HttpSession httpSession;

	private final int stdScore = 50;

	public BattleInfoDto getBattleInfo(long userId) {
		BattleInfo battleInfo = findByUserId(userId);
		return new BattleInfoDto(battleInfo);
	}

	@Transactional(rollbackFor = Exception.class)
	public BattleInfoDto result(BattleInfo battleInfo, BattleResult result) {
		int prevScore = getAndRemovePrevScore();
		cancelLose(battleInfo, prevScore);

		BattleInfoDto battleInfoDto = null;

		switch (result) {
			case WIN:
				battleInfoDto = win(battleInfo);
				break;

			case LOSE:
				battleInfoDto = lose(battleInfo);
				break;

			case DRAW:
				battleInfoDto = draw(battleInfo);
				break;
		}

		return battleInfoDto;
	}

	@Transactional(rollbackFor = Exception.class)
	public BattleInfoDto win(BattleInfo battleInfo) {
		battleInfo.win();
		battleInfo.addScore(BattleResult.WIN.getScore());

		return new BattleInfoDto(battleInfo);
	}

	@Transactional(rollbackFor = Exception.class)
	public BattleInfoDto lose(BattleInfo battleInfo) {
		battleInfo.lose();
		if (battleInfo.getScore() >= stdScore)
			battleInfo.addScore(BattleResult.LOSE.getScore());

		return new BattleInfoDto(battleInfo);
	}

	@Transactional(rollbackFor = Exception.class)
	public BattleInfoDto draw(BattleInfo battleInfo) {
		battleInfo.draw();
		if (battleInfo.getScore() < stdScore)
			battleInfo.addScore(BattleResult.DRAW.getScore());

		return new BattleInfoDto(battleInfo);
	}

	@Transactional(rollbackFor = Exception.class)
	public void cancelLose(BattleInfo battleInfo, int prevScore) {
		battleInfo.cancelLose();
		battleInfo.addScore(prevScore - battleInfo.getScore());
	}

	public BattleInfo findByUser(User user) {
		BattleInfo battleInfo = user.getBattleInfo();
		return battleInfo;
	}

	public BattleInfo findByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Not found user id = " + userId));

		BattleInfo battleInfo = user.getBattleInfo();

		return battleInfo;
	}

	public void setPrevScore(int score) {
		httpSession.setAttribute(Consts.SESSION_SCORE, score);
	}

	public int getAndRemovePrevScore() {
		if (httpSession.getAttribute(Consts.SESSION_SCORE) == null) {
			throw new NotFoundException("Not Found Session");
		}

		int prevScore = (int) httpSession.getAttribute(Consts.SESSION_SCORE);

		httpSession.removeAttribute(Consts.SESSION_SCORE);

		return prevScore;
	}
}
