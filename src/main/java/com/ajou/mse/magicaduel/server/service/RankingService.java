package com.ajou.mse.magicaduel.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ajou.mse.magicaduel.server.controller.dto.RankingDataDto;
import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingService {

	private final StringRedisTemplate redisTemplate;

	private final RedisTemplate<String, Long> redisTemplateLong;

	private UserRepository userRepository;

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

	public RankingDataDto leaderBoardRanking(int page) {

		int pageStartRanking = 0 + (page - 1) * 10;
		int pageEndRanking = 9 + (page - 1) * 10;

		List<User> users = new ArrayList<>();

		Set<Long> ranking = redisTemplateLong.opsForZSet().reverseRange(Consts.RANKING_KEY, pageStartRanking,
				pageEndRanking);

		for (Long value : ranking) {

			Optional<User> finduser = userRepository.findById(value);
			User user = finduser.get();

			User adduser = new User(user.getNickname(), user.getScore(), user.getWin(), user.getLose(), user.getDraw());

			users.add(adduser);
		}
		return new RankingDataDto(users);
	}
}
