package ${package}.${artifactNameLowerCase}.ui;

import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}ExtensionManager;
import ${package}.${artifactNameLowerCase}.ui.actions.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * A helper class that can build up menus for a window menu bar, and also
 * rebuild them dynamically if any changes occur that would affect them.
 * <p>
 * <b>Extensions can supply menu items!</b> - extensions will be queried for top-level menus,
 * if they supply any, and extensions can also optionally supply extra menu items for the File,
 * Edit, and Help menus.
 * </p>
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class MenuManager {

    private final JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    /**
     * Creates and populates a new MenuManager.
     */
    public MenuManager() {
        menuBar = new JMenuBar();
        rebuildAll();
    }

    /**
     * Returns the main JMenuBar for the application.
     */
    public JMenuBar getMainMenuBar() {
        return menuBar;
    }

    /**
     * Can be invoked to rebuild all menus from scratch.
     */
    public void rebuildAll() {
        rebuildMenuBar();
        rebuildFileMenu();
        rebuildEditMenu();
        rebuildHelpMenu();
        // TODO you can add more top-level menus here if needed.
    }

    private void rebuildMenuBar() {
        menuBar.removeAll();

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);

        // Any extension-provided top-level menu can go in between Edit and Help:
        List<JMenu> extensionMenus = ${artifactNamePascalCase}ExtensionManager.getInstance().getTopLevelMenus();
        if (!extensionMenus.isEmpty()) {
            for (JMenu extensionMenu : extensionMenus) {
                menuBar.add(extensionMenu);
            }
        }

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
    }

    private void rebuildFileMenu() {
        fileMenu.removeAll();

        // TODO add your "File" menu items here.

        // Add any items to this list from our extensions, if any:
        List<JMenuItem> items = ${artifactNamePascalCase}ExtensionManager.getInstance().getMenuItems("File");
        if (!items.isEmpty()) {
            for (JMenuItem item : items) {
                fileMenu.add(item);
            }
            fileMenu.addSeparator();
        }

        JMenuItem exitItem = new JMenuItem(new ExitAction());
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        fileMenu.add(exitItem);
    }

    private void rebuildEditMenu() {
        editMenu.removeAll();

        // TODO add your "Edit" menu items here.

        // Add any items to this list from our extensions, if any:
        List<JMenuItem> extensionItems = ${artifactNamePascalCase}ExtensionManager.getInstance().getMenuItems("Edit");
        if (!extensionItems.isEmpty()) {
            for (JMenuItem extensionItem : extensionItems) {
                editMenu.add(extensionItem);
            }
            editMenu.addSeparator();
        }

        editMenu.add(new JMenuItem(new PropertiesAction()));
        editMenu.add(new JMenuItem(new ExtensionManagerAction()));
    }

    private void rebuildHelpMenu() {
        helpMenu.removeAll();

        // TODO add your "Help" menu items here.

        // Add any items to this list from our extensions, if any:
        List<JMenuItem> items = ${artifactNamePascalCase}ExtensionManager.getInstance().getMenuItems("Help");
        if (!items.isEmpty()) {
            for (JMenuItem extensionItem : items) {
                helpMenu.add(extensionItem);
            }
            helpMenu.addSeparator();
        }

        JMenuItem logConsoleItem = new JMenuItem(new LogConsoleAction());
        logConsoleItem.setMnemonic(KeyEvent.VK_L);
        logConsoleItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        helpMenu.add(logConsoleItem);

        JMenuItem aboutItem = new JMenuItem(new AboutAction());
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        helpMenu.add(aboutItem);
    }

}
