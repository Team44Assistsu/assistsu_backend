package com.winnovate.didpatients.model;

import java.io.InputStream;
import java.util.List;

public class ImageResponse {

	int key;
	
	List<InputStream> images;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	
}
