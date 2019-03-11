package com.doubles.bootweb;

import com.doubles.bootweb.domain.Posts.Posts;
import com.doubles.bootweb.domain.Posts.PostsRepository;
import com.doubles.bootweb.dto.PostsSaveRequestDto;
import com.doubles.bootweb.service.PostsService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveDtoDataToPostsTable() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("doubles")
                .content("테스트 본문")
                .title("테스트 제목")
                .build();
        // when
        postsService.save(dto);
        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}
