package starter.practice.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import starter.practice.domain.posts.Posts;
import starter.practice.domain.posts.PostsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@MockBean(JpaMetamodelMappingContext.class)
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void posts_save() {

        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("저자")
                        .build());
        // when
        List<Posts> all = postsRepository.findAll();

        Posts posts = all.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}