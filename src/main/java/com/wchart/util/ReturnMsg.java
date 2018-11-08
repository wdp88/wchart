package com.wchart.util;

public  class ReturnMsg {
	private static ReturnMsg returnMsg=null;
	
	private static String access_token=null;
	private static String expires_in=null;
	private static String errcode=null;
	private static String errmsg=null;
	private ReturnMsg() {}
	
	public static ReturnMsg getReturnMsg() {
		if(returnMsg!=null) return returnMsg;
		returnMsg = new ReturnMsg();	
		return returnMsg;	
	}
	
	
	public static String getAccess_token() {
		return access_token;
	}
	public static void setAccess_token(String access_token) {
		ReturnMsg.access_token = access_token;
	}
	public static String getExpires_in() {
		return expires_in;
	}
	public static void setExpires_in(String expires_in) {
		ReturnMsg.expires_in = expires_in;
	}
	public static String getErrcode() {
		return errcode;
	}
	public static void setErrcode(String errcode) {
		ReturnMsg.errcode = errcode;
	}
	public static String getErrmsg() {
		return errmsg;
	}
	public static void setErrmsg(String errmsg) {
		ReturnMsg.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "ReturnMsg [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
