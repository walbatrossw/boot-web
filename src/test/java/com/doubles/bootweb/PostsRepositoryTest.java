package com.doubles.bootweb;

import com.doubles.bootweb.domain.Posts.Posts;
import com.doubles.bootweb.domain.Posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

//    @After
//    public void cleanup() {
//        postsRepository.deleteAll();
//    }

    @Test
    public void getPosts() {
        // given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글 제목")
                .content("테스트 게시글 본문")
                .author("doubles")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글 제목"));
        assertThat(posts.getContent(), is("테스트 게시글 본문"));
        assertThat(posts.getAuthor(), is("doubles"));
    }

    @Test
    public void insertPostsBaseTimeEntity() {
        // given
        LocalDateTime  now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글 제목")
                .content("테스트 게시글 제목")
                .author("doubles")
                .build());
        // when
        List<Posts> postsList = postsRepository.findAll();
        // then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreateDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
