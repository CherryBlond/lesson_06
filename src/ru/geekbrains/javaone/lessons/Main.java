package ru.geekbrains.javaone.lessons;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            task1();
            System.out.println("files were merged");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Enter search phrase: ");
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();

        try {
            boolean found = task2(phrase);
            if (found) {
                System.out.println("phrase found in file1.txt");
            } else {
                System.out.println("phrase not found in file1.txt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            boolean found = task3(phrase);
            if (found) {
                System.out.println("phrase found in dir data/");
            } else {
                System.out.println("phrase not found in dir data/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void task1() throws Exception {
        FileOutputStream fileResult = new FileOutputStream("./data/result.txt");

        FileInputStream stream1 = new FileInputStream("./data/file1.txt");
        int i;
        while ((i=stream1.read())!= -1)
            fileResult.write(i);
        stream1.close();

        FileInputStream stream2 = new FileInputStream("./data/file2.txt");
        while ((i=stream2.read())!= -1)
            fileResult.write(i);
        stream2.close();

        fileResult.close();
    }

    private static boolean task2(String phrase) throws Exception {
        FileInputStream stream = new FileInputStream("./data/file1.txt");
        String content = "";
        int i;
        while ((i=stream.read())!= -1)
            content += (char)i;

        stream.close();
        return content.contains(phrase);
    }

    private static boolean task3(String phrase) throws Exception {
        File folder = new File("./data/");
        File[] filesInDir = folder.listFiles();
        for (int i =0; i < filesInDir.length; i++) {
            File file = filesInDir[i];
            if (file.isFile()) {
                FileInputStream stream = new FileInputStream(file.getPath());
                String content = "";
                int c;
                while ((c=stream.read())!= -1)
                    content += (char)c;
                stream.close();
                boolean found = content.contains(phrase);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
}
