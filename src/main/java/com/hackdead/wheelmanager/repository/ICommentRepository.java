package com.hackdead.wheelmanager.repository;

import com.hackdead.wheelmanager.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.publicationDate=:publicationDate")
    List<Comment> findAllWithPublicationDate(@Param("publicationDate") Date publicationDate);
}
