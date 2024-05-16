package com.vtw.dna.login.service.impl;

import com.vtw.dna.common.user.LoginVO;
import com.vtw.dna.common.util.EgovFileScrty;
import com.vtw.dna.common.util.EgovNumberUtil;
import com.vtw.dna.common.util.EgovStringUtil;
import com.vtw.dna.login.service.EgovLoginService;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 일반 로그인을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Service("loginService")
public class EgovLoginServiceImpl implements EgovLoginService {
	private static final Logger log = LoggerFactory.getLogger(EgovLoginServiceImpl.class);

//	@Resource(name = "loginDAO")
//	private LoginDAO loginDAO;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	@Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {
		LoginVO loginVO = new LoginVO();
		loginVO.setId("test");
		loginVO.setPassword("test");
		loginVO.setUserSe("USR");
		loginVO.setName("우태진");
		return loginVO;
//		// 1. 입력한 비밀번호를 암호화한다.
//		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
//		vo.setPassword(enpassword);
//
//		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
//		LoginVO loginVO = loginDAO.actionLogin(vo);
//
//		// 3. 결과를 리턴한다.
//		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
//			return loginVO;
//		} else {
//			loginVO = new LoginVO();
//		}
//
//		return loginVO;
	}

	/**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	@Override
	public LoginVO searchId(LoginVO vo) throws Exception {
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
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
	@Override
	public boolean searchPassword(LoginVO vo) throws Exception {
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
