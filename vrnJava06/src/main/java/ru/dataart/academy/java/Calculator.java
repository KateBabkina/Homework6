package ru.dataart.academy.java;

import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int numberOfChar = 0;
        try (ZipInputStream inputZip = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (inputZip.getNextEntry() != null) {
                int currSymbol;
                while ((currSymbol = inputZip.read()) != -1) {
                    if ((char) currSymbol == character) {
                        numberOfChar++;
                    }
                }
                inputZip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfChar;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int maxLength = 0;
        try (ZipInputStream inputZip = new ZipInputStream(new FileInputStream(zipFilePath))) {
            int currLength = 0;
            while (inputZip.getNextEntry() != null) {
                int currSymbol;

                do {
                    currSymbol = inputZip.read();
                    if ((char) currSymbol == ' ' || (char) currSymbol == '\n' || currSymbol == -1) {
                        maxLength = Math.max(maxLength, currLength);
                        currLength = 0;
                    } else {
                        currLength++;
                    }
                } while (currSymbol != -1);
                inputZip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxLength;
    }

}
