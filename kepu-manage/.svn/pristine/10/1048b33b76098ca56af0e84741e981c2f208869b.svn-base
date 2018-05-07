package com.kepu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kepu.controller.NewsController;
import com.kepu.pojo.PictrueResult;
import com.kepu.service.PictureService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.FtpUtil;
import com.kepu.util.OSSUploadUtil;
import com.kepu.util.UUIDFactory;


@Service
public class PictureServiceImpl implements PictureService {
	protected final Log logger = LogFactory.getLog(PictureServiceImpl.class);

	@Override
	public PictrueResult uploadPicture(MultipartFile uploadFile){
		if(uploadFile==null||uploadFile.isEmpty()){
			return PictrueResult.error("上传图片为空");
		}
		String oldName=uploadFile.getOriginalFilename();
		String newName=UUIDFactory.getUUID();
		newName+="."+oldName.split("\\.")[1];
		try {
			logger.info("OSS开始上传");
			OSSUploadUtil.putObject("newsImages/"+newName, uploadFile.getInputStream());
			logger.info("OSS上传结束");
			//FtpUtil.uploadFile(ftp_address, 21, ftp_userName, ftp_password, filePath, uploadFile.getInputStream(),newName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(ExceptionUtil.getStackTrace(e));
			return PictrueResult.error(e.getMessage());
		}
		return PictrueResult.ok("http://ikow.oss-cn-shanghai.aliyuncs.com"+"/newsImages/"+newName);
	}

}
