package github.tourism.data.entity.goods;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Goods")
public class Goods {

    @Id @GeneratedValue
    @Column(name = "goods_id")
    private Integer goodId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_image")
    private String goodsImage;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "sale_start_time")
    private Timestamp saleStartTime;

    @Column(name = "sale_end_time")
    private Timestamp saleEndTime;

    @Column(name = "goods_size")
    private String goodsSize;

    @Column(name = "goods_weight")
    private String goodsWeight;

    @Column(name = "summary")
    private String summary;

    @Column(name = "category")
    private String category;

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

