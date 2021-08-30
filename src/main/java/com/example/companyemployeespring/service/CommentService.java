package com.example.companyemployeespring.service;

import com.example.companyemployeespring.model.Comment;
import com.example.companyemployeespring.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllCommentsByTopicId(int id){
        return commentRepository.findAllByTopic_id(id);
    }
}
