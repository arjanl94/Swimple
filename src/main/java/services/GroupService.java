package services;

import models.Group;
import models.User;

import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

@Stateless
public class GroupService {

    @PersistenceContext
    EntityManager em;

    public List<Group> getAll() {
        Query query = em.createQuery("select g from Group g", Group.class);
        return query.getResultList();
    }

    public Group create(Group group) {
        em.persist(group);
        return group;
    }

    public Group create(JsonObject object) {
        Group group = new Group();
        group.setName(object.getString("name"));

        JsonArray userArray = object.getJsonArray("users");
        Iterator<JsonValue> iterator = userArray.iterator();

        while (iterator.hasNext()) {
            JsonObject userObject = iterator.next().asJsonObject();
            User user = em.find(User.class, userObject.getInt("id"));
            group.addUser(user);
        }

        em.persist(group);
        return group;
    }
}
