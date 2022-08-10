package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //offset起始行行号，limit这一页显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //查询表里一共多少数据，Param取别名
    //sql中需要动态选择拼参数，如果方法内只有一个参数，则必须加别名。
    int selectDiscussPostRows(@Param("userId") int userId);





}
