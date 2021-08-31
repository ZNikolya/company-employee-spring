package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Comment;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.CommentService;
import com.example.companyemployeespring.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final TopicService topicService;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment, @AuthenticationPrincipal CurrentUser currentUser) throws ParseException {
        comment.setTopic(topicService.findTopicById(comment.getTopicId()).get());
        comment.setEmployee(currentUser.getEmployee());
        comment.setCreatedDate(new Date());
        commentService.saveComment(comment);
        return "redirect:/more/" + comment.getTopic().getId();
    }
}
