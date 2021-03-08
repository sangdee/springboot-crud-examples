package com.gwell.crudtemplate.repository.support;

import com.gwell.crudtemplate.domain.Board;
import com.gwell.crudtemplate.domain.QBoard;
import com.gwell.crudtemplate.filter.BoardFilter;
import com.gwell.crudtemplate.repository.custom.CustomBoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */

/**
 * Create dynamic query of custom repositories
 */
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements
        CustomBoardRepository {

    /**
     * querydsl object
     */
    private QBoard board = QBoard.board;

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    /**
     * dynamic query of findAllByFilter
     * Paging value inspected filter
     *
     * @param pageable
     * @param filter
     * @return
     */
    @Override
    public Page<Board> findAllByFilter(Pageable pageable, BoardFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(board.removed.isFalse());
        if (!StringUtils.isBlank(filter.getFTitle())) {
            builder.and(board.title.containsIgnoreCase(filter.getFTitle()));
        }
        final JPQLQuery<Board> query = from(board).where(builder).orderBy(board.createDate.desc());
        final List<Board> result = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(result, pageable, query.fetchCount());
    }
}
