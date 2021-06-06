package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.Comment;

import java.util.Date;
import java.util.List;

public interface ICommentService extends CrudService<Comment> {
    List<Comment> findCommentByPublicationDate(Date publicationDate) throws Exception;
}
