package ${package}.${artifactNameLowerCase}.extensions;

import ca.corbett.extensions.AppExtension;

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.List;

/**
 * This is the starting point for extensions for your application.
 * You can add application-specific hooks here as needed.
 * Some example hooks have been provided to give you an idea as to what's possible:
 * <ul>
 *     <li>handleKeyEvent() - Extensions can register to receive and handle keyboard shortcuts.</li>
 *     <li>getTopLevelMenus() - Extensions can add their own top-level menus to the main menu.</li>
 *     <li>getMenuItems() - Extensions can add menu items to existing top-level menus.</li>
 * </ul>
 * <p>
 *     Whatever functionality your application offers, try to think of how that functionality could be
 *     augmented or even replaced by an extension. Then add appropriate hooks here to allow extensions
 *     to interact with your application in those ways!
 * </p>
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public abstract class ${artifactNamePascalCase}Extension extends AppExtension {

    /**
     * Invoked when the application receives a keyboard shortcut. The extension
     * can choose to do something with it or not. Processing the keyboard event
     * does not stop when an extension acts on a shortcut, so in theory multiple
     * extensions could respond to the same keyboard shortcut.
     *
     * @param keyEvent the KeyEvent that triggered this message.
     * @return true if this extension handled the shortcut (default false).
     */
    public boolean handleKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    /**
     * Invoked when the application wants to know if the extension has its own top-level
     * menu to add to the MainWindow's main menu. These will be inserted in between
     * the "Edit" and "Help" menus.
     *
     * @return an optional list of JMenu objects for the main menu, or null for none.
     */
    public List<JMenu> getTopLevelMenus() {
        return null;
    }

    /**
     * Invoked when the application is building the MainWindow's main menu and wants
     * to know if the extension has anything to add to one of the built-in top-level
     * menus.
     *
     * @param topLevelMenu The name of the top-level menu being built: File, Edit, or Help.
     * @return an optional list of menu items to insert into the given menu, or null for nothing.
     */
    public List<JMenuItem> getMenuItems(String topLevelMenu) {
        return null;
    }

}
