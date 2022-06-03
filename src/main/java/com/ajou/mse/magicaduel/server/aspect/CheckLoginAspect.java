package com.ajou.mse.magicaduel.server.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.ajou.mse.magicaduel.server.controller.dto.SessionUser;
import com.ajou.mse.magicaduel.server.error.exception.UnauthorizedException;
import com.ajou.mse.magicaduel.server.util.Consts;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckLoginAspect {

    private final HttpSession httpSession;

    @Before("@annotation(com.ajou.mse.magicaduel.server.annotation.CheckLogin)")
    public void checkLogin() throws UnauthorizedException {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(Consts.SESSION_USER);

        if (sessionUser == null)
            throw new UnauthorizedException("Unauthorized");
    }
}
