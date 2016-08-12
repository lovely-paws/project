package edu.johnshopkins.lovelypaws;

import edu.johnshopkins.lovelypaws.beans.AddressInfo;
import org.apache.commons.lang3.StringUtils;

public class UserInputUtils {
    // =================================================================================================================
    // Sanitization Methods
    // =================================================================================================================

    public static String trimAndUpper(String name) {
        return StringUtils.upperCase(StringUtils.trimToNull(name));
    }

    public static void trimAndUpper(AddressInfo addressInfo) {
        if(addressInfo != null) {
            addressInfo.setLine1(trimAndUpper(addressInfo.getLine1()));
            addressInfo.setLine2(trimAndUpper(addressInfo.getLine2()));
            addressInfo.setCity(trimAndUpper(addressInfo.getCity()));
            addressInfo.setState(trimAndUpper(addressInfo.getState()));
            addressInfo.setZip(trimAndUpper(addressInfo.getZip()));
        }
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        int length = StringUtils.trimToEmpty(emailAddress).length();
        return 0 < length && length <= 255;
    }

    public static boolean isValidName(String name) {
        int length = StringUtils.trimToEmpty(name).length();
        return 0 < length && length <= 128;
    }
}
