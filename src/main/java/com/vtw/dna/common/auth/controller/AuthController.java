package com.vtw.dna.common.auth.controller;

import com.vtw.dna.common.jwt.EgovJwtTokenUtil;
import com.vtw.dna.common.rest.EmptySuccessResponse;
import com.vtw.dna.common.auth.dto.SignInResponse;
import com.vtw.dna.common.auth.dto.AuthUser;
import com.vtw.dna.common.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService loginService;
    private final EgovJwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/signIn")
    public SignInResponse login(@RequestBody AuthUser loginVO, HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        AuthUser loginResultVO = loginService.findUser(loginVO);

        if (loginResultVO != null && loginResultVO.getId() != null && !loginResultVO.getId().equals("")) {

            log.debug("===>>> loginVO.getId() = " + loginVO.getId());
            log.debug("===>>> loginVO.getPassword() = " + loginVO.getPassword());

            String jwtToken = jwtTokenUtil.generateToken(loginResultVO);

            SignInResponse response = new SignInResponse(jwtToken, loginResultVO.getId(), loginResultVO.getName());
            request.getSession().setAttribute("LoginVO", loginResultVO);

            return response;
        } else {
            throw new RuntimeException("로그인 실패");
        }
    }

    @GetMapping(value = "/signOut")
    public EmptySuccessResponse logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new SecurityContextLogoutHandler().logout(request, response, null);
        return new EmptySuccessResponse();
    }

    @GetMapping(value = "/refreshToken")
    public SignInResponse refreshToken(HttpServletRequest request) throws Exception {
        String HEADER_STRING = "Authorization";
        String token = request.getHeader(HEADER_STRING);
        AuthUser loginUser = new AuthUser();
        loginUser.setId(jwtTokenUtil.getInfoFromToken("id", token));
        loginUser.setName(jwtTokenUtil.getInfoFromToken("name", token));

        String newToken = jwtTokenUtil.generateToken(loginUser);
        SignInResponse response = new SignInResponse(newToken, loginUser.getId(), loginUser.getName());
        return response;
    }

    @PostMapping(value = "/createAccount")
    public EmptySuccessResponse createAccount(@RequestBody AuthUser user) throws Exception {
        loginService.createAccount(user);
        return new EmptySuccessResponse();
    }
}
