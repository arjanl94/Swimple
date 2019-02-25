import models.Group;
import models.Role;
import models.Training;
import models.User;
import services.GroupService;
import services.RoleService;
import services.TrainingService;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;

@Singleton
@Startup
public class Seeds {

    @Inject
    UserService userService;

    @Inject
    GroupService groupService;

    @Inject
    TrainingService trainingService;

    @Inject
    RoleService roleService;

    @PostConstruct
    private void initialize() {
        Role adminRole = new Role("ADMIN_ROLE");
        Role coachRole = new Role("COACH_ROLE");

        roleService.create(adminRole);
        roleService.create(coachRole);

        User admin = new User();
        admin.setName("Tom Bakker");
        admin.setEmail("admin@swimple.nl");
        admin.setPassword("testpassword");
        admin.setRole(adminRole);
        admin.setRole(coachRole);
        userService.create(admin);

        User coach = new User();
        coach.setName("Bob de Coach");
        coach.setEmail("coach@swimple.nl");
        coach.setPassword("testpassword");
        admin.setRole(coachRole);
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
