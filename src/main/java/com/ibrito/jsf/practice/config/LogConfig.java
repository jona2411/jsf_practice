package com.ibrito.jsf.practice.config;

import javax.annotation.PostConstruct;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.springframework.stereotype.Component;

@Component
public class LogConfig {

	@PostConstruct
	public void init() {

		String logName = "sistema";

		ConsoleAppender console = new ConsoleAppender();
		String PATTERN = "%d [%t] %-5p (%F:%L) - %m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.INFO); // disable log to console (catalina.out)
		console.activateOptions();
		Logger.getRootLogger().addAppender(console);

		RollingFileAppender rfa = new RollingFileAppender(); // set log to another file
		rfa.setName(logName);
		rfa.setImmediateFlush(true);
		rfa.setFile(System.getProperty("catalina.base") + "/logs/" + logName + ".log");

		rfa.setLayout(new PatternLayout("%d [%t] %-5p (%F:%L) - %m%n"));
		rfa.setThreshold(Level.INFO);
		rfa.setAppend(true);
		rfa.setMaxFileSize("100MB");
		rfa.setMaxBackupIndex(50);

		rfa.activateOptions();
		Logger.getRootLogger().addAppender(rfa);
		// at this point you can log with:
		Logger log = Logger.getRootLogger();
	}
}
