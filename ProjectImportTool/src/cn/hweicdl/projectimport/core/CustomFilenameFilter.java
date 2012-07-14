/*
 * Created on 2012-7-15
 */
package cn.hweicdl.projectimport.core;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Wei Huang
 */
public class CustomFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File f, String fileName) {
		if (fileName.endsWith("nl1") || fileName.endsWith("nl2") || f.getParent().endsWith(".feature"))
			return false;
		return true;
	}

}

