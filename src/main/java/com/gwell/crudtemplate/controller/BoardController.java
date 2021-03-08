package com.gwell.crudtemplate.controller;

import com.gwell.crudtemplate.domain.Board;
import com.gwell.crudtemplate.exception.CustomNotFoundException;
import com.gwell.crudtemplate.filter.BoardFilter;
import com.gwell.crudtemplate.service.BoardService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-05
 */

/**
 *  Board Controller
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/manage/board")
public class BoardController {

    /**
     * DI
     */
    @NonNull
    private final BoardService boardService;
    @NonNull
    private final SmartValidator validator;

    /**
     * Display paged posts in a list, Search for posts using filters
     *
     * @param pageable
     * @param model
     * @param filter
     * @return
     */
    @GetMapping("/list")
    public String list(Pageable pageable, Model model, BoardFilter filter) {
        Page<Board> boardPage = boardService.findAllByFilter(pageable, filter);
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("filter", filter);

        return "manage/board/list";
    }

    /**
     * View a specific post by id
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public String read(Model model, @PathVariable(name = "id") Long id) {
        Board board = boardService.findById(id).orElseThrow(CustomNotFoundException::new);
        model.addAttribute("board", board);
        return "manage/board/detail";
    }

    /**
     * Register your post
     * When you receive the id value, you can edit the posting about the id value
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping({"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
        Board board = (id != null) ?
                boardService.findById(id).orElseThrow(CustomNotFoundException::new) : new Board();
        model.addAttribute("board", board);

        return "manage/board/edit";
    }

    /**
     * Save post
     * Value can be verified through a validator
     *
     * @param board
     * @param result
     * @return
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Board board, BindingResult result) {

        validator.validate(board, result);

        if (!result.hasErrors()) {
            boardService.save(board);
            return "redirect:/manage/board/list/";
        }
        return "manage/board/edit";
    }

    /**
     * Delete post
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        try {
            boardService.updateRemovedById(id);
        } catch (DataIntegrityViolationException e) {
            return "redirect:/manage/board/list";
        }
        return "redirect:/manage/board/list";
    }
}
