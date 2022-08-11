package org.example.controller;

import org.example.command.CreateBoardCommand;
import org.example.command.validator.CreateBoardValidator;
import org.example.exception.DuplicateBoardTitleException;
import org.example.model.Board;
import org.example.model.Post;
import org.example.model.User;
import org.example.service.BoardService;
import org.example.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    public BoardController(BoardService boardService, PostService postService) {
        this.boardService = boardService;
        this.postService = postService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new CreateBoardValidator());
    }

    @GetMapping("/board")
    public String boardList(Model model) {
        List<Board> boardList = boardService.getBoards();
        model.addAttribute("boards", boardList);
        return "board/list";
    }

    @GetMapping("/board/create")
    public String create(@ModelAttribute("createBoard") CreateBoardCommand command) {
        return "board/create";
    }

    @PostMapping("/board/create")
    public String createPost(Model model, @ModelAttribute("createBoard") @Valid CreateBoardCommand command,
                             Errors errors) {
        // TODO: Allow this page only for users logged in.

        if (errors.hasErrors()) {
            return "board/create";
        }
        try {
            boardService.createBoard(command.getTitle());
            model.addAttribute("title", command.getTitle());
            return "redirect:/board/{title}";
        } catch (DuplicateBoardTitleException e) {
            errors.rejectValue("title", "duplicate");
            return "board/create";
        }
    }

    @GetMapping("/board/{title}")
    public String postList(@PathVariable("title") String title, Model model) {
        List<Post> postList = boardService.getPostsByBoardTitle(title);
        List<User> authorList = postList.stream().map(postService::getAuthor).toList();
        model.addAttribute("posts", postList);
        model.addAttribute("authors", authorList);
        return "board/postList";
    }
}
