package com.ajou.mse.magicaduel.server.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ajou.mse.magicaduel.server.controller.dto.UserSignUpDto;
import com.ajou.mse.magicaduel.server.domain.battleinfo.BattleInfo;
import com.ajou.mse.magicaduel.server.domain.battleinfo.BattleInfoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BattleInfoRepository battleInfoRepository;

        @Test
        void 유저_저장() {
                // given
                User user = new UserSignUpDto("email", "password", "nickname").toEntity();

                // when
                User savedUser = userRepository.save(user);

                // then
                assertEquals(user, savedUser);
                assertEquals(user.getEmail(), savedUser.getEmail());
                assertEquals(user.getPassword(), savedUser.getPassword());
                assertEquals(user.getNickname(), savedUser.getNickname());
        }

        @Test
        void 유저_조회() {
                // given
                User user = User.builder()
                                .email("email")
                                .password("password")
                                .nickname("nickname")
                                .build();
                User savedUser = userRepository.save(user);

                // when
                User findUserById = userRepository.findById(savedUser.getId())
                                .orElseThrow(() -> new IllegalArgumentException(savedUser.getId() + ""));
                User findUserByEmail = userRepository.findByEmail(savedUser.getEmail())
                                .orElseThrow(() -> new IllegalArgumentException(savedUser.getEmail()));
                User findUserByNickname = userRepository.findByNickname(savedUser.getNickname())
                                .orElseThrow(() -> new IllegalArgumentException(savedUser.getNickname()));

                // then
                assertEquals(savedUser, findUserById);
                assertEquals(savedUser, findUserByEmail);
                assertEquals(savedUser, findUserByNickname);
        }

        @Test
        void 유저_전투정보_연결() {
                // given
                User user = User.builder()
                                .email("email")
                                .password("password")
                                .nickname("nickname")
                                .build();
                BattleInfo battleInfo = BattleInfo.builder()
                                .score(10)
                                .win(1)
                                .lose(2)
                                .draw(3)
                                .build();

                // when
                BattleInfo savedBattleInfo = battleInfoRepository.save(battleInfo);
                user.updateBattleInfo(savedBattleInfo);
                User savedUser = userRepository.save(user);

                // then
                assertEquals(savedUser.getBattleInfo(), savedBattleInfo);
        }

        @Test
        void 유저_전투정보_조회() {
                // given
                User user = User.builder()
                                .email("email")
                                .password("password")
                                .nickname("nickname")
                                .build();
                BattleInfo battleInfo = BattleInfo.builder()
                                .score(10)
                                .win(1)
                                .lose(2)
                                .draw(3)
                                .build();
                BattleInfo savedBattleInfo = battleInfoRepository.save(battleInfo);
                user.updateBattleInfo(savedBattleInfo);
                userRepository.save(user);

                // when
                User findUser = userRepository.findByEmail(user.getEmail())
                                .orElseThrow(() -> new IllegalArgumentException(user.getEmail()));

                // then
                assertEquals(findUser.getBattleInfo(), savedBattleInfo);
                assertEquals(user.getEmail(), findUser.getEmail());
                assertEquals(user.getPassword(), findUser.getPassword());
                assertEquals(user.getNickname(), findUser.getNickname());
                assertEquals(battleInfo.getScore(), findUser.getBattleInfo().getScore());
                assertEquals(battleInfo.getWin(), findUser.getBattleInfo().getWin());
                assertEquals(battleInfo.getLose(), findUser.getBattleInfo().getLose());
                assertEquals(battleInfo.getDraw(), findUser.getBattleInfo().getDraw());
        }
}
