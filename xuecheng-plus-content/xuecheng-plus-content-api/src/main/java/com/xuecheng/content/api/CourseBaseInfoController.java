package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author lmj123456
 * @version 1.0
 * @description 课程信息编辑接口
 * @date 2023/7/15 11:04
 */
@Api(value = "课程信息接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParamsDto){
        CourseBase courseBase = new CourseBase();
        courseBase.setName("测试分页");
        courseBase.setCreateDate(LocalDateTime.now());
        ArrayList<CourseBase> list = new ArrayList<>();
        list.add(courseBase);
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, 10, 1, 10);
        return courseBasePageResult;
    }
}