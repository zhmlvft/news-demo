package com.zhm.service;

import com.zhm.model.NewsType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 16-12-30.
 */
@Mapper
@Service("newsTypeService")
public interface NewsTypeService {
    @Insert("insert into news_type(channelId,name) values(#{data.channelId},#{data.name})")
    void saveInfo(@Param("data") NewsType type);
    @Delete("delete from news_type")
    void delAll();
    @Select("select * from news_type")
    List<NewsType> findAll();
}
