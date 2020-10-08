package com.mola.auth.dao;

import com.mola.auth.enyity.Employee;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 员工表 Dao
 *
 * @author wisdom
 * @date 2019-01-23 17:08:18
 */
@Repository
public interface EmployeeDao extends Mapper<Employee>, InsertListMapper<Employee> {

}
