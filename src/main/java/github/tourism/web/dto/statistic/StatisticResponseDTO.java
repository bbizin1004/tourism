package github.tourism.web.dto.statistic;

import org.springframework.http.HttpStatus;

public class StatisticResponseDTO {
    private HttpStatus httpStatus;
    private boolean isSuccess;
    private int statusCode;
    private String message;
    public StatisticResponseDTO(boolean isSuccess, String message) {
        this.httpStatus = isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        this.isSuccess = isSuccess;
        this.statusCode = isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
    // 해당 DTO를 만들어서 사용할 때
    //특정한 요구사항: 특정 통계 데이터에 대한 요구사항이 있을 때.
    //명확한 분리: 다양한 API 응답이 서로 다른 구조를 필요로 할 때.
    //유지보수: 특정 응답에 대한 유지보수가 필요할 때.
}
