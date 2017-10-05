package com.oxygenxml.resources.batch.converter.reporter;
/**
 * Used by checker for report statuses. 
 * @author intern4
 */

public interface StatusReporter {
	
	/**
	 * Report a status
	 * @param message the status
	 */
	public void reportStatus(String message);

}