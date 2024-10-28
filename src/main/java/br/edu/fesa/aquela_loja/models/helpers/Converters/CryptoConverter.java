package br.edu.fesa.aquela_loja.models.helpers.Converters;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES";
    private static final SecretKey SECRET_KEY;

    static {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            SECRET_KEY = keyGen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
