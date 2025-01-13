package github.tourism.service.cart;

import github.tourism.data.entity.cart.Cart;
import github.tourism.data.entity.goods.Goods;
import github.tourism.data.entity.user.User;
import github.tourism.data.repository.cart.CartRepository;
import github.tourism.data.repository.goods.GoodsRepository;
import github.tourism.data.repository.user.UserRepository;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.cart.CartListResponse;
import github.tourism.web.dto.cart.CartRequest;
import github.tourism.web.dto.cart.CartResponseWrapper;
import github.tourism.web.exception.CartNotFoundException;
import github.tourism.web.exception.GoodsNotFoundException;
import github.tourism.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;


    public Integer addToCart(CartRequest request) {

        User userId = userRepository.findById(request.getUserId())
                .orElseThrow(() ->  new NotFoundException(ErrorCode.USER_NOT_FOUNDED));

        Goods goodId = goodsRepository.findById(request.getGoodId())
                .orElseThrow(() -> new GoodsNotFoundException(ErrorCode.GOODS_NOT_FOUNDED));

        // 빌더 패턴 사용해 cart 객체 생성
        Cart cart = Cart.builder()
                .user(userId)
                .goods(goodId)
                .quantity(request.getQuantity())
                .build();

        Cart savedCart = cartRepository.save(cart);
        return savedCart.getCartId();
    }

    public List<CartListResponse> getCartByUserId(Integer userId) {
        List<Cart> cartList = cartRepository.findCartByUserId(userId);
        return cartList.stream()
                .map(cart -> new CartListResponse(
                        cart.getGoods().getGoodId(),
                        cart.getGoods().getGoodsName(),
                        cart.getGoods().getGoodsImage(),
                        cart.getGoods().getPrice(),
                        cart.getQuantity())).collect(Collectors.toList());

    }

    @Transactional
    public CartResponseWrapper updateCart(Integer cartId, int quantity) {
        return cartRepository.findByIdWithGoods(cartId)
                .map(cart -> {
                    cart.updateQuantity(quantity);
                    cartRepository.save(cart);
                    List<CartListResponse> updatedCart = cartRepository.findAll()
                            .stream()
                            .map(updateCart -> new CartListResponse(
                                    updateCart.getGoods().getGoodId(),
                                    updateCart.getGoods().getGoodsName(),
                                    updateCart.getGoods().getGoodsImage(),
                                    updateCart.getGoods().getPrice(),
                                    updateCart.getQuantity()))
                            .collect(Collectors.toList());

                    return new CartResponseWrapper("Success", "장바구니 수량이 변경되었습니다.", updatedCart);
                })

                .orElse(new CartResponseWrapper("Error", "해당 굿즈는 장바구니에서 찾을 수 없습니다.", null));
    }

    @Transactional
    public Integer deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(ErrorCode.CART_NOT_FOUNDED));
        cartRepository.deleteById(cartId);
        return cartId;
    }
}

