package com.ajou.mse.magicaduel.server.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;

public class Ranking {

	@Autowired
	private RedisTemplate<String, Long> redisTemplate;

	@Autowired
	UserRepository userRepository;

	String key = "TEST_RANKING";

	public List<User> ReaderBoardRanking(int start, int end) {

		List<User> users = new ArrayList<>();

		Set<Long> ranking = redisTemplate.opsForZSet().reverseRange(key, start, end);

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

	public User PlayerRanking(long id) {
		Optional<User> user = userRepository.findById(id);
		User player = user.get();

		return player;

	}

}
