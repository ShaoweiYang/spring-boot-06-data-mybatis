package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;

/**
 * @author Shawn.Yang
 * @create 2020-05-17-20:31
 */
//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    void insertEmp(Employee employee);
}
