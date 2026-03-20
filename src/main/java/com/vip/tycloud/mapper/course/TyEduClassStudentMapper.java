package com.vip.tycloud.mapper.course;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vip.tycloud.entity.course.TyEduClassStudent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程与班级 功能模块 - 班级学员关联 - Mapper 接口。
 */
@Mapper
public interface TyEduClassStudentMapper extends BaseMapper<TyEduClassStudent> {
}


