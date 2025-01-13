package github.tourism.service.goods;

import github.tourism.data.entity.goods.Goods;
import github.tourism.data.repository.goods.GoodsRepository;
import github.tourism.web.dto.goods.GoodsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;


    public Page<GoodsResponse> getAllGoods(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Goods> goodsPage = goodsRepository.findAll(pageable);
        Page<GoodsResponse> goodsResponsePage = goodsPage.map(this::convertGoodsToResponse);
        return goodsResponsePage;
    }

    private GoodsResponse convertGoodsToResponse(Goods goods) {
        return new GoodsResponse(
                goods.getGoodId(),
                goods.getGoodsImage(),
                goods.getGoodsName(),
                goods.getPrice(),
                goods.getSummary()
        );
    }

    public Page<GoodsResponse> getAvailableGoods(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Page<Goods> availableGoods = goodsRepository.findAvailableGoods(currentTime,pageable);
        Page<GoodsResponse> goodsResponsePage = availableGoods.map(this::convertGoodsToResponse);
        return goodsResponsePage;

    }

    public Goods getGoodsDetail(Integer goodId) {
        Goods goods = goodsRepository.findById(goodId).orElse(null);
        return goods;
    }

    public Page<GoodsResponse> getGoodsByCategory(int page, int size, String category) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Goods> goodsPage =  goodsRepository.findAllByCategory(pageable,category);
        Page<GoodsResponse> goodsResponsePage = goodsPage.map(this::convertGoodsToResponse);
        return goodsResponsePage;
    }
}

