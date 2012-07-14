/*
 * Create on 2012-7-14
 */
package cn.hweicdl.projectimport.preference;

import org.eclipse.core.resources.IProjectDescription;

/**
 * @author Wei Huang
 */
public class Constants {
	public final static String PREF_PAGE_DESC = "Config for importing projects";
	
	public final static String PREF_PAGE_FROM_PATH = "PREF_PAGE_FROM_PATH"; //$NON-NLS-1$
	public final static String PREF_PAGE_FROM_PATH_DESC = "Import projects from: ";
	public final static String PREF_PAGE_WORKING_SET = "PREF_PAGE_WORKING_SET"; //$NON-NLS-1$
	public final static String PREF_PAGE_WORKING_SET_DESC = "Working set name: ";
	
//	public final static String PREF_PAGE_IGNORE_SET = "PREF_PAGE_WORKING_SET"; //$NON-NLS-1$
//	public final static String PREF_PAGE_WORKING_SET_DESC = "Working set name: ";
	
	public final static String DOT_PROJECT = IProjectDescription.DESCRIPTION_FILE_NAME;
	public final static String METADATA_FOLDER = ".metadata"; //$NON-NLS-1$
}
