package com.wchart.util;

public class AccessTokenError {
	String errcode;
	String errmsg;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "AccessTokenError [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
	
}
