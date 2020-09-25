package bus.reservation.system.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String generateCode() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String fullalphabet = alphabet + "123456789-_";
        Random random = new Random();

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(Constants.AGENCY_CODE_SIZE);

        for (int i = 0; i < Constants.AGENCY_CODE_SIZE; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(fullalphabet.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(fullalphabet.charAt(index));
        }
        return sb.toString();
    }

}
