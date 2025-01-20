package github.tourism.web.controller.goods;

import github.tourism.data.entity.goods.Goods;
import github.tourism.service.goods.GoodsService;
import github.tourism.web.dto.goods.GoodsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    // 굿즈 전체 목록 조회
    @GetMapping
    public ResponseEntity<PagedModel<GoodsResponse>> getAllGoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        Page<GoodsResponse> goods = goodsService.getAllGoods(page,size);
        return ResponseEntity.ok(new PagedModel<>(goods));
    }

    // 판매중인 굿즈만 조회 (판매일자 고려) -> 결국은 전체 조회에 해당 (현재는..)
    @GetMapping("/available")
    public ResponseEntity<Page<GoodsResponse>> getAvailableGoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        return ResponseEntity.ok(goodsService.getAvailableGoods(page,size));
    }

    // 굿즈 상세조회
    @GetMapping("/details/{goodId}")
    public ResponseEntity<Goods> getGoodsDetail(@PathVariable Integer goodId) {
        Goods goods =  goodsService.getGoodsDetail(goodId);
        return ResponseEntity.ok(goods);
    }

    // 굿즈 카테고리별 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<PagedModel<GoodsResponse>> getAllGoodsByCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @PathVariable String category
    ) {
        Page<GoodsResponse> responses =  goodsService.getGoodsByCategory(page,size,category);
        return ResponseEntity.ok(new PagedModel<>(responses));
    }

}
