package com.zhm.service;

import com.zhm.model.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhm on 16-12-30.
 */
@Mapper
@Service("newsService")
public interface NewsService {

    @Delete("delete from news")
    void delAll();

    @Insert("insert into news(title,description,pubDate,picUrl,link,content,html,source,channelId,channelName) values(#{data.title},#{data.description},#{data.pubDate},#{data.picUrl},#{data.link},#{data.content},#{data.html},#{data.source},#{data.channelId},#{data.channelName})")
    void saveInfo(@Param("data") News news);


    @Select("<script>" +
                "select " +
                    "<choose>" +
                        "<when test=\"columns!=null\">" +
                            "${columns}" +
                        "</when>" +
                        "<otherwise>" +
                            "*"  +
                        "</otherwise>" +
                    "</choose>" +
                " from news where picUrl is not null " +
                    "<if test=\"data.data.title != null\">" +
                        "AND title like #{data.data.title} " +
                    "</if>" +
                    "<if test=\"data.data.channelId !=null\">" +
                        "AND channelId=#{data.data.channelId} " +
                    "</if>" +
                    "<if test=\"data.data.channelName !=null\">" +
                        "AND channelName=#{data.data.channelName} " +
                    "</if>" +
                " limit #{data.start},#{data.size}" +
            "</script>")
    List<News> findByCond(@Param("data") Map params,@Param("columns") String columns);


    @Select("<script>" +
                "select count(id) from news where picUrl is not null " +
                    "<if test=\"data.data.title != null\">" +
                        "AND title like #{data.data.title} " +
                    "</if>" +
                    "<if test=\"data.data.channelId !=null\">" +
                        "AND channelId=#{data.data.channelId} " +
                    "</if>" +
                    "<if test=\"data.data.channelName !=null\">" +
                        "AND channelName=#{data.data.channelName} " +
                    "</if>" +
            "</script>")
    long findNumsByCond(@Param("data") Map params);
}
