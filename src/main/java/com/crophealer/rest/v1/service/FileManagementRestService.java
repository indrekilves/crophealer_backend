package com.crophealer.rest.v1.service;

import java.io.File;

public class FileManagementRestService {

	
	public static String getUserPictureDirectoryByUsername(String username) {
		String dir = getUserPicturesRootDirectory() + username + "/";
		createDirIfNeeded(dir);
		return dir;
	}
	

	
	
	private static String getUserPicturesRootDirectory() {
		String dir = getUserPicturesRootPath();
		createDirIfNeeded(dir);
		return dir;
	}




	public static String getUserPicturesRootPath(){
		return "/userpictures/";
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
