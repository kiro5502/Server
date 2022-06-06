package com.ajou.mse.magicaduel.server.domain.battleinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ajou.mse.magicaduel.server.domain.user.User;
import com.ajou.mse.magicaduel.server.domain.user.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BattleInfoTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BattleInfoRepository battleInfoRepository;

	@Test
	void 전투정보_저장_조회() {
		// given
		int score = 10;
		int win = 1;
		int lose = 2;
		int draw = 3;

		battleInfoRepository.save(BattleInfo.builder()
				.score(score)
				.win(win)
				.lose(lose)
				.draw(draw)
				.build());

		// when
		List<BattleInfo> battleInfos = battleInfoRepository.findAll();

		// then
		BattleInfo battleInfo = battleInfos.get(0);
		assertEquals(battleInfo.getScore(), score);
	}

	@Test
	void 전투정보_수정() {
		// given
		int prevScore = 10;
		int prevWin = 1;
		int prevLose = 2;
		int prevDraw = 3;
		int addScore = 5;

		BattleInfo savedBattleInfo = battleInfoRepository.save(BattleInfo.builder()
				.score(prevScore)
				.win(prevWin)
				.lose(prevLose)
				.draw(prevDraw)
				.build());
		User user = User.builder()
				.email("email")
				.password("password")
				.nickname("nickname")
				.build();
		user.updateBattleInfo(savedBattleInfo);
		userRepository.save(user);

		// when
		BattleInfo battleInfo = userRepository.findAll().get(0).getBattleInfo();

		battleInfo.addScore(addScore);
		battleInfo.win();
		battleInfo.lose();
		battleInfo.draw();

		// then
		BattleInfo findBattleInfo = userRepository.findAll().get(0).getBattleInfo();
		assertEquals(findBattleInfo.getScore(), prevScore + addScore);
		assertEquals(findBattleInfo.getWin(), prevWin + 1);
		assertEquals(findBattleInfo.getLose(), prevLose + 1);
		assertEquals(findBattleInfo.getDraw(), prevDraw + 1);
	}
}
