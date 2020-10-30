package com.mola.bi.web;

import com.mola.bi.dao.AssetBillDao;
import com.mola.bi.ds.TargetDs;
import com.mola.bi.entity.AssetBill;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hatim
 */
@Slf4j
@RestController
@RequestMapping("asset/bill")
public class AssetBillController {
    @Autowired
    AssetBillDao assetBillDao;

    @ApiOperation(value = "test1", notes = "test1")
    @GetMapping("/test1")
    public Integer test1() {
        return assetBillDao.selectCount(AssetBill.builder().build());
    }

    @TargetDs(TargetDs.CLICK_HOUSE_DS)
    @ApiOperation(value = "test2", notes = "test2")
    @GetMapping("/test2")
    public AssetBill test2() {
        return assetBillDao.selectByPrimaryKey("173038070963728384");
    }
}
