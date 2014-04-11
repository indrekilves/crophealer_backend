package com.crophealer.rest.v1.service;

import java.io.File;

public class FileManagementRestService {

	
	public static String getUserPictureDirectoryByUsername(String userId) {
		String dir = getUserPicturesRootPath() + userId + "/";
		createDirIfNeeded(dir);
		return dir;
	}
	

	

	public static String getUserPicturesRootPath(){
		return "/var/userpictures/";		
	}
	

	
	
	private static void createDirIfNeeded(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created." + dir);
			} else {
				System.out.println("Failed to create directory" + dir);
			}
		}
	}

}
