package github.tourism.web.dto.user.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SecessionResponse {
    private LocalDateTime deletedAt;
}
