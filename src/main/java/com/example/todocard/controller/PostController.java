package com.example.todocard.controller;

import com.example.todocard.dto.CommonResponseDto;
import com.example.todocard.dto.PostRequestDto;
import com.example.todocard.dto.PostResponseDto;
import com.example.todocard.service.PostService;
import com.example.todocard.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 메인 페이지 조회(게시물 전체 조회)
    @GetMapping
    public List<PostResponseDto> getPostList() {
        List<PostResponseDto> responseDto = postService.getPostList();
        return responseDto;
    }

    // 생성
    @PostMapping()
    public ResponseEntity<PostResponseDto> postPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.createPost(postRequestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    // 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> getPost(@PathVariable Long postId) {
        try {
            PostResponseDto responseDto = postService.getPostById(postId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 수정(title, content)
    @PutMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> putPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        try {
            PostResponseDto responseDto = postService.update(userDetails.getUser(), postId, postRequestDto);
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        try {
            postService.deletePost(userDetails.getUser(), postId);
            return ResponseEntity.ok().body(new CommonResponseDto("게시물 삭제가 완료되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
