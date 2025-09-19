package me.yongho.spai.ai.dto;

import java.util.List;

// 요청 본문 DTO
public record GeminiRequest(List<Content> contents) {
    
    public record Content(List<Part> parts, String role) {}
    public record Part(String text) {}

    /**
     * 시스템 지시사항과 사용자 질문을 함께 담아 대화 형식의 요청 객체를 생성합니다.
     * @param systemInstruction 모델에게 부여할 역할/규칙
     * @param userPrompt 사용자의 실제 질문
     * @return 생성된 GeminiRequest 객체
     */
    public static GeminiRequest createWithSystemInstruction(String systemInstruction, String userPrompt) {
        // 1. 시스템 규칙 설정 (user가 model에게 지시하는 형식)
        Part systemPart = new Part(systemInstruction);
        Content systemContent = new Content(List.of(systemPart), "user");

        // 2. 모델이 규칙을 이해했다는 응답 (가상으로 만들어줌)
        Part modelAckPart = new Part("네, 알겠습니다. 지금부터 법률 전문가로서 법률 관련 질문에만 답변하겠습니다.");
        Content modelAckContent = new Content(List.of(modelAckPart), "model");

        // 3. 실제 사용자 질문
        Part userPart = new Part(userPrompt);
        Content userContent = new Content(List.of(userPart), "user");

        // 이 3가지를 순서대로 리스트에 담아 대화의 흐름을 만듬
        List<Content> conversationHistory = List.of(systemContent, modelAckContent, userContent);

        return new GeminiRequest(conversationHistory);
    }
}