package helpers.data;

import static java.text.MessageFormat.format;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataGenerator {

    private static final Faker faker = Faker.instance(new Locale("ru"));

    public static synchronized String randomPhoneCurrentDate() {
        String phone = String.valueOf(System.currentTimeMillis() / 10000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return format("9{0}", phone);
    }

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateDateOfBirth() {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date birthday = faker.date().birthday(18, 60);
        return simpleDateFormat.format(birthday);
    }

    public static String generateGender() {
        List<String> genders = List.of("Мужчина", "Женщина");
        return genders.get(faker.random().nextInt(0, 1));
    }

    public static String generateEmail() {
        String currentTime = String.valueOf(System.currentTimeMillis());
        return format("user.{0}@autotest.text", currentTime);
    }

    public static User generateUser() {
        User result = User.builder()
            .firstName(generateFirstName())
            .lastName(generateLastName())
            .dateOfBirth(generateDateOfBirth())
            .gender(generateGender())
            .email(generateEmail())
            .build();
        return result;
    }

    public static User emptyUser() {
        User result = User.builder()
            .firstName("")
            .lastName("")
            .dateOfBirth("")
            .gender("")
            .email("")
            .build();
        return result;
    }

}
