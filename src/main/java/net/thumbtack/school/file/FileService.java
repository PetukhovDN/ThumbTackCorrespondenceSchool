package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.ColoredRectangle;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileService {
    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName))) {
            fileOutputStream.write(array);
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        byte[] fileInArray;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            fileInArray = new byte[fileInputStream.available()];
            for (int i = 0; i < fileInArray.length; i++) {
                fileInArray[i] = (byte) fileInputStream.read();
            }
        }
        return fileInArray;
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        return readByteArrayFromBinaryFile(file.getPath());
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        byte[] fileInArray;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byteArrayOutputStream.write(array);
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
                fileInArray = new byte[byteArrayInputStream.available() / 2];
                int j = 0;
                while (byteArrayInputStream.available() > 0) {
                    fileInArray[j] = (byte) byteArrayInputStream.read();
                    byteArrayInputStream.skip(1);
                    j++;
                }
            }
        }
        return fileInArray;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bufferedOutputStream.write(array);
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        byte[] fileInArray;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            fileInArray = new byte[bufferedInputStream.available()];
            for (int i = 0; i < fileInArray.length; i++) {
                fileInArray[i] = (byte) bufferedInputStream.read();
            }
        }
        return fileInArray;
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        return readByteArrayFromBinaryFileBuffered(file.getPath());
    }

    public static void writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(rect.getxLeft());
            dataOutputStream.writeInt(rect.getyTop());
            dataOutputStream.writeInt(rect.getxRight());
            dataOutputStream.writeInt(rect.getyBottom());
        }
    }

    public static Rectangle readRectangleFromBinaryFile(File file) throws IOException {
        Rectangle rectangle;
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            rectangle = new Rectangle(dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt());
        }
        return rectangle;
    }

    public static void writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(rect.getxLeft());
            dataOutputStream.writeInt(rect.getyTop());
            dataOutputStream.writeInt(rect.getxRight());
            dataOutputStream.writeInt(rect.getyBottom());
            dataOutputStream.writeUTF(rect.getColor().toString());
        }
    }

    public static ColoredRectangle readColoredRectangleFromBinaryFile(File file) throws IOException {
        ColoredRectangle coloredRectangle = null;
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            coloredRectangle = new ColoredRectangle(
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readUTF());
        } catch (ColorException e) {
            e.printStackTrace();
        }
        return coloredRectangle;
    }

    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (Rectangle rect : rects) {
                {
                    dataOutputStream.writeInt(rect.getxLeft());
                    dataOutputStream.writeInt(rect.getyTop());
                    dataOutputStream.writeInt(rect.getxRight());
                    dataOutputStream.writeInt(rect.getyBottom());
                }
            }
        }
    }

    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        Rectangle[] rects;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            rects = new Rectangle[(int) randomAccessFile.length() / 16];
            int j = 0;
            for (int i = (int) file.length() - 16; i >= 0; i -= 16) {
                randomAccessFile.seek(i);
                rects[j] = new Rectangle(
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt());
                j++;
            }
        }
        return rects;
    }


    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(String.format("%d %d %d %d", rect.getxLeft(), rect.getyTop(), rect.getxRight(), rect.getyBottom()));
        }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException {
        Rectangle rect;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int[] numArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rect = new Rectangle(numArr[0], numArr[1], numArr[2], numArr[3]);
        }
        return rect;
    }

    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(rect.getxLeft() + "\n");
            bufferedWriter.write(rect.getyTop() + "\n");
            bufferedWriter.write(rect.getxRight() + "\n");
            bufferedWriter.write(rect.getyBottom() + "\n");
        }
    }

    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException {
        Rectangle rect;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int[] numArr = new int[4];
            for (int i = 0; i < 4; i++) {
                numArr[i] = Integer.parseInt(bufferedReader.readLine());
            }
            rect = new Rectangle(numArr[0], numArr[1], numArr[2], numArr[3]);
        }
        return rect;
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8)) {
            printWriter.write(String.format("%s %s %s", trainee.getFirstName(), trainee.getLastName(), trainee.getRating()));
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException {
        Trainee trainee = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] strings = bufferedReader.readLine().split(" ");
            trainee = new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
        } catch (TrainingException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8)) {
            printWriter.write(trainee.getFirstName() + "\n");
            printWriter.write(trainee.getLastName() + "\n");
            printWriter.write(trainee.getRating() + "\n");
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException {
        Trainee trainee = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            trainee = new Trainee(bufferedReader.readLine(), bufferedReader.readLine(), Integer.parseInt(bufferedReader.readLine()));
        } catch (TrainingException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException {
        Trainee trainee = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            trainee = (Trainee) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static String serializeTraineeToJsonString(Trainee trainee) throws IOException {
        return new Gson().toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) throws IOException {
        return new Gson().fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            new Gson().toJson(trainee, bufferedWriter);
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        Trainee trainee;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            trainee = new Gson().fromJson(bufferedReader, Trainee.class);
        }
        return trainee;
    }
}
