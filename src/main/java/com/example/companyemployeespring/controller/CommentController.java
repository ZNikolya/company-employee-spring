package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Comment;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment, @AuthenticationPrincipal CurrentUser currentUser) throws ParseException {
        comment.setEmployee(currentUser.getEmployee());
        comment.setCreatedDate(new Date());
        commentService.saveComment(comment);
        return "redirect:/topics/" + comment.getTopic().getId();
    }
}
