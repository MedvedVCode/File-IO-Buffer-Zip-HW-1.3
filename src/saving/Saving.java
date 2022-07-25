package saving;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Saving {
    public static void main(String[] args) {
        GameProgress save01 = new GameProgress(100, 2, 4, 5.3);
        GameProgress save02 = new GameProgress(34, 6, 12, 9.3);
        GameProgress save03 = new GameProgress(67, 4, 4, 5.4);

        String path = "src/installing/Games/savegames/";

        saveGame(path + "save01.dat", save01);
        saveGame(path + "save02.dat", save02);
        saveGame(path + "save03.dat", save03);

        File allFiles = new File(path);

        String[] savePaths = new String[allFiles.list().length];

        for (int i = 0; i < allFiles.list().length; i++) {
            savePaths[i] = path + allFiles.list()[i];
        }

        zipFiles(path + "gamearchive.zip", savePaths);
    }

    private static void zipFiles(String path, String[] list) {
        try (ZipOutputStream targetZip = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < list.length; i++) {
                FileInputStream zipSource = new FileInputStream(list[i]);
                ZipEntry zipEntry = new ZipEntry(list[i].substring(list[i].length() - 10, list[i].length()));

                targetZip.putNextEntry(zipEntry);

                byte[] buffer = new byte[zipSource.available()];
                zipSource.read(buffer);
                targetZip.write(buffer);

                targetZip.closeEntry();
                zipSource.close();

                File file = new File(list[i]);
                file.delete();
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void saveGame(String path, GameProgress save) {
        try (FileOutputStream file = new FileOutputStream(path);
             ObjectOutputStream stream = new ObjectOutputStream(file)) {
            stream.writeObject(save);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

}
