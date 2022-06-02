package com.ajou.mse.magicaduel.server.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BattleResult {

	WIN("WIN", 5), LOSE("LOSE", -2), DRAW("DRAW", 1);

	private final String type;
	private final int score;
}
