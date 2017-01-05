package com.zhm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.zhm.exception.CommonException;
import com.zhm.model.CommonResponse;
import com.zhm.model.News;
import com.zhm.model.NewsType;
import com.zhm.service.NewsService;
import com.zhm.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhm on 16-12-30.
 */
@Controller
public class HomeController extends BaseController{
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsTypeService newsTypeService;
    @RequestMapping(value = "/news")
    public @ResponseBody CommonResponse newslist(String token, String data){
        JsonNode json = parseStringToJson(data);
        if(StringUtils.isEmpty(json)){
            throw new CommonException("客户端提交的data参数为空，请校验！");
        }
        if(!checkJson(json)){
            return null;
        }
        Pageable page = new PageRequest(json.get("page").asInt(),json.get("size").asInt());
        Map<String, Object> params = parseJsonToMap(json);
        params.put("start",(page.getPageNumber()-1)*page.getPageSize());
        List<News> result = newsService.findByCond(params, "id,title,channelId,description,link,picUrl,pubDate");
        long total = newsService.findNumsByCond(params);
        return buildResponse(new PageImpl<News>(result,page,total));
    }
    @RequestMapping(value = "/newsType")
    public @ResponseBody CommonResponse newsType(String token,String data){
        List<NewsType> results = newsTypeService.findAll();
        return buildResponse(results);
    }
}
