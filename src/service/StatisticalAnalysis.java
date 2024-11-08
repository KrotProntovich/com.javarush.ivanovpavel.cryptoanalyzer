package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class StatisticalAnalysis {
    private Path pathIn;
    private Path pathOut;
    private Path pathLemm;
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public StatisticalAnalysis(Path pathIn,Path pathOut, Path pathLemm) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.pathLemm = pathLemm;
    }

    public void hacking(){
        try {
            BufferedReader readerWord = Files.newBufferedReader(pathLemm);
            HashSet<String> setWord = new HashSet<>();
            while (readerWord.ready()) {
                setWord.add(readerWord.readLine());
            }

            int key = 0;
            int n = 0;

            for (int i = 0; i < ALPHABET.length; i++) {
                BufferedReader readerText = Files.newBufferedReader(pathIn);
                Encryptor encryptor = new Encryptor(i, "2");
                int count = 0;

                while (readerText.ready()) {
                    String stringText = encryptor.stringCipher(readerText.readLine());
                    for (String stringWord : setWord) {
                        if (stringText.contains(stringWord)) {
                            count++;
                        }
                    }
                }
                if (count > n) {
                    n = count;
                    key = i;
                }
            }
            Encryptor encryptor1 = new Encryptor(pathIn,pathOut,key,"2");
            encryptor1.fileCreator();
            System.out.printf("Ключ = %d.Количество совпадений %d\n", key, n);
        }catch (IOException e) {
            System.out.println("Проблемы с переданным файлом.Возможно несоответствие кодировки");
            e.printStackTrace();
        }

    }
}
