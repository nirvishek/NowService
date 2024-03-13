package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application", Locale.getDefault());

    public static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}