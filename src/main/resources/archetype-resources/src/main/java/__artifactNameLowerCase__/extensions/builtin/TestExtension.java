package ${package}.${artifactNameLowerCase}.extensions.builtin;

import ca.corbett.extensions.AppExtensionInfo;
import ca.corbett.extras.properties.AbstractProperty;
import ca.corbett.extras.properties.BooleanProperty;
import ca.corbett.extras.properties.LabelProperty;
import ${package}.${artifactNameLowerCase}.Version;
import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}Extension;
import ${package}.${artifactNameLowerCase}.ui.MainWindow;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This is an example of a built-in extension for your application.
 * You can supply built-in extensions by supplying them directly
 * to your application's ExtensionManager implementation.
 * These don't need to be packaged into separate jar files and loaded
 * dynamically.
 * <p>
 * Extensions are typically built and distributed as jar files that can
 * be dynamically loaded by the application at runtime. Refer to the
 * <a href="https://www.corbett.ca/swing-extras-book/">swing-extras documentation</a>
 * for more information on building and distributing extensions.
 * </p>
 * <p>
 * This minimal TestExtension is just to give you an idea of what's
 * possible with the application extension framework!
 * </p>
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class TestExtension extends ${artifactNamePascalCase}Extension {
    private static final Logger logger = Logger.getLogger(TestExtension.class.getName());
    private final AppExtensionInfo extInfo;

    public TestExtension() {
        // All extensions must supply a well-formed AppExtensionInfo object:
        extInfo = new AppExtensionInfo.Builder("TestExtension")
                .setVersion("1.0.0")
                .setAuthor("scorbo2")
                .setShortDescription("A test extension for demonstration purposes.")
                .setLongDescription(
                        "This is a test extension for demonstration purposes only. It doesn't actually do anything useful.")
                .setTargetAppName(Version.NAME)
                .setTargetAppVersion(Version.VERSION)
                .build();
    }

    @Override
    public AppExtensionInfo getInfo() {
        return extInfo;
    }

    @Override
    public void onActivate() {
        // This method is invoked when the application starts up
        // or when the extension is enabled.
        logger.info("TestExtension has been activated!");
    }

    @Override
    public void onDeactivate() {
        // This method is invoked when the application is shutting down
        // or when the extension is disabled.
        logger.info("TestExtension has been deactivated!");
    }

    @Override
    protected List<AbstractProperty> createConfigProperties() {
        // We'll return some example config properties.
        // These will automatically be included in the application properties dialog
        // when this extension is enabled, and will automatically be hidden in the
        // properties dialog when the extension is disabled.
        List<AbstractProperty> props = new ArrayList<>();
        props.add(new LabelProperty("TestExtension.General.label1",
                                    "These properties were supplied by TestExtension."));
        props.add(new LabelProperty("TestExtension.General.label2",
                                    "They don't actually do anything."));
        props.add(new BooleanProperty("TestExtension.General.field1", "Option 1", true));
        props.add(new BooleanProperty("TestExtension.General.field2", "Option 2", true));
        props.add(new BooleanProperty("TestExtension.General.field3", "Option 3", true));
        props.add(new LabelProperty("TestExtension.General.label3",
                                    "If you disable TestExtension, these should vanish."));
        return props;
    }

    @Override
    protected void loadJarResources() {
        // Nothing to load here. If this were a typical, jar-loaded extension,
        // then we could load icon images or other resources from our jar file.
    }

    @Override
    public boolean handleKeyEvent(KeyEvent keyEvent) {
        // We'll respond to Ctrl+T as a demo:
        if (keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_T) {
            JOptionPane.showMessageDialog(MainWindow.getInstance(),
                                          "TestExtension received the Ctrl+T keyboard shortcut!\n" +
                                                  "This keyboard shortcut will only work when the extension is enabled.");
            return true;
        }
        return false;
    }

    @Override
    public List<JMenu> getTopLevelMenus() {
        // We'll add a simple "Test" menu with one item:
        JMenu testMenu = new JMenu("Test");
        JMenuItem aboutItem = new JMenuItem(new DummyAction("About TestExtension",
                                                            "This is a test extension for demonstration purposes."));
        testMenu.add(aboutItem);
        return List.of(testMenu);
    }

    @Override
    public List<JMenuItem> getMenuItems(String topLevelMenu) {
        List<JMenuItem> items = new ArrayList<>();

        // We'll return a dummy option for the "File" menu:
        if ("File".equals(topLevelMenu)) {
            items.add(new JMenuItem(new DummyAction("TestExtension Info",
                                                    "This is a test extension for demonstration purposes.")));
        }

        // And a dummy option for the "Edit" menu:
        if ("Edit".equals(topLevelMenu)) {
            items.add(new JMenuItem(new DummyAction("TestExtension Settings",
                                                    "This is a test extension for demonstration purposes.")));
        }

        // And a dummy option for the "Help" menu:
        if ("Help".equals(topLevelMenu)) {
            items.add(new JMenuItem(new DummyAction("TestExtension Help",
                                                    "This is a test extension for demonstration purposes.")));
        }

        return items;
    }

    /**
     * Just a quick example action to show a dialog when the action is executed.
     */
    private static class DummyAction extends AbstractAction {

        private final String msg;

        public DummyAction(String name, String msg) {
            super(name);
            this.msg = msg;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Just show our message:
            JOptionPane.showMessageDialog(MainWindow.getInstance(), msg);
        }
    }
}
