package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.model.Trainee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTraineeOperations extends TestBase {

    @Test
    public void testInsertTrainee() {
        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
        Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
        assertEquals(traineeIvanov, traineeIvanovFromDB);
    }

    @Test
    public void testInsertTraineeWithNullFirstName() {
        assertThrows(RuntimeException.class, () -> {
            Trainee traineeIvanov = new Trainee(null, "Иванов", 5);
            traineeDao.insert(null, traineeIvanov);
        });
    }

    @Test
    public void testUpdateTrainee() {
        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
        Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
        assertEquals(traineeIvanov, traineeIvanovFromDB);
        traineeIvanov.setLastName("Федор");
        traineeDao.update(traineeIvanov);
        traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
        assertEquals(traineeIvanov, traineeIvanovFromDB);
    }

    @Test
    public void testUpdateTraineeSetNullLastName() {
        assertThrows(RuntimeException.class, () -> {
            Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
            Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
            assertEquals(traineeIvanov, traineeIvanovFromDB);
            traineeIvanov.setLastName(null);
            traineeDao.update(traineeIvanov);
        });
    }

    @Test
    public void testDeleteTrainee() {
        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
        Trainee traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
        assertEquals(traineeIvanov, traineeIvanovFromDB);
        traineeDao.delete(traineeIvanov);
        traineeIvanovFromDB = traineeDao.getById(traineeIvanov.getId());
        assertNull(traineeIvanovFromDB);
    }

    @Test
    public void testInsertTwoTrainees() {
        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
        Trainee traineePetrov = insertTrainee("Петр", "Петров", 4);
        List<Trainee> trainees = new ArrayList<>();
        trainees.add(traineeIvanov);
        trainees.add(traineePetrov);
        List<Trainee> traineesFromDB = traineeDao.getAll();
        assertEquals(trainees, traineesFromDB);
    }

    @Test
    public void testIfCondition() {
        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5);
        Trainee traineePetrovPetr = insertTrainee("Петр", "Петров", 4);
        Trainee traineePetrovFedor = insertTrainee("Федор", "Петров", 3);
        Trainee traineeSidorov = insertTrainee("Петр", "Сидоров", 4);
        Trainee traineeSidorenko = insertTrainee("Иван", "Сидоренко", 2);

        List<Trainee> traineesFull = new ArrayList<>();
        traineesFull.add(traineeIvanov);
        traineesFull.add(traineePetrovPetr);
        traineesFull.add(traineePetrovFedor);
        traineesFull.add(traineeSidorov);
        traineesFull.add(traineeSidorenko);

        List<Trainee> traineesIvan = new ArrayList<>();
        traineesIvan.add(traineeIvanov);
        traineesIvan.add(traineeSidorenko);

        List<Trainee> traineesSidor = new ArrayList<>();
        traineesSidor.add(traineeSidorov);
        traineesSidor.add(traineeSidorenko);

        List<Trainee> traineesPetrovWithRating4 = new ArrayList<>();
        traineesPetrovWithRating4.add(traineePetrovPetr);

        List<Trainee> traineesFullFromDB = traineeDao.getAll();
        traineesFullFromDB.sort(Comparator.comparingInt(Trainee::getId));
        assertEquals(traineesFull, traineesFullFromDB);

        List<Trainee> traineesIvanFromDB = traineeDao.getAllWithParams("Иван", null, null);
        traineesIvanFromDB.sort(Comparator.comparingInt(Trainee::getId));
        assertEquals(traineesIvan, traineesIvanFromDB);

        List<Trainee> traineesSidorFromDB = traineeDao.getAllWithParams(null, "Сидор%", null);
        traineesSidorFromDB.sort(Comparator.comparingInt(Trainee::getId));
        assertEquals(traineesSidor, traineesSidorFromDB);

        List<Trainee> traineesPetrovWithRating4FromDB = traineeDao.getAllWithParams(null, "Петров", 4);
        traineesPetrovWithRating4FromDB.sort(Comparator.comparingInt(Trainee::getId));
        assertEquals(traineesPetrovWithRating4, traineesPetrovWithRating4FromDB);

    }

    @Test
    public void testBatchInsertTrainees() {
        Trainee traineeIvanov = new Trainee("Иван", "Иванов", 5);
        Trainee traineePetrovPetr = new Trainee("Петр", "Петров", 4);
        Trainee traineePetrovFedor = new Trainee("Федор", "Петров", 3);
        Trainee traineeSidorov = new Trainee("Петр", "Сидоров", 4);
        Trainee traineeSidorenko = new Trainee("Иван", "Сидоренко", 2);

        List<Trainee> traineesFull = new ArrayList<>();
        traineesFull.add(traineeIvanov);
        traineesFull.add(traineePetrovPetr);
        traineesFull.add(traineePetrovFedor);
        traineesFull.add(traineeSidorov);
        traineesFull.add(traineeSidorenko);
        traineeDao.batchInsert(traineesFull);
        List<Trainee> traineesFullFromDB = traineeDao.getAll();
        traineesFullFromDB.sort(Comparator.comparingInt(Trainee::getId));
        assertEquals(traineesFull, traineesFullFromDB);
    }

}


