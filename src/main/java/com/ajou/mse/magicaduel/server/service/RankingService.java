package com.ajou.mse.magicaduel.server.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RankingService {

  private final StringRedisTemplate redisTemplate;

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
}
