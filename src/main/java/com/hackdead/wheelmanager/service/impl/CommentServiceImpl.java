package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.Comment;
import com.hackdead.wheelmanager.repository.ICommentRepository;
import com.hackdead.wheelmanager.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) throws Exception {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAll() throws Exception {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) throws Exception {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findCommentByPublicationDate(Date publicationDate) throws Exception {
        return commentRepository.findAllWithPublicationDate(publicationDate);
    }
}
