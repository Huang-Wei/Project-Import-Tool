package cn.hweicdl.projectimport.preference;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cn.hweicdl.projectimport.ProjectImportActivator;

public class ProjectImportPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ProjectImportPreferencePage() {
		super(GRID);
		setPreferenceStore(ProjectImportActivator.getDefault().getPreferenceStore());
		setDescription("Config for importing projects");
	}
	
	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor("FromPath", "Import projects from: ", getFieldEditorParent()));
		addField(new StringFieldEditor("WorkingSetName", "Working set name: ", getFieldEditorParent()));
	}
	
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
	}
}
