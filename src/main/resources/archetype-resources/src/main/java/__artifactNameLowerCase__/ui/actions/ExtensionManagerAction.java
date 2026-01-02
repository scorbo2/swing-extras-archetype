package ${package}.${artifactNameLowerCase}.ui.actions;

import ${package}.${artifactNameLowerCase}.AppConfig;
import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}ExtensionManager;
import ${package}.${artifactNameLowerCase}.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * An action that, when invoked, will open the ExtensionManagerDialog.
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class ExtensionManagerAction extends AbstractAction {

    public ExtensionManagerAction() {
        super("Extension Manager...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (AppConfig.getInstance().showExtensionDialog(MainWindow.getInstance(),
                ${artifactNamePascalCase}ExtensionManager.getInstance().getUpdateManager())) {
            // Reload UI to reflect any changes in extensions:
            UIReloadAction.getInstance().actionPerformed(null);
        }
    }
}
