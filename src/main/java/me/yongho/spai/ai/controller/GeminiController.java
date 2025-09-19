package me.yongho.spai.ai.controller;

import me.yongho.spai.ai.dto.QuestionRequest;
import me.yongho.spai.ai.service.GeminiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public Mono<String> askGemini(@RequestBody QuestionRequest request) {
        return geminiService.getCompletion(request.question());
    }
}