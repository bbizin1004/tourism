package github.tourism.config.util.webSocket;

import github.tourism.data.entity.statistic.Statistic;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendLikeCountUpdate(String category, String placeName, Integer newCount, String placeImage) {
        Map<String, Object> message = new HashMap<>();
        message.put("category", category);
        message.put("placeName", placeName);
        message.put("likeCount", newCount);
        message.put("placeImage", placeImage);

        messagingTemplate.convertAndSend("/topic/likeCount/" + category, message);
    }

    public void sendTopStatisticsUpdate(List<Statistic> topStatistics) {
        messagingTemplate.convertAndSend("/topic/topStatistics", topStatistics);
    }
}

