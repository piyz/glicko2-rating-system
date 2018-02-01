package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.model.Team;

import java.util.List;

public interface TeamService {
     List<Team> getAll();
     Team calculateRank(Team team1, Team team2, double s);
     Team getById(long id);
     void save(Team team);
}
