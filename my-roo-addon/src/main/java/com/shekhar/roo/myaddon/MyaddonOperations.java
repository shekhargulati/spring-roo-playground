package com.shekhar.roo.myaddon;

import org.springframework.roo.model.JavaType;

/**
 * Interface of commands that are available via the Roo shell.
 *
 * @since 1.1
 */
public interface MyaddonOperations {

	boolean isCommandAvailable();

	void annotateType(JavaType type);
	
	void setup();
}