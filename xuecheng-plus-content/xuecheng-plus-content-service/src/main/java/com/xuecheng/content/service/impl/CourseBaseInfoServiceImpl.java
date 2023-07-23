package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @description TODO
 * @date 2023/7/18 21:01
 */
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Resource
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
//        //1.设置查询条件
//        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
//        //1.1 课程名称条件
//        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),
//                CourseBase::getName,queryCourseParamsDto.getCourseName());
//        //1.2 审核状态条件
//        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),
//                CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
//        //1.3 发布状态条件
//        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),
//                CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());
//        //2.调用分页查询功能
//        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
//        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, queryWrapper);
//        //3.取出参数
//        List<CourseBase> records = courseBasePage.getRecords();//数据列表
//        long total = courseBasePage.getTotal();//总条数
//        //4.构造返回结果
//        PageResult<CourseBase> courseBasePageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
//        return courseBasePageResult;
        //1.构建查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //2.添加查询条件
        //2.1 课程名称
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),
                CourseBase::getName,queryCourseParamsDto.getCourseName());
        //2.2 审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),
                CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
        //2.3 发布状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),
                CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());
        //3.构建分页对象
        Page<CourseBase> page = new Page<>();
        //4.调用Mapper分页功能
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, queryWrapper);
        //5.取出参数
        List<CourseBase> records = courseBasePage.getRecords();//数据列表
        long total = courseBasePage.getTotal();//总条数
        //6.构建返回结果对象
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;

    }
}
