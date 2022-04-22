package com.ajou.mse.magicaduel.server.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ajou.mse.magicaduel.server.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String nickname;

  private Integer score;

  private Integer win;

  private Integer lose;

  @Builder
  public User(String email, String password, String nickname, Integer score, Integer win,
      Integer lose) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.score = score;
    this.win = win;
    this.lose = lose;
  }

  public void update(String nickname) {
    this.nickname = nickname;
  }

}
