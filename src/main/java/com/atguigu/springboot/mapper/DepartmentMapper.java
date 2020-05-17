package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author 杨少伟
 * @create 2020-05-17-19:41
 */
//指定这是一个操作数据库的mapper
//@Mapper
public interface DepartmentMapper {

    @Select("select id,department_name from department where id = #{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    int delDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set department_name = #{departmentName} where id = #{id}")
    int updateDept(Department dept);

}
