package com.wchart.msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wchart.util.Constant;


public class MsgKit {
	public static Map<String,String> reqMsg2Map(HttpServletRequest req) {
		try {
			String xml = req2xml(req);
			Map<String,String> maps=new HashMap<>();
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			
			List<Element> eles = root.elements();
			for(Element e:eles) {
				maps.put(e.getName(), e.getTextTrim());
			}
			return maps;
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		return null;	
	}
	
	private static String req2xml(HttpServletRequest req)  {
		try {
			BufferedReader br=null;
			br= new BufferedReader(new InputStreamReader(req.getInputStream()) );
			String str = null ; 
			StringBuffer sb =new StringBuffer();
			while((str=br.readLine())!=null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String handlerMsg(Map<String, String> msg) {
		String msgType = msg.get("MsgType");
		if(msgType.equals(Constant.MSG_TEXT_TYPE)) {
			return textTypeHandler(msg,"这是文字");
		}else if(msgType.equals(Constant.MSG_IMAGE_TYPE)) {
			return textTypeHandler(msg,"这是图片");
		}else if(msgType.equals(Constant.MSG_VOICE_TYPE)) {
			return textTypeHandler(msg,"这是音频");
		}else if(msgType.equals(Constant.MSG_VIDEO_TYPE)) {
			return textTypeHandler(msg,"这是视频");
		}else if(msgType.equals(Constant.MSG_SHORTVIDEO_TYPE)) {
			return textTypeHandler(msg,"这是短视频");
		}else if(msgType.equals(Constant.MSG_LOCATION_TYPE)){
			return textTypeHandler(msg,"这是定位");
		}else if(msgType.equals(Constant.MSG_EVENT_TYPE)){
			return textTypeHandler(msg,"这是事件");
		}else {
			
		}
		return null;	
	}
	private static String textTypeHandler(Map<String,String> msg,String returnMsg) {
		Map<String,String> map =new HashMap<>();
		map.put("ToUserName", msg.get("FromUserName"));
		map.put("FromUserName",  msg.get("ToUserName"));
		map.put("CreateTime", new Date().getTime()+"");
		map.put("MsgType", "text");
		map.put("Content", returnMsg);
		return map2xml(map);
		
	}

	public static String map2xml(Map<String, String> map) {
		Document d=DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key: keys) {
			root.addElement(key).addText(map.get(key));
		}
		StringWriter sw =new StringWriter();
		try {
			d.write(sw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
}
