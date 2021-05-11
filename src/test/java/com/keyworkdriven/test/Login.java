package com.keyworkdriven.test;

import org.testng.annotations.Test;

import com.keyword.engine.KeywordEngine;

public class Login {
	
	public KeywordEngine key;
	
	@Test
	public void login()
	{
		key = new KeywordEngine();
		key.startExecution("loginData");
	}

}
