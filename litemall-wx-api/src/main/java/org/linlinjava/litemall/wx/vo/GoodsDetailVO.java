package org.linlinjava.litemall.wx.vo;

import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallGoodsAttribute;
import org.linlinjava.litemall.db.domain.LitemallGoodsProduct;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVO {
    private LitemallGoods goods;
    private Object specificationList;
    private List<LitemallGoodsProduct> productList;
    private List<LitemallGoodsAttribute> attribute;
    private String shareImage;

}
