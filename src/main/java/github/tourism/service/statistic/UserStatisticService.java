package github.tourism.service.statistic;

import github.tourism.config.util.webSocket.WebSocketController;
import github.tourism.data.entity.map.Map;
import github.tourism.data.entity.statistic.Statistic;
import github.tourism.data.entity.user.User;
import github.tourism.data.repository.map.MapRepository;
import github.tourism.data.repository.statistic.UserStatisticRepository;
import github.tourism.data.repository.user.UserRepository;
import github.tourism.service.favPlace.FavPlaceService;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatisticService {
    private final UserStatisticRepository userStatisticRepository;
    private final MapRepository mapRepository;
    private final WebSocketController webSocketController;

    @Transactional
    public Statistic updatePlaceAndLikeCount(String category) {
        Map map = mapRepository.findByCategory(category)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_SAVED_PLACE));

        Statistic statistic = userStatisticRepository.findByMapId(map.getMapId())
                .orElse(createStatistic(map));

        userStatisticRepository.save(statistic);

        updateRankings();

        List<Statistic> topStatistics = getTopStatistics();
        webSocketController.sendTopStatisticsUpdate(topStatistics);

        return statistic;
    }

    private Statistic createStatistic(Map map) {
        return Statistic.builder()
                .mapId(map.getMapId())
                .placeName(map.getPlace_name())
                .placeLikeNum(map.getLikemarkCount())
                .placeImage(map.getPlace_image())
                .category(map.getCategory())
                .userRanking(0)
                .build();
    }

    private void updateRankings() {
        List<Statistic> statistics = userStatisticRepository.findAll(Sort.by(Sort.Direction.DESC, "place_like_num"));
        int rank = 1;
        for (Statistic stat : statistics) {
            stat.setUserRanking(rank++);
            userStatisticRepository.save(stat);
        }
    }

    public List<Statistic> getTopStatistics() {
        Page<Statistic> topStatisticsPage = userStatisticRepository.findTopStatistics(PageRequest.of(0, 3));
        return topStatisticsPage.getContent();
    }
}