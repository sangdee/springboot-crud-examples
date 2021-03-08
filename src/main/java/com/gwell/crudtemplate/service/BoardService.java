package com.gwell.crudtemplate.service;

import com.gwell.crudtemplate.domain.Board;
import com.gwell.crudtemplate.filter.BoardFilter;
import com.gwell.crudtemplate.repository.BoardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-07
 */

/**
 * Board Service Inject BoardRepository
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    @NonNull
    private final BoardRepository boardRepository;

    public Page<Board> findAllByFilter(Pageable pageable, BoardFilter filter) {
        return boardRepository.findAllByFilter(pageable, filter);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void updateRemovedById(Long id) {
        boardRepository.updateRemovedById(id);
    }
}
