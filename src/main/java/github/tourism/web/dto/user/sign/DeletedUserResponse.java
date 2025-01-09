package github.tourism.web.dto.user.sign;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeletedUserResponse {
    private boolean available;
    private String message;

    public DeletedUserResponse(boolean available, String message) {
        this.message = message;
        this.available = available;
    }

}
