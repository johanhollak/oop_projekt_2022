package com.ut.oop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TestTextGenerator {
    public static void generate(String filename) {

        Logger logger = LoggerFactory.getLogger(Server.class);

        File file = new File(Paths.get(System.getProperty("user.dir")) + "\\src\\" + filename);
        while (true) {
            int i = 0;
            try (PrintWriter pw = new PrintWriter(new FileWriter(file), true)) {
                do {
                    pw.println(new Random().nextDouble());
                    i++;
                } while (i < 100);

            } catch (IOException e) {
                logger.error(e.getMessage() + " from TestTextGenerator");
            }
            try {
                Thread.sleep(15000);
                FileClient.main(new String[0]);
            } catch (InterruptedException e) {
                logger.error(e.getMessage() + " from TestTextGenerator");
            }
        }
    }

    public static void main(String[] args) {
        generate("dummygen.txt");
    }
}