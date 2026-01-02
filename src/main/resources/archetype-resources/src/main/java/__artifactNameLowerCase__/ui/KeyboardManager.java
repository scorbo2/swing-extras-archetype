package ${package}.${artifactNameLowerCase}.ui;

import ${package}.${artifactNameLowerCase}.extensions.${artifactNamePascalCase}ExtensionManager;
import ${package}.${artifactNameLowerCase}.ui.actions.*;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Utility class for handling global application keyboard shortcuts.
 *
 * @author <a href="https://github.com/scorbo2">scorbo2</a>
 */
public class KeyboardManager {

    private KeyboardManager() {

    }

    /**
     * Sets up the key listener for the given Window.
     * If the given Window is not active when a keystroke happens, no action will be taken.
     *
     * @param window the given window must be active in order to receive key events.
     */
    public static void addGlobalKeyListener(final Window window) {
        //Hijack the keyboard manager
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                // Don't process if the window isn't active!
                if (!window.isActive()) {
                    return false;
                }

                // TODO add additional keyboard shortcuts here by following the pattern below:
                //      And note that extensions are also given a chance to handle key events!
                boolean wasHandled = false;
                if (e.getID() == KeyEvent.KEY_PRESSED) {

                    switch (e.getKeyCode()) {

                        // Ctrl+P for Preferences
                        case KeyEvent.VK_P:
                            if (e.isControlDown()) {
                                new PropertiesAction().actionPerformed(null);
                            }
                            wasHandled = true;
                            break;

                        // Ctrl+E for ExtensionManagerDialog
                        case KeyEvent.VK_E:
                            if (e.isControlDown()) {
                                new ExtensionManagerAction().actionPerformed(null);
                            }
                            wasHandled = true;
                            break;


                        // Ctrl+L to show the log console:
                        case KeyEvent.VK_L:
                            if (e.isControlDown()) {
                                new LogConsoleAction().actionPerformed(null);
                            }
                            wasHandled = true;
                            break;

                        // Ctrl+A for About
                        case KeyEvent.VK_A:
                            if (e.isControlDown()) {
                                new AboutAction().actionPerformed(null);
                            }
                            wasHandled = true;
                            break;

                        // Ctrl+Q for quit
                        case KeyEvent.VK_Q:
                            if (e.isControlDown()) {
                                new ExitAction().actionPerformed(null);
                            }
                            wasHandled = true; // pointless, as we're quitting anyway, but keep the pattern consistent
                            break;

                        default:
                            break;
                    }

                    // Give extensions a chance to handle this shortcut, if not yet handled:
                    if (!wasHandled) {
                        wasHandled = ${artifactNamePascalCase}ExtensionManager.getInstance().handleKeyEvent(e);
                    }
                }

                // Allow the event to be redispatched if wasn't handled above:
                return wasHandled;
            }
        });
    }
}
