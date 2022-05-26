package com.ajou.mse.magicaduel.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

	public List<User> ReaderBoardRanking(int start, int end) {

		List<User> users = new ArrayList<>();

		Set<Long> ranking = redisTemplateLong.opsForZSet().reverseRange(Consts.RANKING_KEY, start, end);

		for (Long value : ranking) {

			System.out.println(value);
			Optional<User> finduser = userRepository.findById(value);
			User user = finduser.get();
			System.out.println(user.getNickname());
			System.out.println(user.getScore());
			System.out.println(user.getWin());
			System.out.println(user.getLose());
			System.out.println(user.getDraw());

			users.add(user);
		}
		return users;
	}

	public User PlayerRanking(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		User player = user.get();

		return player;

	}
}
