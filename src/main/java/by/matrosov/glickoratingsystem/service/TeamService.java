package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.model.Team1;
import by.matrosov.glickoratingsystem.model.Team3;

import java.util.List;

public interface TeamService {
     List<Team1> getAll();
     Team1 calculateRank1(Team1 team1, Team1 team2, double s);
     Team3 calculateRank3(Team3 team1, Team3 team2, double s);
     Team1 getById1(long id);
     Team3 getById3(long id);
     void save1(Team1 team1);
     void save2(Team3 team3);
     double calculateOdds(double f, double m, double m1);
     Team1 getByName(String s);

     Team1 calculateRankNew(List<Team1> list, List<Double> s);
     Team3 calculateRankNew3(List<Team3> list, List<Double> s);
}
