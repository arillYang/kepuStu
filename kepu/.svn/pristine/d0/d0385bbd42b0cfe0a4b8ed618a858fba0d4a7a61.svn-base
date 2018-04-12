package com.kepu.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.dao.JedisClient;
import com.kepu.util.WeixinUtils;

@Controller
public class WeixinController {

	private static final Logger LOG = Logger.getLogger(WeixinController.class);
	@Autowired
	private JedisClient jedisClient;
	
	 @RequestMapping(value = "/wx-config", method = { RequestMethod.GET })
      public @ResponseBody Map<String, Object> getConfig(HttpServletRequest request,
              HttpServletResponse response, @RequestParam(value="url", required=true) String url) throws Exception {
          long timeStamp = new Date().getTime()/1000;
          Map<String, Object> result = WeixinUtils.getConfig(timeStamp, url,jedisClient);
          return result;
	  }
}
