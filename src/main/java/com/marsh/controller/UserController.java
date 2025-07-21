package com.marsh.controller;

import com.marsh.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "사용자 관련 API")
public class UserController {
    private final UserService userService;
}
