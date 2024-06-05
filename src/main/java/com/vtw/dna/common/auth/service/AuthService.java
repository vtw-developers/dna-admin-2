package com.vtw.dna.common.auth.service;

import com.vtw.dna.common.auth.dto.AuthUser;
import com.vtw.dna.common.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

//	@Resource(name = "loginDAO")
//	private LoginDAO loginDAO;

    private final AuthRepository loginRepository;

    /**
     * 일반 로그인을 처리한다
     *
     * @param user LoginVO
     * @return LoginVO
     * @throws Exception
     */
    public AuthUser findUser(AuthUser user) throws Exception {
        // 1. 입력한 비밀번호를 암호화한다.
//		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
//		vo.setPassword(enpassword);
        AuthUser loginVO = loginRepository.selectByIdAndPassword(user.getId(), user.getPassword());

        // 3. 결과를 리턴한다.
        if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
            return loginVO;
        } else {
            loginVO = new AuthUser();
        }

        return loginVO;
    }

    public void createAccount(AuthUser user) throws Exception {
        loginRepository.insert(user);
    }

    /**
     * 아이디를 찾는다.
     *
     * @param vo LoginVO
     * @return LoginVO
     * @throws Exception
     */
    public AuthUser searchId(AuthUser vo) throws Exception {
        return null;
/*		// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
		LoginVO loginVO = loginDAO.searchId(vo);

		// 2. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getId().equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;*/
    }

    /**
     * 비밀번호를 찾는다.
     *
     * @param vo LoginVO
     * @return boolean
     * @throws Exception
     */
    public boolean searchPassword(AuthUser vo) throws Exception {
        return false;
/*		boolean result = true;

		// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
		LoginVO loginVO = loginDAO.searchPassword(vo);
		if (loginVO == null || loginVO.getPassword() == null || loginVO.getPassword().equals("")) {
			return false;
		}

		// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫=6자리)
		String newpassword = "";
		for (int i = 1; i <= 6; i++) {
			// 영자
			if (i % 3 != 0) {
				newpassword += EgovStringUtil.getRandomStr('a', 'z');
				// 숫자
			} else {
				newpassword += EgovNumberUtil.getRandomNum(0, 9);
			}
		}

		// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
		LoginVO pwVO = new LoginVO();
		String enpassword = EgovFileScrty.encryptPassword(newpassword, vo.getId());
		pwVO.setId(vo.getId());
		pwVO.setPassword(enpassword);
		pwVO.setUserSe(vo.getUserSe());
		loginDAO.updatePassword(pwVO);

		return result;*/
    }
}