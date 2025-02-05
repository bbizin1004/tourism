package github.tourism.web.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenderTop7ResponseDTO {

    private int year;
    private int month;
    private List<GenderTop7DTO> data;

}
