package com.marsh.controller;

import com.marsh.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "게시글 관련 API")
public class PostController {
    private final PostService postService;
}
