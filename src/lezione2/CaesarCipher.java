package lezione2;

public class CaesarCipher {

    // Method to encrypt a string using Caesar cipher
    public static String encrypt(String plainText, int shift) {
        // Normalize the shift value (in case it's larger than 26)
        shift = shift % 26;

        // If shift is negative, convert to equivalent positive shift
        if (shift < 0) {
            shift = 26 + shift;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);

            // Handle uppercase letters
            if (Character.isUpperCase(ch)) {
                char encrypted = (char) (((ch - 'A' + shift) % 26) + 'A');
                result.append(encrypted);
            }
            // Handle lowercase letters
            else if (Character.isLowerCase(ch)) {
                char encrypted = (char) (((ch - 'a' + shift) % 26) + 'a');
                result.append(encrypted);
            }
            // Leave non-alphabetic characters unchanged
            else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Method to decrypt a string encrypted with Caesar cipher
    public static String decrypt(String cipherText, int shift) {
        // Decryption is just encryption with the opposite shift
        return encrypt(cipherText, 26 - (shift % 26));
    }

}