package com.ajou.mse.magicaduel.server.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ajou.mse.magicaduel.server.error.exception.NotFoundException;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BattleService {

	private final HttpSession httpSession;

	public void setPrevScore(int score) {
		httpSession.setAttribute(Consts.SESSION_SCORE, score);
	}

	public int getAndRemovePrevScore() {
		String prevScoreStr = (String) httpSession.getAttribute(Consts.SESSION_SCORE);
		if (prevScoreStr == null) {
			throw new NotFoundException("Not Found Session");
		}

		httpSession.removeAttribute(Consts.SESSION_SCORE);

		return Integer.parseInt(prevScoreStr);
	}
}
