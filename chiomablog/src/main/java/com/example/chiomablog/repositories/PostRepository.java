package com.example.chiomablog.repositories;

import com.example.chiomablog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where upper(p.postTitle) like upper(concat('%', ?1, '%'))")
    Optional<Post> findContainingPostTitle(@NonNull String postTitle);
    @Query("select p from Post p where p.postTitle = ?1")
    Optional<Post>  findPostTitle(@NonNull String postTitle);

    @Query("select p from Post p where p.postTitle = ?1")
    Optional<Post> findByPostTitle(@NonNull String postTitle);


//    @Query("select p from Post p where p.postTitle = ?1")
//    Optional<Post> FindByPostTitle(String postTitle);

    @Override
    Optional<Post> findById(Long id);

}