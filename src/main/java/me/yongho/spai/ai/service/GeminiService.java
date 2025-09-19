package me.yongho.spai.ai.service;

import me.yongho.spai.ai.dto.GeminiRequest;
import me.yongho.spai.ai.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.system.instruction}")
    private String systemInstruction;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getCompletion(String prompt) {
        GeminiRequest geminiRequest = GeminiRequest.createWithSystemInstruction(systemInstruction, prompt);

        return webClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .bodyValue(geminiRequest)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .map(response -> response.firstText().orElse("No content available."));
    }
}
