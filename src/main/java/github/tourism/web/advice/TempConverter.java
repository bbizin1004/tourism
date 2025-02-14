package github.tourism.web.advice;

public class TempConverter {

    public static TempResponse.ResultDTO toResultDTO() {
        return TempResponse.ResultDTO.builder()
                .message("test")
                .data("결과 값을 출력")
                .build();
    }

    public static TempResponse.ExceptionDTO toExceptionDTO() {
        return TempResponse.ExceptionDTO.builder()
                .errorCode("HttpStatus 상태코드")
                .errorMessage("message 출력")
                .build();
    }
}