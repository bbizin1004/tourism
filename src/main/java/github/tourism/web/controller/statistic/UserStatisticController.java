package github.tourism.web.controller.statistic;

import github.tourism.config.util.webSocket.WebSocketController;
import github.tourism.data.entity.statistic.Statistic;
import github.tourism.service.statistic.UserStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/user")
public class UserStatisticController {
    private final UserStatisticService userStatisticService;
    private final WebSocketController webSocketController;

    @PostMapping("/{category}/like")
    public void likePlace(@PathVariable String category) {
        Statistic statistic = userStatisticService.updatePlaceAndLikeCount(category);
        webSocketController.sendLikeCountUpdate(
                statistic.getCategory(),
                statistic.getPlaceName(),
                statistic.getPlaceLikeNum(),
                statistic.getPlaceImage()
        );
    }

    @GetMapping("/top")
    public List<Statistic> getTopStatistics() {
        return userStatisticService.getTopStatistics();
    }
}
