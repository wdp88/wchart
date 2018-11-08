package com.wchart.util;

import org.springframework.stereotype.Component;

@Component
public abstract class Wchartcontext {
	public  static AccessToken accessToken;
	public static AccessTokenError accessTokenError;
}
