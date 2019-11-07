package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.common.Result;
import org.linlinjava.litemall.core.system.SystemConfig;
import org.linlinjava.litemall.db.service.LitemallCategoryService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.wx.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/wx/home")
public class WxHomeController {
    private final Log logger = LogFactory.getLog(WxHomeController.class);
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private LitemallCategoryService categoryService;

    ExecutorService executorService = Executors.newFixedThreadPool(10, r -> new Thread(r, "HomeIndexThread"));

    @GetMapping
    @ApiOperation("首页")
    public Result<IndexVO> index() {
        FutureTask<List> categoryFutureTask = new FutureTask<>(() -> categoryService.queryChannel());
        FutureTask<List> newGoodsFutureTask = new FutureTask<>(() -> goodsService.queryByNew(0,
                SystemConfig.getNewLimit()));
        FutureTask<List> hotGoodsFutureTask = new FutureTask<>(() -> goodsService.queryByHot(0,
                SystemConfig.getHotLimit()));

        executorService.submit(categoryFutureTask);
        executorService.submit(newGoodsFutureTask);
        executorService.submit(hotGoodsFutureTask);

        IndexVO vo = new IndexVO();
        try {
            vo.setCategoriesList(categoryFutureTask.get());
            vo.setNewGoodsList(newGoodsFutureTask.get());
            vo.setHotGoodsList(hotGoodsFutureTask.get());
        } catch (Exception e) {
            //TODO 异常处理
            e.printStackTrace();
        }
        return Result.ok(vo);
    }
}