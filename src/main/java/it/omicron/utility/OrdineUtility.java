package it.omicron.utility;

import it.omicron.entities.Ordine;
import it.omicron.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Random;

@Component
public class OrdineUtility {

    private static final String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedChars.toCharArray();
    private int base = allowedCharacters.length;

    @Autowired
    private OrdineRepository ordineRepository;

    public String codiceOrdineGenerator() {
        int rnd1 = getRandomInt();
        int rnd2 = getRandomInt();
        String codice = encode(rnd1) + encode(rnd2);
        Optional<Ordine> ordineOpt = ordineRepository.findByCodiceOrdine(codice);
        if (ordineOpt.isPresent()) {
            return codiceOrdineGenerator();
        }
        return codice;
    }

    private int getRandomInt() {
        int min = 14776336;
        int max = 2147383647;
        Random rnd = new Random();
        return rnd.ints(min, max).findFirst().getAsInt();
    }

    private String encode(int input) {
        StringBuilder encodedString = new StringBuilder();
        if (input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }
        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;

        }
        return encodedString.reverse().toString();
    }

}
