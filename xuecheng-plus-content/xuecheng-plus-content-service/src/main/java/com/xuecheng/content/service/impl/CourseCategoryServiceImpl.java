package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @description 课程分类查询
 * @date 2023/7/19 10:01
 */
@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Resource
    CourseCategoryMapper courseCategoryMapper;
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
//        //调用mapper层的查询方法，获得指定id的结点和其所有子节点的list集合
//        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
//        //最终返回的列表
//        ArrayList<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
//        HashMap<String, CourseCategoryTreeDto> mapTemp = new HashMap<>();
//        courseCategoryTreeDtos.stream().forEach(item->{
//            mapTemp.put(item.getId(),item);
//            //只将根节点的下级节点放入list
//            if(item.getParentid().equals(id)){
//                categoryTreeDtos.add(item);
//            }
//            //取到该节点的父节点
//            CourseCategoryTreeDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
//            if(courseCategoryTreeDto!=null){
//                if(courseCategoryTreeDto.getChildrenTreeNodes() == null){
//                    courseCategoryTreeDto.setChildrenTreeNodes(
//                            new ArrayList<CourseCategoryTreeDto>());
//                                    }
//                    //向节点的下级节点list加入节点
//                    courseCategoryTreeDto.getChildrenTreeNodes().add(item);
//            }
//        });
//            return categoryTreeDtos;
        //通过mapper查询出该id节点和其所有子节点到一个集合中
        //创建一个返回结果的list集合，一个临时使用的Map集合
        //利用stream流遍历查询到的集合
        //将节点放入临时的Map集合中
        //然后将当前id的下一级子节点放入结果集合中
        //不是当前id的下一级节点会进行下面的
        //从临时集合中取到当前节点的父节点
        //如果父节点存在且该父节点的子节点为空，则创建一个新的list集合作为它的孩子节点集合，并向它的孩子节点集合中添加该节点
        //如果父节点存在但已经有子节点存在，则继续向它的孩子节点集合中添加该节点
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
        //创建一个返回结果的list集合，一个临时使用的Map集合
        ArrayList<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        HashMap<String, CourseCategoryTreeDto> tempMap = new HashMap<>();
        //利用stream流遍历查询到的集合
        courseCategoryTreeDtos.stream().forEach(item->{
            //将节点放入临时的Map集合中
            tempMap.put(item.getId(),item);
            //然后将当前id的下一级子节点放入结果集合中
            if(item.getParentid().equals(id)){
                categoryTreeDtos.add(item);
            }
            //不是当前id的下一级节点会进行下面的
            //从临时集合中取到当前节点的父节点
            CourseCategoryTreeDto courseCategoryTreeDto = tempMap.get(item.getParentid());
            //如果父节点存在且该父节点的子节点为空，则创建一个新的list集合作为它的孩子节点集合，并向它的孩子节点集合中添加该节点
            //如果父节点存在但已经有子节点存在，则继续向它的孩子节点集合中添加该节点
            if(courseCategoryTreeDto!=null){
                if(courseCategoryTreeDto.getChildrenTreeNodes()==null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }

        });


        return categoryTreeDtos;
    }

}
