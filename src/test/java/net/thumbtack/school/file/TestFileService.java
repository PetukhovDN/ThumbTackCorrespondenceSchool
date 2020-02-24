package net.thumbtack.school.file;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.figures.v3.ColoredRectangle;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.junit.Assert.*;

//import net.thumbtack.school.colors.v3.ColorException;

public class TestFileService {
    @TempDir
    public Path tempDir;


    @Test
    public void testFileReadWriteByteArray1() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeByteArrayToBinaryFile(file, arrayToWrite);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFile(file);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteByteArray2() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        String fileName = Files.createFile(tempDir.resolve("test.dat")).toFile().getAbsolutePath();
        FileService.writeByteArrayToBinaryFile(fileName, arrayToWrite);
        File file = new File(fileName);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFile(fileName);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testByteStreamReadWriteByteArray() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        byte[] result = FileService.writeAndReadByteArrayUsingByteStream(arrayToWrite);
        assertArrayEquals(new byte[]{0, 5, 67}, result);
    }

    @Test
    public void testFileReadWriteByteArray1Buffered() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeByteArrayToBinaryFileBuffered(file, arrayToWrite);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(file);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteByteArray2Buffered() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        String fileName = Files.createFile(tempDir.resolve("test.dat")).toFile().getAbsolutePath();
        FileService.writeByteArrayToBinaryFileBuffered(fileName, arrayToWrite);
        File file = new File(fileName);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(fileName);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteRectangleBinary() throws IOException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeRectangleToBinaryFile(file, rectToWrite);
        assertTrue(file.exists());
        assertEquals(16, file.length());
        Rectangle rectRead = FileService.readRectangleFromBinaryFile(file);
        assertEquals(rectToWrite, rectRead);
    }

    @Test
    public void testFileReadWriteColoredRectangleBinary() throws ColorException, IOException {
        ColoredRectangle rectToWrite = new ColoredRectangle(10000, 10000, 20000, 20000, Color.RED);
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeColoredRectangleToBinaryFile(file, rectToWrite);
        assertTrue(file.exists());
        assertEquals(21, file.length());
        ColoredRectangle rectRead = FileService.readColoredRectangleFromBinaryFile(file);
        assertEquals(rectToWrite, rectRead);
    }

    @Test
    public void testFileReadRectangleArrayBinary() throws IOException {
        int count = 5;
        Rectangle[] rectsToWrite = new Rectangle[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            rectsToWrite[i] = new Rectangle(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
        }
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeRectangleArrayToBinaryFile(file, rectsToWrite);
        assertTrue(file.exists());
        assertEquals(count * 16, file.length());
        Rectangle[] rectsRead = FileService.readRectangleArrayFromBinaryFileReverse(file);
        for (int i = 0; i < rectsRead.length / 2; i++) {
            Rectangle temp = rectsRead[i];
            rectsRead[i] = rectsRead[rectsRead.length - i - 1];
            rectsRead[rectsRead.length - i - 1] = temp;
        }
        assertArrayEquals(rectsToWrite, rectsRead);
    }

    @Test
    public void testFileReadWriteRectangleTextOneLine() throws IOException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeRectangleToTextFileOneLine(file, rectToWrite);
        assertTrue(file.exists());
        assertEquals(1, Files.readAllLines(file.toPath()).size());
        Rectangle rectRead = FileService.readRectangleFromTextFileOneLine(file);
        assertEquals(rectToWrite, rectRead);
    }

    @Test
    public void testFileReadWriteRectangleTextFourLines() throws IOException {
        Rectangle rectToWrite = new Rectangle(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeRectangleToTextFileFourLines(file, rectToWrite);
        assertTrue(file.exists());
        assertEquals(4, Files.readAllLines(file.toPath()).size());
        Rectangle rectRead = FileService.readRectangleFromTextFileFourLines(file);
        assertEquals(rectToWrite, rectRead);
    }

    @Test
    public void testFileReadWriteTraineeTextOneLine() throws NumberFormatException, TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeTraineeToTextFileOneLine(file, traineeToWrite);
        assertTrue(file.exists());
        assertEquals(1, Files.readAllLines(file.toPath()).size());
        Trainee traineeRead = FileService.readTraineeFromTextFileOneLine(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileReadWriteTraineeTextThreeLines() throws NumberFormatException, TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeTraineeToTextFileThreeLines(file, traineeToWrite);
        assertTrue(file.exists());
        assertEquals(3, Files.readAllLines(file.toPath()).size());
        Trainee traineeRead = FileService.readTraineeFromTextFileThreeLines(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileSerializeDeserializeTraineeBinary() throws TrainingException, ClassNotFoundException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.serializeTraineeToBinaryFile(file, traineeToWrite);
        assertTrue(file.exists());
        Trainee traineeRead = FileService.deserializeTraineeFromBinaryFile(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testStringSerializeDeserializeTraineeJson() throws TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        String json = FileService.serializeTraineeToJsonString(traineeToWrite);
        Trainee traineeRead = FileService.deserializeTraineeFromJsonString(json);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileSerializeDeserializeTraineeJson() throws TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.serializeTraineeToJsonFile(file, traineeToWrite);
        assertTrue(file.exists());
        Trainee traineeRead = FileService.deserializeTraineeFromJsonFile(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testThrowsIOException() {
        Method[] declaredMethods = FileService.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("serializeTraineeToJsonString") || method.getName().equals("deserializeTraineeFromJsonString")) {
                continue;
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            boolean throwIOException = false;
            for (Class<?> exception : exceptionTypes) {
                if (exception == IOException.class) {
                    throwIOException = true;
                    break;
                }
            }
            if (!throwIOException) {
                fail("Every public method must throw IOException");
            }
        }
    }

}
