package able.cmm.mybatis.service;

import java.util.List;

import able.com.vo.HMap;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : MyBatisSampleService.java
 * @Description : 마이바티스 Sample 서비스 인터페이스 정의
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

public interface MyBatisSampleService {

    /**
     * 글 목록을 조회한다.
     *
     * @param hmap
     * @return
     */
    List<?> selectMyBatisList(HMap hmap)throws Exception;

    /**
     * 총 게시물 건수
     *
     * @param hmap
     * @return
     */
    int selectMyBatisListTotCnt(HMap hmap)throws Exception;
    
}

























































