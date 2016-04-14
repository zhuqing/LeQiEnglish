//package com.leqienglish.util.log;
//
//import org.apache.log4j.Logger;
//
//public class LOGGER {
//	private static Logger logger;
//
//	private LOGGER() {
//
//	}
//
//	public static LOGGER instance(Class msg) {
//
//		logger = Logger.getLogger(msg);
//
//		return new LOGGER();
//	}
//
//	public void info(String message) {
//		logger.info(message);
//	}
//
//	public void info(String message, Throwable t) {
//		logger.info(message, t);
//	}
//
//	public void debug(String message) {
//		logger.debug(message);
//	}
//
//	public void debug(String message, Throwable t) {
//		logger.debug(message, t);
//	}
//
//	public void error(String message) {
//		logger.error(message);
//	}
//
//	public void error(String message, Throwable t) {
//		logger.error(message, t);
//	}
//}
