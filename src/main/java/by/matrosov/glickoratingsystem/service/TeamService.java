package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.model.TeamBo1;

import java.util.List;

public interface TeamService {
     List<TeamBo1> getAll();
     TeamBo1 calculateRank(TeamBo1 teamBo11, TeamBo1 teamBo12, double s);
     TeamBo1 getById(long id);
     void save(TeamBo1 teamBo1);
     double calculateOdds(double f, double m, double m1);
     TeamBo1 getByName(String s);
}
