package com.winnovate.didpatients.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.ProfileImageDao;
import com.winnovate.didpatients.domain.ProfileImage;

@Service
public class ProfileImageService {
	
	@Autowired
	ProfileImageDao profileImageDao;

	public List<ProfileImage> getProfileImages(){
		return profileImageDao.findAll();
	}
}
