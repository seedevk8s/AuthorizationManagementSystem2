package able.cmm.mybatis.web;

import java.util.List;

import javax.annotation.Resource;

import able.cmm.mybatis.service.MyBatisSampleService;
import able.com.vo.HMap;
import able.com.web.HController;
import able.com.web.view.PagingInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : MyBatisSampleController.java
 * @Description : 마이바티스 Sample 컨트롤러 Class
 * @author hojin
 * @since 2019. 8. 16.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2019. 8. 16.     hojin     	최초 생성
 * </pre>
 */

@Controller
public class MyBatisSampleController extends HController{

    @Resource(name = "myBatisSampleService")
    private MyBatisSampleService myBatisSampleService;
    
    @RequestMapping(value ="/cmm/mybatis/selectMyBatisList.do")
    public String selectMyBatisList(HMap hmap, ModelMap model) throws Exception {
        
        if (null == hmap.get("currPage") || hmap.get("currPage").equals("")) {
            hmap.put("currPage", "1");
        }
        
        int currPage = Integer.parseInt(hmap.get("currPage") + "");
        
        hmap.put("limit", 15 * currPage);
        hmap.put("offset", 15 * (currPage - 1));       
        
        List<?> resultList =  myBatisSampleService.selectMyBatisList(hmap);
        
        //페이징 정보
        PagingInfo bbsMasterPage = new PagingInfo();
        
        // 총 게시물 건수는 별도로 조회해야 한다.
        bbsMasterPage.setTotalRecordCount(myBatisSampleService.selectMyBatisListTotCnt(hmap));
        
        
        
        
        return "";
    }
}

















































