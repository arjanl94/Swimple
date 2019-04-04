package swimple.services;

import swimple.dao.TeamRepository;
import swimple.models.Team;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TeamService {

    @Inject
    TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }
}
