package swimple;

import swimple.models.Group;
import swimple.models.Training;
import swimple.models.User;
import swimple.services.GroupService;
import swimple.services.TrainingService;
import swimple.services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class Seeds {

    @Inject
    UserService userService;

    @Inject
    GroupService groupService;

    @Inject
    TrainingService trainingService;

    @PostConstruct
    private void initialize() {
        User admin = new User();
        admin.setName("Tom Bakker");
        admin.setEmail("admin@swimple.nl");
        admin.setPassword("testpassword");
        admin.setAdmin(true);
        admin.setCoach(true);
        userService.create(admin);

        User coach = new User();
        coach.setName("Bob de Coach");
        coach.setEmail("coach@swimple.nl");
        coach.setPassword("testpassword");
        coach.setCoach(true);
        userService.create(coach);

        User swimmer = new User();
        swimmer.setName("Siem Swimmer");
        swimmer.setEmail("swimmer@swimple.nl");
        swimmer.setPassword("testpassword");
        userService.create(swimmer);

        Group core = new Group();
        core.setName("Core");
        core.addUser(admin);
        core.addUser(swimmer);
        groupService.create(core);

        Training training = new Training();
        training.setDescription("Endurance training");
        training.setStartDate(new Date());
        training.setEndDate(new Date());
        training.setLocation("Zwembad Olympia");
        trainingService.create(training);
    }
}
