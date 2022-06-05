package com.ajou.mse.magicaduel.server.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ajou.mse.magicaduel.server.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DynamicInsert // 변경 감지된 필드만 Insert 쿼리에 반영. @ColumnDefault와 같이 사용
@DynamicUpdate // 변경 감지된 필드만 Update 쿼리에 반영
@NoArgsConstructor
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nickname;

	@ColumnDefault("0")
	private Integer score;

	@ColumnDefault("0")
	private Integer win;

	@ColumnDefault("0")
	private Integer lose;

	@ColumnDefault("0")
	private Integer draw;

	@Builder
	public User(String email, String password, String nickname, Integer score, Integer win, Integer lose,
			Integer draw) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.score = score;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
	}

	public void addScore(int score) {
		this.score += score;
		if (this.score < 0)
			this.score = 0;
	}

	public void win() {
		this.win++;
	}

	public void lose() {
		this.lose++;
	}

	public void cancelLose() {
		this.lose--;
	}

	public void draw() {
		this.draw++;
	}
}
