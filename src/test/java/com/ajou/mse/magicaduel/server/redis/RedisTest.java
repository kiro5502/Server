package com.ajou.mse.magicaduel.server.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisTest {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	void 레디스_테스트() {
		// given
		String key = "TEST_RANKING";
		Long id = (long) 1;

		// when
		redisTemplate.opsForZSet().add(key, "1", 30);
		redisTemplate.opsForZSet().add(key, "2", 20);
		redisTemplate.opsForZSet().add(key, "3", 10);

		Long ranking = redisTemplate.opsForZSet().reverseRank(key, id + "");

		// then
		assertEquals(ranking + 1, id);

		redisTemplate.delete(key);
	}

	@Test
	void 레디스_랭킹_테스트() {
		// given
		String key = "TEST_RANKING";
		int page = 1;
		int limit = 10;
		int pageStartRanking = (page - 1) * limit;
		int pageEndRanking = page * limit - 1;

		// when
		for (int i = 0; i < 20; i++) {
			redisTemplate.opsForZSet().add(key, i + "", 20 - i);
		}

		Set<String> list = redisTemplate.opsForZSet().reverseRange(key, pageStartRanking, pageEndRanking);

		int idx = 0;
		for (String value : list) {
			assertEquals(value, idx++ + "");
		}

		// then
		assertEquals(list.size(), limit);

		redisTemplate.delete(key);
	}
}
