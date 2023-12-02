package com.example.todocard.service;

import com.example.todocard.dto.PostRequestDto;
import com.example.todocard.dto.PostResponseDto;
import com.example.todocard.entity.Post;
import com.example.todocard.entity.User;
import com.example.todocard.repository.PostRepository;
import com.example.todocard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    // 메인 페이지 조회(게시물 전체 조회)
    public List<PostResponseDto> getPostList() {
        return postRepository.findAllByOrderByCreateDateDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 생성
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto);
        post.setUser(user);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }

    // 조회(유저)
    public PostResponseDto getPostById(Long postId){
        Post post = getPost(postId);

        return new PostResponseDto(post);
    }

    // 수정
    @Transactional
    public PostResponseDto update(User user, Long postId, PostRequestDto postRequestDto) {
        Post post = getUserPost(user, postId);

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        return new PostResponseDto(post);
    }

    // 삭제
    public void deletePost(User user, Long postId) {
        Post post = getUserPost(user, postId);

        postRepository.delete(post);
    }

    public Post getPost(Long postId) {

        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public Post getUserPost(User user, Long postId) {
        Post post = getPost(postId);

        if(!user.getId().equals(post.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return post;
    }
}
