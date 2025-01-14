package github.tourism.data.entity.map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Street {

    @Id @GeneratedValue
    @Column(name = "street_id")
    private Integer id;

    @Column(name = "key_word")
    private String keyword;

    private String alias;

    @Column(name = "street_name")
    private String streetName;

    private String addr;
    private String si;
    private String goo;
    private String dong;
    private String si2;
    private String goo2;
    private String dong2;
    private BigDecimal lat;
    private BigDecimal lng;
}
