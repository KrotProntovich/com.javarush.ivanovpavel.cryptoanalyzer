package service;

import java.nio.file.Path;

public class BruteForseDecipher {
    private Path pathIn;
    private String fileOut;
    private final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public BruteForseDecipher(Path pathIn, String fileOut) {
        this.pathIn = pathIn;
        this.fileOut = fileOut;
    }

    public void hacking(){
        for (int i = 0; i < ALPHABET.length; i++) {
            String src = fileOut+"=key"+i+".txt";
            Path pathOut = Path.of(src);
            int key = i;
            Encryptor encryptor = new Encryptor(pathIn, pathOut, key, "2");
            encryptor.fileCreator();
        }
    }
}
