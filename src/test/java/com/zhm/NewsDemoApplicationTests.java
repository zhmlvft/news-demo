package com.zhm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhm.model.News;
import com.zhm.model.NewsType;
import com.zhm.service.NewsService;
import com.zhm.service.NewsTypeService;
import com.zhm.utils.NewsUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsDemoApplicationTests {
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsTypeService newsTypeService;
	@Test
	public void loadNewsType() throws IOException {
		newsTypeService.delAll();
		newsService.delAll();
		String httpUrl = "http://route.showapi.com/109-34?showapi_appid=29976&showapi_sign=0f1c789c5e77428a8a2a720bb93f1b5f";
		String httpArg = "";
		String jsonResult = NewsUtils.INSTANCE.request(httpUrl);
		System.out.println(jsonResult);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readValue(jsonResult, JsonNode.class);
		JsonNode listData = json.get("showapi_res_body");
		JsonNode aryData = listData.get("channelList");
		for(int i=0;i<aryData.size();i++){
			JsonNode tmp = aryData.get(i);
			NewsType type = new NewsType();
			type.setChannelId(tmp.get("channelId").asText());
			type.setName(tmp.get("name").asText());
			newsTypeService.saveInfo(type);
		}
		newsTypeService.findAll().stream().forEach(type->{
				loadNews(type);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	private void loadNews(NewsType type){
		try {
			System.out.println(type.getChannelId());
			for(int j=1;j<10;j++){
				System.out.println(j+"-----------");
				String httpUrl = "http://route.showapi.com/109-35?showapi_appid=29976&channelId="+type.getChannelId()+"&channelName="+URLEncoder.encode(type.getName(), "UTF-8")+"&title=&page="+j+"&needContent=1&needHtml=1&needAllList=1&maxResult=20&showapi_sign=0f1c789c5e77428a8a2a720bb93f1b5f";
				String jsonResult = NewsUtils.INSTANCE.request(httpUrl);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode json = mapper.readValue(jsonResult, JsonNode.class);
				JsonNode listData = json.get("showapi_res_body");
				JsonNode pageData = listData.get("pagebean");
				if(pageData!=null){
					int allNum = pageData.get("allNum").asInt();
					int allPages = pageData.get("allPages").asInt();
					JsonNode contentData = pageData.get("contentlist");
					for(int i=0;i<contentData.size();i++) {
						JsonNode tmp = contentData.get(i);
						if (tmp.get("imageurls").size() == 0) {
							continue;
						}
						News news = new News();
						news.setPubDate(tmp.get("pubDate").asText());
						news.setDescription(tmp.get("desc").asText());
						if (tmp.get("imageurls").size() > 0) {
							JsonNode imgs = tmp.get("imageurls").get(0);
							news.setPicUrl(imgs.get("url").asText());
						}
						news.setTitle(tmp.get("title").asText());
						news.setLink(tmp.get("link").asText());
						news.setChannelId(tmp.get("channelId").asText());
						news.setChannelName(tmp.get("channelName").asText());
						if (tmp.get("content") != null) {
							news.setContent(tmp.get("content").asText());
						}
						if (tmp.get("html") != null) {
							news.setHtml(tmp.get("html").asText());
						}
						if (tmp.get("source") != null) {
							news.setSource(tmp.get("source").asText());
						}
						newsService.saveInfo(news);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
