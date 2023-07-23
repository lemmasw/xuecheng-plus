package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @description TODO
 * @date 2023/7/19 8:13
 */
@Slf4j
@RestController
@Api(value = "课程分类信息",tags = "课程分类信息")
public class CourseCategoryController {

    @Autowired
    CourseCategoryService courseCategoryService;
    @ApiOperation("课程分类信息接口")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
                return courseCategoryService.queryTreeNodes("1");
    }
}
