package com.korit.board_back.controller;

import com.korit.board_back.dto.MailDTO;
import com.korit.board_back.service.implement.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody MailDTO mailDTO) throws MessagingException {
        return mailService.sendSimpleMessage(mailDTO.getEmail());
    }
}