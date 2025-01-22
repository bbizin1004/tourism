package github.tourism.web.controller.statistic;


import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.service.statistic.StatisticService;
import github.tourism.web.dto.statistic.GenderStatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/genderTop7Population")
    public ResponseEntity<List<GenderStatisticDTO>> getTop7Population(@RequestParam int year, @RequestParam int month) {

        List<GenderStatisticDTO> genderStatistics = statisticService.getTop7(year, month);
        return ResponseEntity.ok(genderStatistics);

    }









}
