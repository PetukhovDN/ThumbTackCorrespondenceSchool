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
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.readAllBytes();
        }
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        byte[] fileInArray;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byteArrayOutputStream.write(array);
            byte[] buf = byteArrayOutputStream.toByteArray();
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf)) {
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
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            return bufferedInputStream.readAllBytes();
        }
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
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            return new Rectangle(dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt());
        }
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

    public static ColoredRectangle readColoredRectangleFromBinaryFile(File file) throws IOException, ColorException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            return new ColoredRectangle(
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readInt(),
                    dataInputStream.readUTF());
        }
    }

    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rectangles) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (Rectangle rectangle : rectangles) {
                dataOutputStream.writeInt(rectangle.getxLeft());
                dataOutputStream.writeInt(rectangle.getyTop());
                dataOutputStream.writeInt(rectangle.getxRight());
                dataOutputStream.writeInt(rectangle.getyBottom());
            }
        }
    }

    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        Rectangle[] rectangles;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            rectangles = new Rectangle[(int) randomAccessFile.length() / 16];
            int j = 0;
            for (int i = (int) file.length() - 16; i >= 0; i -= 16) {
                randomAccessFile.seek(i);
                rectangles[j] = new Rectangle(
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readInt());
                j++;
            }
        }
        return rectangles;
    }


    public static void writeRectangleToTextFileOneLine(File file, Rectangle rectangle) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            int xLeft = rectangle.getxLeft();
            int yTop = rectangle.getyTop();
            int xRight = rectangle.getxRight();
            int yBottom = rectangle.getyBottom();
            bufferedWriter.write(String.format("%d %d %d %d", xLeft, yTop, xRight, yBottom));
        }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int[] numArr = Arrays.stream(bufferedReader.readLine()
                    .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new Rectangle(numArr[0], numArr[1], numArr[2], numArr[3]);
        }
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int[] numArr = new int[4];
            for (int i = 0; i < 4; i++) {
                numArr[i] = Integer.parseInt(bufferedReader.readLine());
            }
            return new Rectangle(numArr[0], numArr[1], numArr[2], numArr[3]);
        }
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8)) {
            String firstName = trainee.getFirstName();
            String lastName = trainee.getLastName();
            int rating = trainee.getRating();
            printWriter.write(String.format("%s %s %d", firstName, lastName, rating));
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] strings = bufferedReader.readLine().split(" ");
            return new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
        }
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8)) {
            printWriter.write(trainee.getFirstName() + "\n");
            printWriter.write(trainee.getLastName() + "\n");
            printWriter.write(trainee.getRating() + "\n");
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return new Trainee(br.readLine(), br.readLine(), Integer.parseInt(br.readLine()));
        }
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) objectInputStream.readObject();
        }
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        return new Gson().toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        return new Gson().fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            new Gson().toJson(trainee, bufferedWriter);
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return new Gson().fromJson(bufferedReader, Trainee.class);
        }
    }
}
