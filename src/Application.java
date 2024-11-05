import service.BruteForseDecipher;
import service.Encryptor;
import service.Validate;



import java.nio.file.Path;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        System.out.println("Шифровальшик методом Цезаря. Вот что я умею:");
        System.out.println("1.Шиврование с ключом\n2.Расшифровка с ключом\n3.Brute force\n0.Выход");
        System.out.println("Выберете пункт меню:");

        Validate validate = new Validate();
        String m =validate.menuItem();

        Scanner scanner = new Scanner(System.in);


        String result = switch (m) {
            case "1", "2", "3" -> {

                System.out.println("Введите путь к файлу для шифрования/расшифровки:");
                String fileIn = scanner.nextLine();
                Path pathIn = Path.of(fileIn);
                if (!validate.fileIsRegular(pathIn) || !validate.fileExists(pathIn)) {
                    yield  "Путь к файлу не корректный";
                }
                if (validate.fileIsEmpty(pathIn)){
                    yield  "Фаил для шифрования/расшифровки пуст";
                }

                System.out.println("Введите путь для сохранения зашифрованного/расшифрованного файла:");
                String fileOut = scanner.nextLine();
                Path pathOut = Path.of(fileOut);
                if (validate.fileIsDirectory(pathOut)) {
                    yield "Не корректный путь";
                }
                if (validate.fileExists(pathOut)) {
                    yield "Фаил по заданному пути существует";
                }

                if(m.equals("1") || m.equals("2")) {
                    System.out.println("Введите ключ:");
                    int key = validate.keyInt();

                    Encryptor encryptor = new Encryptor(pathIn, pathOut, key, m);
                    encryptor.fileCreator();
                } else if (m.equals("3")){
                    BruteForseDecipher bruteForse = new BruteForseDecipher(pathIn, fileOut);
                    bruteForse.hacking();
                }

                yield "Процесс завершен";
            }


            case "0" -> {
                yield "Завершение работы";
            }
            default -> {
                scanner.close();
                yield "Что-то пошло не так";
            }
        };
        System.out.println(result);
    }
}