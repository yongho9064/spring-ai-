package me.yongho.spai.ai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

// 응답 JSON에 모르는 필드가 있어도 에러가 나지 않도록 설정
@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiResponse(List<Candidate> candidates) {
    public record Candidate(Content content) {}
    public record Content(List<Part> parts, String role) {}
    public record Part(String text) {}

    // 응답에서 첫 번째 텍스트를 안전하게 추출하는 헬퍼 메소드
    public Optional<String> firstText() {
        if (candidates == null || candidates.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(candidates.get(0).content.parts.get(0).text);
    }
}
