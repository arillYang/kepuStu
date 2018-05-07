package com.kepu.service;

import org.springframework.web.multipart.MultipartFile;

import com.kepu.pojo.PictrueResult;




public interface PictureService {
	public PictrueResult  uploadPicture(MultipartFile uploadFile);
	
}
