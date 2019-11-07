package org.linlinjava.litemall.wx.vo;

import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallGoods;

import java.util.List;

import lombok.Data;

@Data
public class CatalogIndexVO {
    private List<LitemallCategory> categoriesList;
    private List<LitemallGoods> currentGoodsList;
    private LitemallCategory currentCategory;
}
