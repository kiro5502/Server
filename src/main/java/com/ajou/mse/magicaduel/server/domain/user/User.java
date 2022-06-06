package com.ajou.mse.magicaduel.server.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.ajou.mse.magicaduel.server.domain.BaseTimeEntity;
import com.ajou.mse.magicaduel.server.domain.battleinfo.BattleInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
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

	@OneToOne
	@JoinColumn(name = "battle_info_id", referencedColumnName = "id")
	private BattleInfo battleInfo;

	@Builder
	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public void updateBattleInfo(BattleInfo battleInfo) {
		this.battleInfo = battleInfo;
	}
}
