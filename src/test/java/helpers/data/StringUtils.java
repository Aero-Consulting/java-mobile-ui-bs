package helpers.data;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public abstract class StringUtils {

    public static String formatPhone(String phone) {
        String phoneMask = "+7 (###)###-##-##";
        try {
            MaskFormatter maskFormatter = new MaskFormatter(phoneMask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            String formattedPhone = maskFormatter.valueToString(phone);
            return formattedPhone;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatPhone(String phone, String phoneMask) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(phoneMask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            String formattedPhone = maskFormatter.valueToString(phone);
            return formattedPhone;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
