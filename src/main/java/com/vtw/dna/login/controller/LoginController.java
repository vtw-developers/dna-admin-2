package com.vtw.dna.login.controller;

import com.vtw.dna.common.jwt.EgovJwtTokenUtil;
import com.vtw.dna.common.jwt.JwtAuthenticationFilter;
import com.vtw.dna.common.rest.EmptySuccessResponse;
import com.vtw.dna.common.util.EgovStringUtil;
import com.vtw.dna.login.dto.LoginResponse;
import com.vtw.dna.login.dto.LoginUser;
import com.vtw.dna.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@RestController
public class LoginController {

    private final LoginService loginService;
    private final EgovJwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/auth/login-jwt")
    public LoginResponse login(@RequestBody LoginUser loginVO, HttpServletRequest request, ModelMap model) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        LoginUser loginResultVO = loginService.actionLogin(loginVO);

        if (loginResultVO != null && loginResultVO.getId() != null && !loginResultVO.getId().equals("")) {

            log.debug("===>>> loginVO.getId() = " + loginVO.getId());
            log.debug("===>>> loginVO.getPassword() = " + loginVO.getPassword());

            String jwtToken = jwtTokenUtil.generateToken(loginResultVO);

            LoginResponse response = new LoginResponse(jwtToken, loginResultVO.getId(), loginResultVO.getName());
            request.getSession().setAttribute("LoginVO", loginResultVO);

            return response;
        } else {
            throw new RuntimeException("로그인 실패");
        }
    }

    @GetMapping(value = "/auth/logout")
    public EmptySuccessResponse logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new SecurityContextLogoutHandler().logout(request, response, null);
        return new EmptySuccessResponse();
    }

    @GetMapping(value = "/auth/refreshToken")
    public LoginResponse refreshToken(HttpServletRequest request) throws Exception {
        String HEADER_STRING = "Authorization";
        String token = request.getHeader(HEADER_STRING);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(jwtTokenUtil.getInfoFromToken("id", token));
        loginUser.setName(jwtTokenUtil.getInfoFromToken("name", token));

        String newToken = jwtTokenUtil.generateToken(loginUser);
        LoginResponse response = new LoginResponse(newToken, loginUser.getId(), loginUser.getName());
        return response;
    }

    @PostMapping(value = "/auth/createAccount")
    public EmptySuccessResponse createAccount(@RequestBody LoginUser user) throws Exception {
        loginService.createAccount(user);
        return new EmptySuccessResponse();
    }
}
