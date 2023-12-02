package com.example.todocard.service;

import com.example.todocard.dto.CommentRequestDto;
import com.example.todocard.dto.CommentResponseDto;
import com.example.todocard.entity.Comment;
import com.example.todocard.entity.Post;
import com.example.todocard.entity.User;
import com.example.todocard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;


    // 댓글 생성
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postService.getPost(requestDto.getPostId());

        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);

        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    // 댓글 단건조회
    public CommentResponseDto getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(User user, Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = getComment(user, commentId);

        comment.setText(commentRequestDto.getText());

        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    public void deleteComment(User user, Long commentId) {
        Comment comment = getComment(user, commentId);

        commentRepository.delete(comment);
    }


    // 검증
    public Comment getComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return comment;
    }
}
