package org.opentdk.gui.controls;

import java.io.File;
import java.util.List;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Class to open the explorer/finder or similar in different modes (file
 * chooser, directory chooser, save dialog).
 * 
 * Call like this:
 * 
 * <pre>
 * List{@literal <File>} files = openFileChooserDialog(TITLE, ExtensionType.FILE, LATESTAPTH); 
 * </pre>
 * 
 * The latest path can stored in a properties file or set to null to open the
 * default path (user home)
 * 
 * @author LK Test Solutions
 *
 */
public final class ChooserDialog {
	private ChooserDialog() {

	}

	/**
	 * A file can be opened in the application or in another program
	 */
	public enum FileOpeningType {
		INTERN, EXTERN;
	}

	/**
	 * Set extension filter in the chooser dialog: FILE: TXT, JAVA, CSV, XML etc.
	 * APP: DMG, APP, EXE, RPG etc.
	 */
	public enum ExtensionType {
		FILE, APP;
	}

	/**
	 * Open the OS depending explorer to open or save files.
	 * 
	 * @param title      the title of the chooser dialog
	 * @param ext        the extension of the files that should be displayed by
	 *                   default.
	 * @param latestPath if the latest path string was saved somewhere, it can be
	 *                   used to open the chooser at this location again.
	 * @return the chosen file as <code>File</code> instance.
	 */
	public static List<File> openFileChooserDialog(final String title, final ExtensionType ext, final String latestPath) {
		final FileChooser fileChooser = new FileChooser(); /* Initialize the file chooser object */

		String load = null;
		if (latestPath == null || latestPath.isEmpty()) {
			load = System.getProperty("user.home");
		} else {
			load = latestPath;
		}
		File file = new File(load);

		if (ext == ExtensionType.FILE) {
			configureFileChooser(fileChooser, file, file.getName(), ExtensionType.FILE, title);
		} else if (ext == ExtensionType.APP) {
			configureFileChooser(fileChooser, file, file.getName(), ExtensionType.APP, title);
		}
		return fileChooser.showOpenMultipleDialog(null);
	}

	public static File openSaveDialog(final String title, final ExtensionType ext, final String latestPath) {
		final FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle(title);

		String load = null;
		if (latestPath == null || latestPath.isEmpty()) {
			load = System.getProperty("user.home");
		} else {
			load = latestPath;
		}
		File file = new File(load);

		if (ext == ExtensionType.FILE) {
			configureFileChooser(fileChooser, file, file.getName(), ExtensionType.FILE, title);
		} else if (ext == ExtensionType.APP) {
			configureFileChooser(fileChooser, file, file.getName(), ExtensionType.APP, title);
		}
		return fileChooser.showSaveDialog(null);
	}

	private static void configureFileChooser(FileChooser fileChooser, File latest, String name, ExtensionType ext, String title) {
		fileChooser.setTitle(title);
		fileChooser.setInitialFileName(name);

		if (ext == ExtensionType.FILE) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"), new FileChooser.ExtensionFilter("java", "*.java"), new FileChooser.ExtensionFilter("txt", "*.txt"), new FileChooser.ExtensionFilter("csv", "*.csv"), new FileChooser.ExtensionFilter("xml", "*.xml"));
		} else if (ext == ExtensionType.APP) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("exe", "*.exe"), new FileChooser.ExtensionFilter("dmg", "*.dmg"), new FileChooser.ExtensionFilter("app", "*.app"), new FileChooser.ExtensionFilter("rpm", "*.rpm"));
		}

	}

	public static File openDirectoryChooser(String title, String latest) {
		final DirectoryChooser dirChooser = new DirectoryChooser(); 
		String load;
		if (latest == null || latest.isEmpty() || !new File(latest).exists())
			load = System.getProperty("user.home");
		else
			load = latest;

		configureDirectoryChooser(dirChooser, load, title);

		return dirChooser.showDialog(null);
	}

	private static void configureDirectoryChooser(DirectoryChooser directoryChooser, String latest, String title) {
		directoryChooser.setTitle(title); 
		directoryChooser.setInitialDirectory(new File(latest));
	}
}
