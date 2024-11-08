package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Validate {

    public String menuItem() {
        Scanner scanner = new Scanner(System.in);
        String keyboard = scanner.nextLine();
        while (!keyboard.equals("1") && !keyboard.equals("2") && !keyboard.equals("3") && !keyboard.equals("4")&& !keyboard.equals("0")) {
            System.out.println("Вветите пункт из меню:");
            keyboard = scanner.nextLine();
        }

        return keyboard;
    }

    public boolean fileIsRegular(Path file) {
        if (Files.isRegularFile(file))
            return true;
        else
            return false;
    }//это фаил а не директория

    public boolean fileIsDirectory(Path file) {
        if (Files.isDirectory(file))
            return true;
        else
            return false;
    }//это директория а не фаил

    public boolean fileExists(Path file) {
        if (Files.exists(file)) {
            return true;
        } else {
            return false;
        }
    }//объект по заданному пути существует

    public boolean fileIsEmpty(Path file) {
        try {
            if (Files.size(file) == 0)
                return true;
            else
                return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }// фаил пуст

    public int keyInt() {
        Scanner scanner = new Scanner(System.in);
        String stringKey = scanner.nextLine();
        String regul = "\\d+";
        int key = 0;
        for (int i = 3; i > 0; i--) {
            if (stringKey.matches(regul) && Integer.parseInt(stringKey) < 1_000_000 && Integer.parseInt(stringKey) > 0) {
                key = Integer.parseInt(stringKey);
            } else {
                System.out.println("Осталось " + i + " попытки. Введите положительное целое число(не более 6 символов):");
                stringKey = scanner.nextLine();
            }
        }
        return key;
    }//ключ число
}
