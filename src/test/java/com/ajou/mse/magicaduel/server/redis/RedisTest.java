package com.ajou.mse.magicaduel.server.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ajou.mse.magicaduel.server.util.Consts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisTest {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	void 레디스_랭킹_테스트() {
		// given
		String key = Consts.RANKING_KEY;
		Long id = (long) 1;

		// when
		redisTemplate.opsForZSet().add(key, "1", 30);
		redisTemplate.opsForZSet().add(key, "2", 20);
		redisTemplate.opsForZSet().add(key, "3", 10);

		Long ranking = redisTemplate.opsForZSet().reverseRank(key, id + "");

		// then
		assertEquals(ranking + 1, id);
	}
}
