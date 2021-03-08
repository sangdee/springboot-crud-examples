package com.gwell.crudtemplate.repository.custom;

import com.gwell.crudtemplate.domain.Board;
import com.gwell.crudtemplate.filter.BoardFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */


/**
 * Custom Repository
 */
@NoRepositoryBean
public interface CustomBoardRepository {

    /**
     * Paging with pageable and filter
     *
     * @param pageable
     * @param filter
     * @return
     */
    Page<Board> findAllByFilter(Pageable pageable, BoardFilter filter);
}
