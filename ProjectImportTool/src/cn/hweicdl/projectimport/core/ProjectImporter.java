package cn.hweicdl.projectimport.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;

public class ProjectImporter {

	private boolean overrideProject;
	
	private String[] folders;
	
	private boolean ignoreFeatureProject;
	private boolean ignoreNL1NL2;
	
	private IWorkbenchPage activePage;
	
	final String dotProject = IProjectDescription.DESCRIPTION_FILE_NAME;
	final String METADATA_FOLDER = ".metadata";
	
	public ProjectImporter(IWorkbenchPage activePage) {
		overrideProject = true;
		folders = new String[]{"E:/temp/"};
		this.activePage = activePage;
	}
	
	public String load() {
		int totalSuccessProject = 0;
		int totalFailProject = 0;
		List<String> invalidFolders = new ArrayList<String>();
		List<File> projectFiles = new ArrayList<File>();
		
		for (String folder : folders) {
			File f = new File(folder);
			
			List<IAdaptable> resources = new ArrayList<IAdaptable>();
			if (f.exists() && f.isDirectory()) {
				getAllProjects(f, projectFiles);
			}
			else {
				invalidFolders.add(folder);
			}
			
			for (File projectFile : projectFiles) {
				try {
					IProjectDescription desc = ResourcesPlugin.getWorkspace().loadProjectDescription(
						new Path(projectFile.getCanonicalPath()));
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(desc.getName());
					
					if (project.exists() && overrideProject)
						project.delete(false, true, null);
					
					if (!project.exists()) {
						project.create(desc, null);
						project.getName();
						project.open(null);
						resources.add((IAdaptable) project);
						totalSuccessProject++;
					}
				} catch (CoreException e) {
					totalFailProject++;
					/*e.printStackTrace();*/
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			createWorkingSet(folder, resources.toArray(new IAdaptable[resources.size()]));
		}
		return totalSuccessProject + " projects imported successfully." + "\r\n" +
			totalFailProject + " projects imported failed.";
	}
	
	private void createWorkingSet(String wsName, IAdaptable[] resources) {
		IWorkingSetManager workingSetManager = PlatformUI.getWorkbench().getWorkingSetManager();
		
		IWorkingSet ws = workingSetManager.createWorkingSet(/*"Java Element"*/wsName, resources);
		ws.setId("org.eclipse.jdt.ui.JavaWorkingSetPage");
		
		workingSetManager.addWorkingSet(ws);
	}
	
	private List<IResource> getAllProjects(IWorkingSet ws) {
		List<IResource> resources = new ArrayList<IResource>(); // or LinkedList
		for (IAdaptable adaptable : ws.getElements()) {
			IResource r = (IResource) adaptable.getAdapter(IResource.class);
			if (r != null)
				resources.add(r);
		}
		return resources;
	}
	
	public void getAllProjects(File dir, List<File> files) {
		File[] folders = dir.listFiles();
		
		if (folders == null)
			;
		
		for (int i = 0; i < folders.length; i++) {
			File file = folders[i];
			if (file.isFile() && file.getName().equals(dotProject)) {
				files.add(file);
				// don't search sub-directories since we can't have nested
				// projects
			}
		}
		
		// no project description found, so recurse into sub-directories
		for (int i = 0; i < folders.length; i++) {
			if (folders[i].isDirectory()) {
				if (!folders[i].getName().equals(METADATA_FOLDER)) {
					getAllProjects(folders[i], files);
				}
			}
		}
	}
}
