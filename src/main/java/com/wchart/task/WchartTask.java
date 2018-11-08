package com.wchart.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wchart.util.AccessToken;
import com.wchart.util.AccessTokenError;
import com.wchart.util.Constant;
import com.wchart.util.HttpClientUtils;
import com.wchart.util.ReturnMsg;
import com.wchart.util.WcahrtMenu;
import com.wchart.util.Wchartcontext;

@Component
public class WchartTask { 
	//@Scheduled(cron="0/30 * * * * ? ") 
	public void cron() throws IOException {
		System.out.println("====>>>  定时任务开始   <<<=====");
		
		String token = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
				Constant.APPID+"&secret="+Constant.APP_SECTET);
		System.out.println("-----获取到的信息-----\n"+token);
		
		ObjectMapper mapper= new ObjectMapper();
		//忽略多余字段
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AccessToken accessToken=null;
		AccessTokenError accessTokenError=null;		
		try {			
//			returnMsg = mapper.readValue(token, ReturnMsg.class);
//			System.out.println("-------------   "+returnMsg);
			accessToken = mapper.readValue(token, AccessToken.class);
			Wchartcontext.accessToken = accessToken;
			Wchartcontext.accessTokenError = null;
	
		} catch (IOException e) {
			System.out.println("--------------------获取token失败------------------------");
			try {
				
//				returnMsg = mapper.readValue(token, ReturnMsg.class);
//				System.out.println("-------------   "+returnMsg.toString());
				accessTokenError = mapper.readValue(token, AccessTokenError.class);
				Wchartcontext.accessToken = null;
				Wchartcontext.accessTokenError = accessTokenError;				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		menu(accessToken);
	
	}
	
	
	private void menu(AccessToken accessToken) {
		
		
		WcahrtMenu m1=new WcahrtMenu();
		m1.setId(1);
		m1.setName("百度首页");
		m1.setType("view");
		m1.setUrl("https://www.hao123.com/");
		
		
		WcahrtMenu m2=new WcahrtMenu();
		m2.setId(2);
		m2.setName("多级菜单");
		List<WcahrtMenu> m2_sub= new ArrayList<WcahrtMenu>();
		WcahrtMenu m21=new WcahrtMenu();
		m21.setId(1);
		m21.setName("点击事件");
		m21.setType("click");
		m21.setKey("dianji");
		m2_sub.add(m21);
		WcahrtMenu m22=new WcahrtMenu();
		m22.setId(2);
		m22.setName("百度首页");
		m22.setType("view");
		m22.setUrl("https://www.hao123.com/");
		m2_sub.add(m22);
		m2.setSub_button(m2_sub);
		
		List<WcahrtMenu> menu= new ArrayList<WcahrtMenu>();
		menu.add(m1);
		menu.add(m2);
		
		Map<String,List<WcahrtMenu>> button =new HashMap<>();
		button.put("button", menu);
		
		ObjectMapper objectMapper = new ObjectMapper();
		// 过滤对象的null属性.
		objectMapper.setSerializationInclusion(Include.NON_NULL);  
		String jsonStr=null;
		try {
			jsonStr = new ObjectMapper().writeValueAsString(button);
			
			System.out.println("-----json字符串------\n "+jsonStr);
//			String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ReturnMsg.getAccess_token();
			
			String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken.getAccess_token();
			String msg = HttpClientUtils.postJson(url, jsonStr);
			AccessTokenError accessTokenError = new ObjectMapper().readValue(msg, AccessTokenError.class);
//			System.out.println("=====>>>"+accessTokenError);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
