package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.RateLimit;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OpenAiChatModelTest {
    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    public void testChatModelSimple(){
        String result = chatModel.call("동국대학교는 뭐야?");
        System.out.println(result);

    }

    @Test
    public void testChatModelMessage(){
        UserMessage userMessage = new UserMessage("서울 올림픽에 대해 알려주세요.");
        SystemMessage systemMessage = new SystemMessage("답변은 최대한 간결하고 관련된 내용은 뉴스를 참고하라고 해 줘.");
        String result = chatModel.call(userMessage,systemMessage);
        System.out.println(result);
    }

    @Test
    public void testChatModelMessageContext(){
        var system = new SystemMessage("간략하게 답변해 주세요.");
        var message1 = new UserMessage("서울 올림픽에 대해 알려주세요");
        var assistant1 = new AssistantMessage("서울 올림픽, 공식명칭은 제24회 하계 올림픽대회는 1988년 9월 17일부터 10월 2일까지 대한민국 서울에서 개최되었습니다. 이 대회는 한국에서 처음으로 열린 올림픽 경기로, 총 159개 국가가 참여하였고 23종목의 경기가 진행되었습니다. 서울 올림픽은 성공적인 개최로 평가받았으며, 그로 인해 한국은 국제 스포츠 무대에서의 위상이 크게 향상되었습니다. 또한, 대회 기간 동안 평화와 화합을 강조하며 많은 이들에게 기억에 남는 현장이 되었습니다.");
        var message2 = new UserMessage("그럼 바로 그 이전 올림픽은 어디야?");
        var assistant2 = new AssistantMessage("바로 이전 올림픽은 1984년 하계 올림픽으로, 미국 로스앤젤레스에서 개최되었습니다. 이 대회는 7월 28일부터 8월 12일까지 진행되었고, 많은 국가가 참여한 강행된 올림픽 중 하나였습니다.");
        var message3 = new UserMessage("그럼 그 2개 올림픽 중 참여 국가는 어디가 많아?");
        String result = chatModel.call(system,message1,assistant1,message2,assistant2,message3);
        System.out.println(result);
    }
    @Test
    public void testChatGptPrompt(){
        List<Message> messages = List.of(
                new SystemMessage("간략하게 답변해 주세요."),
                new UserMessage("서울 올림픽에 대해 알려 주세요"),
                new AssistantMessage("서울 올림픽, 공식명칭은 제24회 하계 올림픽대회는 1988년 9월 17일부터 10월 2일까지 대한민국 서울에서 개최되었습니다. 이 대회는 한국에서 처음으로 열린 올림픽 경기로, 총 159개 국가가 참여하였고 23종목의 경기가 진행되었습니다. 서울 올림픽은 성공적인 개최로 평가받았으며, 그로 인해 한국은 국제 스포츠 무대에서의 위상이 크게 향상되었습니다. 또한, 대회 기간 동안 평화와 화합을 강조하며 많은 이들에게 기억에 남는 현장이 되었습니다."),
                new UserMessage("그럼 바로 그 이전 올림픽은 어디야?"),
                new AssistantMessage("바로 이전 올림픽은 1984년 하계 올림픽으로, 미국 로스앤젤레스에서 개최되었습니다. 이 대회는 7월 28일부터 8월 12일까지 진행되었고, 많은 국가가 참여한 강행된 올림픽 중 하나였습니다."),
                new UserMessage("그럼 그 두개의 올림픽중 참여 국가는 어디가 많아?")
        );

        ChatOptions chatOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O)
                .N(2)
                .temperature(1.0).build();

        Prompt prompt = new Prompt(messages,chatOptions);

        ChatResponse chatResponse = chatModel.call(prompt);

        // 토큰 사용량
        // 메시지로 전달하기 위해 소요된 프롬프트 토큰 수, 답변을 만들기 위해 소요된 토큰 수
        Usage usage = chatResponse.getMetadata().getUsage();
        System.out.println("promptTokens = " + usage.getPromptTokens() +
                ", completionTokens = " + usage.getCompletionTokens() +
                ", totalTokens = " + usage.getTotalTokens());

        // 요청 제한 상세
        // 계정의 분당 요청 제한, 남은 요청, 앞으로 몇 초 후 초기화 되는지
        RateLimit rateLimit = chatResponse.getMetadata().getRateLimit();
        System.out.println("requestLimit = " + rateLimit.getRequestsLimit() +
                ", requestRemaining = " + rateLimit.getRequestsRemaining() +
                ", requestReset = " + rateLimit.getRequestsReset());
        // 분당 사용할 수 있는 토큰 개수
        // 분당 최대 사용 토큰 개수, 현재 요청 처리후 남는 토큰 사용 개수, 앞으로 몇 초 후 초기화?
        System.out.println("tokensLimit = " + rateLimit.getTokensLimit() +
                ", tokensRemaining = " + rateLimit.getTokensRemaining() +
                ", tokensReset = " + rateLimit.getTokensReset());

        for (Generation generation : chatResponse.getResults()) {
            System.out.println("response = " + generation.getOutput().getText());
        }
    }
}
