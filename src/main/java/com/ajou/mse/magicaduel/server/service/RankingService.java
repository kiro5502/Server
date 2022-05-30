package com.ajou.mse.magicaduel.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ajou.mse.magicaduel.server.controller.dto.RankingUser;
import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingService {

	private final StringRedisTemplate redisTemplate;
	private final HttpSession httpSession;

	private final UserRepository userRepository;

	private int rankingPerPage = 10;

	public int getRanking(Long userId) {
		// Ranking starts with 0
		Long ranking = redisTemplate.opsForZSet().reverseRank(Consts.RANKING_KEY, String.valueOf(userId));
		if (ranking == null) {
			return 0;
		}
		return ranking.intValue() + 1;
	}

	public void setScore(Long userId, int score) {
		redisTemplate.opsForZSet().add(Consts.RANKING_KEY, String.valueOf(userId), score);
	}

	public List<RankingUser> getLeaderBoard(int page) {
		int start = (page - 1) * rankingPerPage;
		int end = page * rankingPerPage - 1;

		List<RankingUser> users = new ArrayList<>();
		Set<String> ranking = redisTemplate.opsForZSet().reverseRange(Consts.RANKING_KEY, start, end);

		for (String id : ranking) {
			User user = userRepository.findById(Long.parseLong(id))
					.orElseThrow(() -> new IllegalArgumentException("Not found user id = " + id));

			RankingUser rankingUser = RankingUser.builder()
					.nickname(user.getNickname())
					.score(user.getScore())
					.win(user.getWin())
					.lose(user.getLose())
					.draw(user.getDraw())
					.build();

			users.add(rankingUser);
		}

		return users;
	}

	public int getPlayerRanking() {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		return getRanking(sessionUser.getId());
	}
}
