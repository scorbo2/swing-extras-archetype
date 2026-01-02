package ${package}.${artifactNameLowerCase};

import ca.corbett.extensions.AppProperties;
import ca.corbett.extras.properties.AbstractProperty;
import ca.corbett.extras.properties.BooleanProperty;
import ca.corbett.extras.properties.LookAndFeelProperty;
import com.formdev.flatlaf.FlatLightLaf;

import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}Extension;
import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}ExtensionManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages application configuration for your application, and provides a convenient
 * way to launch both the application properties dialog and also the extension manager dialog.
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class AppConfig extends AppProperties<${artifactNamePascalCase}Extension> {

    /**
     * For thread-safe lazy-loaded singleton pattern.
     */
    private static class SingletonHolder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    private static final String PROPS_FILE_NAME = "${artifactNamePascalCase}.props";
    public static final File PROPS_FILE = new File(Version.SETTINGS_DIR, PROPS_FILE_NAME);

    private LookAndFeelProperty lookAndFeelProp;
    private BooleanProperty enableSingleInstance;

    private AppConfig() {
        super(Version.FULL_NAME, PROPS_FILE, ${artifactNamePascalCase}ExtensionManager.getInstance());
    }

    public static AppConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Get the class name of the currently selected Look and Feel.
     */
    public String getLookAndFeelClassName() {
        return lookAndFeelProp.getSelectedLafClass();
    }

    public boolean isSingleInstanceEnabled() {
        return enableSingleInstance.getValue();
    }

    /**
     * We'll add a convenience wrapper around the static peek() method so that
     * our callers don't have to specify our props file each time.
     */
    public static String peek(String propName) {
        return AppProperties.peek(PROPS_FILE, propName);
    }

    /**
     * This is where you can define the configuration properties for your application.
     * These properties will be displayed in the PropertiesDialog, and persisted
     * to the properties file automatically.
     */
    @Override
    protected List<AbstractProperty> createInternalProperties() {
        List<AbstractProperty> props = new ArrayList<>();

        // We'll create a Look and Feel property to allow users to select
        // their preferred Look and Feel for the application:
        lookAndFeelProp = new LookAndFeelProperty("UI.General.lookAndFeel",
                "Look and Feel:",
                FlatLightLaf.class.getName());
        props.add(lookAndFeelProp);

        // We'll create a property to allow enabling/disabling single-instance mode:
        enableSingleInstance = new BooleanProperty("UI.General.singleInstance",
                "Allow only a single instance of the application",
                true);
        props.add(enableSingleInstance);

        // TODO add the rest of your application configuration here.

        return props;
    }
}
