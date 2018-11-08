package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.WritableTypeId.Inclusion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wchart.msg.MsgKit;
import com.wchart.util.AccessToken;
import com.wchart.util.AccessTokenError;
import com.wchart.util.Constant;
import com.wchart.util.HttpClientUtils;
import com.wchart.util.WcahrtMenu;
import com.wchart.util.Wchartcontext;


public class WchartApplicationTests {
	String token22 = null;
	@Test
	public void contextLoads()  {
		
		String token = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
				Constant.APPID+"&secret="+Constant.APP_SECTET);
		ObjectMapper mapper=null;
		AccessToken accessToken=null;
		AccessTokenError accessTokenError=null;		
		try {			
			mapper = new ObjectMapper();
			accessToken = mapper.readValue(token, AccessToken.class);
			Wchartcontext.accessToken = accessToken;
			Wchartcontext.accessTokenError = null;
			token22 = accessToken.getAccess_token();
			System.out.println(token22);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				accessTokenError = mapper.readValue(token, AccessTokenError.class);
				Wchartcontext.accessToken = null;
				Wchartcontext.accessTokenError = accessTokenError;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	@Test
	public void menu() throws IOException {
		List<WcahrtMenu> menu= new ArrayList<WcahrtMenu>();
		WcahrtMenu m1=new WcahrtMenu();
		m1.setId(1);
		m1.setName("一级菜单");
		m1.setType("view");
		m1.setUrl("https://www.hao123.com/");
		menu.add(m1);
		
		WcahrtMenu m2=new WcahrtMenu();
		m2.setId(2);
		m2.setName("菜单");
		List<WcahrtMenu> sub= new ArrayList<WcahrtMenu>();
		WcahrtMenu m21=new WcahrtMenu();
		m21.setId(1);
		m21.setName("点击事件");
		m21.setType("click");
		m21.setKey("dianji");
		m21.setUrl("https://www.hao123.com/");
		sub.add(m21);
		WcahrtMenu m22=new WcahrtMenu();
		m22.setId(2);
		m22.setName("视图");
		m22.setType("view");
		m22.setUrl("https://www.hao123.com/");
		sub.add(m22);
		
		m2.setSub_button(sub);
		
		menu.add(m2);
		
		
		Map<String,List<WcahrtMenu>> button =new HashMap<>();
		button.put("button", menu);
		
		ObjectMapper objectMapper = new ObjectMapper();
		// 过滤对象的null属性.
		objectMapper.setSerializationInclusion(Include.NON_NULL);  
		// 过滤map中的null值
		//objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		
		String jsonStr = objectMapper.writeValueAsString(button);
		System.out.println("-----json字符串------\n "+jsonStr);
		String token = "14_-fTyqJuWv-fsDDJRMVtlcuBeMEgSdridIwg-XzPQA5DTpVZTNQYEIYAQFPQk8k7QNRZydbDevFCYmknq82QCoOY9E5BNz-p-dBuiTPfn07mZBG0YtmU5VLFFmx7trwCMFO6uB4y7VqAv2Sf6ITQgAEAXIF";
		String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
		
		String msg = HttpClientUtils.postJson(url, jsonStr);
		AccessTokenError accessTokenError = new ObjectMapper().readValue(msg, AccessTokenError.class);
		System.out.println("=====>>>"+accessTokenError);
		
	
	}
	@Test
	public void test() {
		Map<String,String> map =new HashMap<>();
		map.put("ToUserName", "FromUserName");
		map.put("FromUserName",  "ToUserName");
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "text");
		map.put("Content", "返回的信息");
		System.out.println(MsgKit.map2xml(map));
	}
	//添加客服账号
	@Test
	public void addkefu() {
		String token = "14_-fTyqJuWv-fsDDJRMVtlcuBeMEgSdridIwg-XzPQA5DTpVZTNQYEIYAQFPQk8k7QNRZydbDevFCYmknq82QCoOY9E5BNz-p-dBuiTPfn07mZBG0YtmU5VLFFmx7trwCMFO6uB4y7VqAv2Sf6ITQgAEAXIF";
		String url ="https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+token;
		String jsonString = "{'kf_account' : 'test1@test','nickname' : '客服1','password' : 'pswmd5'}";
		System.out.println(jsonString);
		String msg = HttpClientUtils.postJson(url, jsonString);
		System.out.println(msg);
	}

}
