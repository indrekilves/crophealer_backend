package com.crophealer.data;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;



public class DataLoaderLogger {
	static private FileHandler fileTxt;
	
	static public void setup() throws IOException {

		// Get the global logger to configure it
	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	    //logger.setLevel(Level.INFO);
	    //fileTxt = new FileHandler("dataLoader.log");
	    //logger.addHandler(fileTxt);
	  }
}