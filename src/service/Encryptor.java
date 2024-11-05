package service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encryptor {

    private final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private Path pathIn;
    private Path pathOut;
    private int key;
    private String menu;


    public Encryptor(Path pathIn, Path pathOut, int key, String menu) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.key = key;
        this.menu = menu;
    }

    public void fileCreator() {
        try {
            BufferedReader reader = Files.newBufferedReader(pathIn);
            BufferedWriter writer = Files.newBufferedWriter(pathOut);
            while (reader.ready()) {
                String s = stringCipher(reader.readLine() + "\n");
                writer.write(s);
                writer.flush();
            }
        }catch (IOException e) {
            System.out.println("Проблемы с переданным файлом.Возможно несоответствие кодировки");
            e.printStackTrace();
        }
    }


    private char[] alphabetCipher() {
        char[] alphabetCipher = new char[ALPHABET.length];
        int n = key % ALPHABET.length;
        for (int i = 0; i < ALPHABET.length; i++) {
            if (n > ALPHABET.length - 1)
                n = 0;
            if (this.menu.equals("1"))
                alphabetCipher[n] = ALPHABET[i];
            else if (this.menu.equals("2"))
                alphabetCipher[i] = ALPHABET[n];
            n++;
        }
        return alphabetCipher;
    }


    private String stringCipher(String buff) {
        String string = buff.toLowerCase();
        StringBuilder builder = new StringBuilder(string);
        for (int i = 0; i < builder.length(); i++) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (builder.charAt(i) == ALPHABET[j]) {
                    builder.setCharAt(i, alphabetCipher()[j]);
                    break;
                }
            }
        }
        String result = builder.toString();
        return result;
    }


}