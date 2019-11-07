package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.common.Result;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.service.LitemallCategoryService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.wx.vo.CatalogIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.linlinjava.litemall.db.domain.LitemallGoods.Column.categoryId;

@Api
@RestController
@RequestMapping("/wx/catalogV2")
@Validated
public class WxCatalogV2Controller {
    private final Log logger = LogFactory.getLog(WxCatalogV2Controller.class);

    @Autowired
    private LitemallCategoryService categoryService;
    @Autowired
    private LitemallGoodsService goodsService;


    @GetMapping
    @ApiOperation("获取所有类目，只有一级")
    public Result<CatalogIndexVO> index(@RequestParam(required = false) Integer categoryId) {
        CatalogIndexVO vo = new CatalogIndexVO();
        List<LitemallCategory> categoriesList = categoryService.queryL1();
        if (categoriesList.size() == 0) {
            return Result.ok(vo);
        }
        vo.setCategoriesList(categoriesList);
        if (null == categoryId) {
            vo.setCurrentCategory(categoriesList.get(0));
            vo.setCurrentGoodsList(goodsService.queryByCategory(categoriesList.get(0).getId()));
        } else {
            vo.setCurrentCategory(categoriesList.stream().filter(t -> t.getId().equals(categoryId)).findAny().orElse(null));
            vo.setCurrentGoodsList(goodsService.queryByCategory(categoryId));
        }

        return Result.ok(vo);
    }
}