package eips.message.filter;

import org.apache.camel.Header;

public class TypeChecker {

    public static boolean isWidget(@Header("type") String type) {
        return type.equalsIgnoreCase("widget");
    }

}
