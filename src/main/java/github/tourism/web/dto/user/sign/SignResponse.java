package github.tourism.web.dto.user.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignResponse {
    private boolean available;
    private String message;

    public SignResponse(boolean available, String message) {
        this.message = message;
        this.available = available;
    }

}
