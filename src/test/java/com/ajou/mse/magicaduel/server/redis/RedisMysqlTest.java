package com.ajou.mse.magicaduel.server.redis;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;
import com.ajou.mse.magicaduel.server.service.RankingService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RedisMysqlTest {

	@Autowired
	private RedisTemplate<String, Long> redisTemplate;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RankingService rankingService;

	String key = "TEST_RANKING";
	Long id = (long) 1;

	@Test
	void saveTest() {
		// userRepository.deleteAll();

		// userRepository.save(user1);
		// userRepository.save(user2);

		redisTemplate.opsForZSet().add(key, (long) 1, 100);
		redisTemplate.opsForZSet().add(key, (long) 2, 90);
		redisTemplate.opsForZSet().add(key, (long) 3, 80);
		redisTemplate.opsForZSet().add(key, (long) 4, 70);
		redisTemplate.opsForZSet().add(key, (long) 5, 60);
		redisTemplate.opsForZSet().add(key, (long) 6, 50);
		redisTemplate.opsForZSet().add(key, (long) 7, 40);
		redisTemplate.opsForZSet().add(key, (long) 8, 30);
		redisTemplate.opsForZSet().add(key, (long) 9, 20);
		redisTemplate.opsForZSet().add(key, (long) 10, 10);

		User user1 = new User("aa@aa.com", "aa", "a", 100, 1, 1, 1);

		userRepository.deleteAllInBatch();

		userRepository.save(user1);

		Set<Long> ranking = redisTemplate.opsForZSet().reverseRange(key, 0, 0);

		for (Long value : ranking) {

			System.out.println(value);
			Optional<User> finduser = userRepository.findById(value);
			User user = finduser.get();
			System.out.println(user.getNickname());
			System.out.println(user.getScore());
			System.out.println(user.getWin());
			System.out.println(user.getLose());
			System.out.println(user.getDraw());

		}

	}

}
