package org.linlinjava.litemall.wx.vo;

import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallGoods;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexVO {
    private List<LitemallCategory> categoriesList;
    private List<LitemallGoods> newGoodsList;
    private List<LitemallGoods> hotGoodsList;
}
