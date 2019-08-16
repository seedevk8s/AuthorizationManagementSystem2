package able.cmm.mybatis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import able.cmm.mybatis.service.MyBatisSampleService;
import able.cmm.mybatis.service.dao.MyBatisSampleMDAO;
import able.com.service.HService;
import able.com.vo.HMap;

import org.springframework.stereotype.Service;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : MyBatisSampleServiceImpl.java
 * @Description : MyBatisSampleService 구현 Class
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
@Service("myBatisSampleService")
public class MyBatisSampleServiceImpl extends HService implements MyBatisSampleService{
    
    
    /**
     * MyBatis 샘플을 위한 db 연동 처리
     */
    @Resource(name = "myBatisSampleMDAO")
    private MyBatisSampleMDAO myBatisSampleMDAO;

    /*
     * mybatis 데이터 목록을 조회한다.
     */
    @Override
    public List<?> selectMyBatisList(HMap hmap) throws Exception {
        
        return myBatisSampleMDAO.selectMyBatisList(hmap);
    }

    /*
     * 총 게시물 건수
     */
    @Override
    public int selectMyBatisListTotCnt(HMap hmap) throws Exception {
        
        return myBatisSampleMDAO.selectMyBatisListToCnt(hmap);
    }

}





























