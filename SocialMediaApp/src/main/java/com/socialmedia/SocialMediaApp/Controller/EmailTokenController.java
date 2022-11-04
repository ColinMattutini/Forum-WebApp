package com.socialmedia.SocialMediaApp.Controller;

import com.socialmedia.SocialMediaApp.Service.EmailTokenService;
import com.socialmedia.SocialMediaApp.Service.EmailTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailTokenController {

    private final EmailTokenServiceImpl emailTokenService;

    @GetMapping("/user/confirm")
    public String confirm(@RequestParam("token") String token){
        return emailTokenService.confirmToken(token);
    }
}
