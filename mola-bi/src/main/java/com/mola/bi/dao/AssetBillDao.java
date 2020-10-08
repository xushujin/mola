package com.mola.bi.dao;

import com.mola.bi.entity.AssetBill;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AssetBillDao extends Mapper<AssetBill>, InsertListMapper<AssetBill> {
}
