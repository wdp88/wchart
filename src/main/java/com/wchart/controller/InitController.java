package com.wchart.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wchart.msg.MsgKit;
import com.wchart.util.Constant;
import com.wchart.util.SHA1;

@RestController
public class InitController {
	@GetMapping(value="/init") 
	public void init(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		String[] arry = {Constant.TOKEN,timestamp,nonce};
		Arrays.sort(arry);
		StringBuffer arry1= new StringBuffer();
		for(String m:arry) arry1.append(m);

		String sha1 = SHA1.encode(arry1.toString());
		System.out.println("=====>>> "+sha1);
		if(sha1.equals(signature)) {
			resp.getWriter().println(echostr);
		}
	}
	@PostMapping(value="/init")
	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		Map<String,String> msg = MsgKit.reqMsg2Map(req);
//		System.out.println("----msg--------\n"+msg);
//		
//		String response = MsgKit.handlerMsg(msg);
//		resp.setContentType("application/xml;charset=UTF-8");
//		resp.getWriter().write(response);
		
		BufferedReader br=null;
		br= new BufferedReader(new InputStreamReader(req.getInputStream()) );
		String str = null ; 
		StringBuffer sb =new StringBuffer();
		while((str=br.readLine())!=null) {
			System.out.println(str);
		}
	
	}
}
