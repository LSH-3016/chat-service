package com.example.rapa.chatT.controller;

import com.example.rapa.chatT.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String nickname,
                               HttpSession session,
                               Model model) {
        // 이미 로그인한 사용자가 /register에 접근하지 않도록 처리
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard"; // 로그인 후 대시보드로 리다이렉트
        }

        try {
            userService.registerUser(username, password, nickname);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("errormessage", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        // 세션에 로그인 정보가 있으면 대시보드로 리다이렉트
        if (session.getAttribute("user") != null) {
            return "redirect:/chatroom"; // 로그인 후 대시보드로 리다이렉트
        }
        return "login";
    }


}
