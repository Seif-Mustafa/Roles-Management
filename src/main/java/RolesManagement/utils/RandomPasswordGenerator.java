package RolesManagement.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RandomPasswordGenerator {
    private static final String LOWERCASE_CHARS = "abcdefghijkmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String DIGITS = "23456789";
    private static final String SYMBOLS = "@#$%&";

    // All allowed characters combined
    private static final String ALL_CHARS = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SYMBOLS;

    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a secure password with a guaranteed mix of character types.
     *
     * @param length The total length of the password. Must be at least 8.
     * @return A securely generated random password.
     */
    public  String generateSecurePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        // Use a List<Character> for easy shuffling
        List<Character> passwordChars = new ArrayList<>(length);

        // 1. Guarantee at least one character from each bucket
        passwordChars.add(getRandomChar(LOWERCASE_CHARS));
        passwordChars.add(getRandomChar(UPPERCASE_CHARS));
        passwordChars.add(getRandomChar(DIGITS));
        passwordChars.add(getRandomChar(SYMBOLS));

        // 2. Fill the rest of the password length with random characters
        //    from the combined set.
        int remainingLength = length - 4;
        for (int i = 0; i < remainingLength; i++) {
            passwordChars.add(getRandomChar(ALL_CHARS));
        }

        // 3. Shuffle the list to randomize the positions of the guaranteed chars
        Collections.shuffle(passwordChars, random);

        // 4. Convert the list of characters to a string
        //    Using StringBuilder is efficient.
        StringBuilder passwordBuilder = new StringBuilder(length);
        for (Character ch : passwordChars) {
            passwordBuilder.append(ch);
        }

        return passwordBuilder.toString();
    }

    /**
     * Helper method to get a single random character from a given string.
     */
    private static char getRandomChar(String charSet) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }


}
