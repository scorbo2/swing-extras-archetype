package ${package}.${artifactNameLowerCase}.ui.actions;

import ca.corbett.extras.about.AboutDialog;
import ${package}.${artifactNameLowerCase}.Version;
import ${package}.${artifactNameLowerCase}.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to show the About dialog.
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class AboutAction extends AbstractAction {

    public AboutAction() {
        super("About");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AboutDialog(MainWindow.getInstance(), Version.getAboutInfo()).setVisible(true);
    }
}
