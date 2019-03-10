package swimple.factories;

import com.github.javafaker.Faker;
import swimple.models.Training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingsFactory {

    Faker faker = new Faker();

    public Training create() {
        Training training = new Training();
        training.setDescription(faker.friends().character());
        training.setLocation(faker.friends().location());
        training.setStartDate(new Date());
        training.setEndDate(new Date());
        return training;
    }

    public List<Training> createList(int size) {
        List<Training> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(create());
        }

        return list;
    }
}
