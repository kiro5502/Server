package com.ajou.mse.magicaduel.server.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.service.RankingService;

@SpringBootTest
public class RankingServiceTest {

	@Autowired
	private RedisTemplate<String, Long> redisTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RankingService rankingService;

	String key = "TEST_RANKING";

	public List<User> ReaderBoardRanking(int start, int end) {

		List<User> users = new ArrayList<>();

		Set<Long> ranking = redisTemplate.opsForZSet().reverseRange(key, start, end);

		for (Long value : ranking) {
			int i = 1;

			System.out.println(value);
			Optional<User> finduser = userRepository.findById(value);
			User user = finduser.get();

			users.add(user);
		}
		return users;
	}

}
