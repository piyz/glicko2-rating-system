package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.model.Team1;

import java.util.List;

public interface TeamService {
     List<Team1> getAll();
     Team1 calculateRank(Team1 team11, Team1 team12, double s);
     Team1 getById(long id);
     void save(Team1 team1);
     double calculateOdds(double f, double m, double m1);
     Team1 getByName(String s);
}
