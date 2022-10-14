package starter.practice.domain.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDTO requestDTO) {
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    public PostsResponseDTO findById(Long id) {
        return new PostsResponseDTO(postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("없는 게시글입니다. id=" + id)));
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO) {

        Posts findPosts = postsRepository.findById(id).orElseThrow(() ->
            new IllegalStateException(("없는 게시글입니다. id=" + id)));

        findPosts.update(requestDTO.getTitle() , requestDTO.getContent());

        return id;
    }
}
