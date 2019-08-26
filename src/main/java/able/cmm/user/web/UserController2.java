package able.cmm.user.web;

import able.com.vo.HMap;
import able.com.web.HController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import able.cmm.user.vo.UserVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserController.java
 * @Description : 클래스 설명을 기술합니다.
 * @author hojin
 * @since 2019. 8. 26.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 8. 26.     hojin     	최초 생성
 * </pre>
 */

@Controller
public class UserController2 extends HController{

    @RequestMapping(value = "/cmm/user/showUserForm2.do", method = RequestMethod.GET)
    public String showUserForm(HMap hMap, ModelMap model) throws Exception{
        //빈 객체 설정
        UserVO user = new UserVO();
        model.addAttribute("userVO", user);
        return "cmm/user/userForm";
    }
}
