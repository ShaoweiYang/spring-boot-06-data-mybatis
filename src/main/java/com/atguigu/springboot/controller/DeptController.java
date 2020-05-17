package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.Department;
import com.atguigu.springboot.bean.Employee;
import com.atguigu.springboot.mapper.DepartmentMapper;
import com.atguigu.springboot.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shawn.Yang
 * @create 2020-05-17-19:49
 */
@RestController
public class DeptController {

    @Autowired
    @Qualifier("departmentMapper")
    private DepartmentMapper mapper;

    @Autowired
    @Qualifier("employeeMapper")
    private EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return mapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department dept) {
        mapper.insertDept(dept);
        return dept;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }
}
