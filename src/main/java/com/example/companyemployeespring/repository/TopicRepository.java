package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findAllByCompany_id(int id);
}
