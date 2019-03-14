package swimple.services;

import swimple.dao.TrainingRepository;
import swimple.models.Training;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TrainingService {

    @Inject
    private TrainingRepository trainingRepository;

    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    public Training get(int id) {
        return trainingRepository.findById(id).get();
    }

    public Training create(Training training) {
        trainingRepository.save(training);
        return training;
    }

    public Training update(Training training) {
        trainingRepository.save(training);
        return training;
    }
}
