package github.tourism.web.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartResponseWrapper {

    private String status;
    private String message;
    private List<CartListResponse> cartList;


}

