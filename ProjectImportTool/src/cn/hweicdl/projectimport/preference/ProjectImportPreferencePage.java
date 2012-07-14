/*
 * Create on 2012-7-11
 */
package cn.hweicdl.projectimport.preference;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cn.hweicdl.projectimport.ProjectImportActivator;

/**
 * @author Wei Huang
 */
public class ProjectImportPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ProjectImportPreferencePage() {
		super(GRID);
		setPreferenceStore(ProjectImportActivator.getDefault().getPreferenceStore());
		setDescription(Constants.PREF_PAGE_DESC);
	}
	
	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor(Constants.PREF_PAGE_FROM_PATH, 
				Constants.PREF_PAGE_FROM_PATH_DESC, getFieldEditorParent()));
		addField(new StringFieldEditor(Constants.PREF_PAGE_WORKING_SET, 
				Constants.PREF_PAGE_WORKING_SET_DESC, getFieldEditorParent()));
		
		Composite pageParent = getFieldEditorParent();
		ProjectImportPreferenceIgnore ignore = new ProjectImportPreferenceIgnore();
		ignore.createContents(pageParent);
	}
	
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
	}
}
