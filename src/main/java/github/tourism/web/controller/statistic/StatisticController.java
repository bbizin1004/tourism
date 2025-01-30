package github.tourism.web.controller.statistic;


import github.tourism.service.statistic.StatisticService;
import github.tourism.web.advice.ApiResponse;
import github.tourism.web.dto.statistic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/gender")
    public ResponseEntity<ApiResponse<List<GenderResponseDTO>>> showGenderStatistic() {
        try {
            List<GenderResponseDTO> genderStatistics = statisticService.getGenderStatistics();
            return ResponseEntity.ok(ApiResponse.onSuccess(genderStatistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    //성별통계-월별 방문인구 많은순으로 상위 7개 나라 조회
    @GetMapping("/genderTop7Population")
    public ResponseEntity<ApiResponse<List<GenderTop7ByYearAndMonthDTO>>> getGenderTop7ByMonth(
            @RequestParam int year, @RequestParam int month) {
        try {
            List<GenderTop7ByYearAndMonthDTO> genderStatistics = statisticService.getGenderTop7ByMonth(year, month);
            return ResponseEntity.ok(ApiResponse.onSuccess(genderStatistics));
        }   catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    //성별통계-연도별 나라의 총방문객수 상위7개 합산 데이터 조회
    @GetMapping("/genderTop7ByYear")
    public ResponseEntity<ApiResponse<List<GenderTop7ByYearAndMonthDTO>>> getGenderTop7ByYear(
            @RequestParam int year) {
        try {
            List<GenderTop7ByYearAndMonthDTO> top7ByYear = statisticService.getGenderTop7ByYear(year);
            return ResponseEntity.ok(ApiResponse.onSuccess(top7ByYear));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }




    @GetMapping("/purpose")
    public ResponseEntity<ApiResponse<List<PurposeResponseDTO>>> showPurposeStatistic() {
        try {
            List<PurposeResponseDTO> purposeStatistics = statisticService.getPurposeStatistics();
            return ResponseEntity.ok(ApiResponse.onSuccess(purposeStatistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    //목적통계-월별 탑7 나라 조회
    @GetMapping("/purposeTop7ByMonth")
    public ResponseEntity<ApiResponse<List<PurposeTop7ResponseDTO>>> getPurposeTop7ByMonth(
            @RequestParam int year, @RequestParam int month) {
        try {
            List<PurposeTop7ResponseDTO> top7Purpose = statisticService.getTop7PurposeByMonth(year, month);
            return ResponseEntity.ok(ApiResponse.onSuccess(top7Purpose));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }
    //목적통계-연도별 탑7 나라 데이터 합산 조회
    @GetMapping("/purposeTop7ByYear")
    public ResponseEntity<ApiResponse<List<PurposeTop7ResponseDTO>>> getPurposeTop7ByYear(
            @RequestParam int year) {
        try {
            List<PurposeTop7ResponseDTO> top7PurposeByYear = statisticService.getTop7PurposeByYear(year);
            return ResponseEntity.ok(ApiResponse.onSuccess(top7PurposeByYear));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }


    @GetMapping("/rankplace")
    public ResponseEntity<ApiResponse<List<RankPlaceResponseDTO>>> showRankPlaceStatistic() {
        try {
            List<RankPlaceResponseDTO> rankPlaceStatistics = statisticService.getRankPlace();
            return ResponseEntity.ok(ApiResponse.onSuccess(rankPlaceStatistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

    @GetMapping("/visitlist")
    public ResponseEntity<ApiResponse<List<VisitListResponseDTO>>> showVisitListStatistic() {
        try {
            List<VisitListResponseDTO> visitListStatistics = statisticService.getVisitListStatistics();
            return ResponseEntity.ok(ApiResponse.onSuccess(visitListStatistics));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }


}
