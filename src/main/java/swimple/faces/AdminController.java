package swimple.faces;

import swimple.models.User;
import swimple.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("adminController")
@ViewScoped
public class AdminController implements Serializable {

    @Inject
    UserService userService;

    private List<User> users;
    private DataModel<User> userDataModel;

    @PostConstruct
    private void initialize() {
        users = userService.getAll();
        userDataModel = new ListDataModel<>(users);
    }

    public DataModel<User> getUserDataModel() {
        return userDataModel;
    }

    public void setUserDataModel(DataModel<User> userDataModel) {
        this.userDataModel = userDataModel;
    }
}
