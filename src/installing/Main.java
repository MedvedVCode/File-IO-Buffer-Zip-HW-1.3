package installing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {

        File createSrc = new File("C:\\Work\\Java\\homework_jd22_1.3\\src\\installing\\Games\\src");
        addDirectoryResult(createSrc);

        File createRes = new File(createSrc.getParent(), "res");
        addDirectoryResult(createRes);

        File createSaveGame = new File(createSrc.getParent(), "savegames");
        addDirectoryResult(createSaveGame);

        File createTemp = new File(createSrc.getParent(), "temp");
        addDirectoryResult(createTemp);

        File createMain = new File(createSrc.getPath(), "main");
        addDirectoryResult(createMain);

        File createTest = new File(createSrc.getPath(), "test");
        addDirectoryResult(createTest);

        File createMainJava = new File(createMain.getPath(), "Main.java");
        addFileResult(createMainJava);

        File createUtilsJava = new File(createMain.getPath(),"Utils.java");
        addFileResult(createUtilsJava);

        File createDrawables = new File(createRes.getPath(),"drawables");
        addDirectoryResult(createDrawables);

        File createVectors = new File(createRes.getPath(),"vectors");
        addDirectoryResult(createVectors);

        File createIcons = new File(createRes.getPath(),"icons");
        addDirectoryResult(createIcons);

        File createTempTxt = new File(createTemp.getPath(),"temp.txt");
        addFileResult(createTempTxt);

        try(FileWriter writer = new FileWriter(createTempTxt)){
            writer.write(result.toString());
            writer.flush();
        }
        catch(IOException error){
            System.out.println(error.getMessage());
        }

        System.out.println(result.toString());
    }

    private static void addFileResult(File file) {
        Date createTime = new Date();
        result.append(createTime).append(" --> Создание файла ").append(file.getName());
        try{
            file.createNewFile();
            result.append(" прошло успешно!\n");
        }
        catch(IOException error){
            System.out.println(error.getMessage());
            result.append(" прошло неуспешно!\n");
        }
    }

    private static void addDirectoryResult(File file) {
        Date createTime = new Date();
        result.append(createTime).append(" --> Создание каталога ").append(file.getName());
        if (file.mkdir()) {
            result.append(" прошло успешно!\n");
        } else {
            result.append(" прошло неуспешно!\n");
        }

    }

}
