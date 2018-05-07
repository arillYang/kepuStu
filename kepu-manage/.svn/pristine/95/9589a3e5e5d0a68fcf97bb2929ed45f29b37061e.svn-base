package com.kepu.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kepu.service.NewService;
import com.kepu.service.UserService;


@Component  
public class TaskController {
	
	
	private static final Logger LOG = Logger.getLogger(TaskController.class);
	
	@Autowired
	private NewService newService;
	@Autowired
	private UserService userService;
	/**
	 * 每小时检查一次定时发布任务
	 */
	@Scheduled(cron = "0 0 * * * ?")
	//@Scheduled(cron = "0/6 * *  * * ?") //每5秒执行
	public void publish(){
		try {
			LOG.info("发布任务定时任务开始");
			newService.publishTask();
			LOG.info("发布任务定时任务完成");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("定时任务失败"+e.getMessage());
		}
	}

	
	@Scheduled(cron = "0 5 0 ? * MON")
	//@Scheduled(cron = "0 */1 * * * ?")
	public void readStatistics(){
		try {
			userService.readStatistics();
			LOG.info("阅读统计生成完毕----------->");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("定时任务失败"+e.getMessage());
		}
	}
}
