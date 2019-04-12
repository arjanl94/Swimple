package swimple.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import swimple.dao.GroupRepository;
import swimple.dao.UserRepository;
import swimple.models.Group;
import swimple.models.User;

@Stateless
public class GroupService {

    @Inject
    GroupRepository groupRepository;

    @Inject
    UserRepository userRepository;

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group create(Group group) {
        groupRepository.save(group);
        return group;
    }

    public Group create(JsonObject object) {
        Group group = new Group();
        group.setName(object.getString("name"));

        JsonArray userArray = object.getJsonArray("users");
        Iterator<JsonValue> iterator = userArray.iterator();

        while (iterator.hasNext()) {
            JsonObject userObject = iterator.next().asJsonObject();
            Optional<User> user = userRepository.findById(userObject.getInt("id"));
            user.ifPresent(group::addUser);
        }

        groupRepository.save(group);
        return group;
    }
}
