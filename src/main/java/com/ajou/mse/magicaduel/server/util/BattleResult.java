package com.ajou.mse.magicaduel.server.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BattleResult {

    WIN("WIN", 10),
    LOSE("LOSE", -8);

    private final String type;
    private final int score;
}
