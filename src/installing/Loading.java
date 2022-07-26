package installing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loading {
    public static void main(String[] args) throws ClassNotFoundException {
        String path = "src/installing/Games/savegames/";
        openZip(path + "gamearchive.zip", path);

        GameProgress game01 = openProgress(path + "save01.dat");
        GameProgress game02 = openProgress(path + "save02.dat");
    }

    private static GameProgress openProgress(String path) throws ClassNotFoundException {
        GameProgress load = null;
        try (ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(path))) {
            load = (GameProgress) objectStream.readObject();
        } catch (IOException er) {
            System.out.println(er.getMessage());
        }
        System.out.println(load);
        return load;
    }

    private static void openZip(String archivePath, String path) {
        try (ZipInputStream zipStream = new ZipInputStream(new FileInputStream(archivePath))) {
            ZipEntry entry;
            String name;
            while ((entry = zipStream.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream archiveTarget = new FileOutputStream(path + name);
                archiveTarget.write(zipStream.readAllBytes());
//                for (int c = zipStream.read(); c !=-1 ; c= zipStream.read()) {
//                    archiveTarget.write(c);
//                }
                archiveTarget.flush();
                zipStream.closeEntry();
                archiveTarget.close();
            }
        } catch (IOException er) {
            System.out.println(er.getMessage());
        }
    }
}
