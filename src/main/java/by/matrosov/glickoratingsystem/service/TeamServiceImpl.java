package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.dao.Team1Dao;
import by.matrosov.glickoratingsystem.dao.Team3Dao;
import by.matrosov.glickoratingsystem.model.Team1;
import by.matrosov.glickoratingsystem.model.Team3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private Team1Dao team1Dao;

    @Autowired
    private Team3Dao team3Dao;

    @Override
    public List<Team1> getAll() {
        return team1Dao.findAll();
    }

    private static double function(double x, double delta, double f, double v, double a, double tay){
        return Math.exp(x) * (Math.pow(delta, 2) - Math.pow(f, 2) - v - Math.exp(x)) / 2*(Math.pow(Math.pow(f, 2) + v + Math.exp(x), 2)) - (x - a)/Math.pow(tay,2);
    }

    /**
     * <p></p>
     * @param team1
     * @param team1
     * @param s
     * @return
     */
    @Override
    public Team1 calculateRank1(Team1 team1, Team1 team2, double s) {
        double tay = 0.2; //const

        double rate1 = team1.getRating();
        double rd1 = team1.getDeviation();
        double volatility = team1.getVolatility();
        double rate2 = team2.getRating();
        double rd2 = team2.getDeviation();

        //step 2

        double mu1 = (rate1 - 1500)/173.7178;
        double f1 = rd1/173.7178;
        double mu2 = (rate2 - 1500)/173.7178;
        double f2 = rd2/173.7178;

        //step 3

        double g2 = 1/Math.sqrt(1 + 3*Math.pow(f2, 2)/Math.pow(Math.PI, 2));
        double E = 1/(1 + Math.exp(-g2*(mu1 - mu2)));
        double v = Math.pow(Math.pow(g2, 2) * E * (1 - E), -1);

        //step 4

        double delta = v * g2 * (s - E);

        //step 5.1

        double a = Math.log(Math.pow(volatility, 2));
        double epsilon = 0.000001;

        //step 5.2

        double b;
        if (Math.pow(delta, 2) > f1 + v){
            b = Math.log(Math.pow(delta, 2) - Math.pow(f1, 2) - v);
        }else {
            int k = 1;
            while (function(a-k*tay, delta, f1, v, a, tay) < 0){
                k = k + 1;
            }
            b = a - k * tay;
        }

        //step 5.3

        double fa = function(a, delta, f1, v, a, tay);
        double fb = function(b, delta, f1, v, a, tay);

        //step 5.4

        double c;
        double fc;
        while (Math.abs(b - a) > epsilon){
            c = a + (a - b) * fa / (fb - fa);
            fc = function(c, delta, f1, v, a, tay);
            if (fc * fb < 0){
                a = b;
                fa = fb;
            }else {
                fa = fa / 2;
            }
            b = c;
            fb = fc;
        }

        //step 5.5

        double volatilityNew = Math.exp(a / 2);

        //step 6

        double preRate = Math.sqrt(Math.pow(f1, 2) + Math.pow(volatilityNew, 2));

        //step 7

        double fNew = 1 / Math.sqrt(1 / Math.pow(preRate, 2) + 1 / v);
        double muNew = mu1 + Math.pow(fNew, 2) * g2 * (s - E);

        //step 8

        double rateNew = 173.7178 * muNew + 1500;
        double rdNew = 173.7178 * fNew;

        return new Team1(rateNew, rdNew, volatilityNew);
    }

    @Override
    public Team3 calculateRank3(Team3 team1, Team3 team2, double s) {
        double tay = 0.2; //const

        double rate1 = team1.getRating();
        double rd1 = team1.getDeviation();
        double volatility = team1.getVolatility();
        double rate2 = team2.getRating();
        double rd2 = team2.getDeviation();

        //step 2

        double mu1 = (rate1 - 1500)/173.7178;
        double f1 = rd1/173.7178;
        double mu2 = (rate2 - 1500)/173.7178;
        double f2 = rd2/173.7178;

        //step 3

        double g2 = 1/Math.sqrt(1 + 3*Math.pow(f2, 2)/Math.pow(Math.PI, 2));
        double E = 1/(1 + Math.exp(-g2*(mu1 - mu2)));
        double v = Math.pow(Math.pow(g2, 2) * E * (1 - E), -1);

        //step 4

        double delta = v * g2 * (s - E);

        //step 5.1

        double a = Math.log(Math.pow(volatility, 2));
        double epsilon = 0.000001;

        //step 5.2

        double b;
        if (Math.pow(delta, 2) > f1 + v){
            b = Math.log(Math.pow(delta, 2) - Math.pow(f1, 2) - v);
        }else {
            int k = 1;
            while (function(a-k*tay, delta, f1, v, a, tay) < 0){
                k = k + 1;
            }
            b = a - k * tay;
        }

        //step 5.3

        double fa = function(a, delta, f1, v, a, tay);
        double fb = function(b, delta, f1, v, a, tay);

        //step 5.4

        double c;
        double fc;
        while (Math.abs(b - a) > epsilon){
            c = a + (a - b) * fa / (fb - fa);
            fc = function(c, delta, f1, v, a, tay);
            if (fc * fb < 0){
                a = b;
                fa = fb;
            }else {
                fa = fa / 2;
            }
            b = c;
            fb = fc;
        }

        //step 5.5

        double volatilityNew = Math.exp(a / 2);

        //step 6

        double preRate = Math.sqrt(Math.pow(f1, 2) + Math.pow(volatilityNew, 2));

        //step 7

        double fNew = 1 / Math.sqrt(1 / Math.pow(preRate, 2) + 1 / v);
        double muNew = mu1 + Math.pow(fNew, 2) * g2 * (s - E);

        //step 8

        double rateNew = 173.7178 * muNew + 1500;
        double rdNew = 173.7178 * fNew;

        return new Team3(rateNew, rdNew, volatilityNew);
    }

    @Override
    public Team1 getById1(long id) {
        return team1Dao.getOne(id);
    }

    @Override
    public Team3 getById3(long id) {
        return team3Dao.getOne(id);
    }

    @Override
    public void save1(Team1 team1) {
        team1Dao.save(team1);
    }

    @Override
    public void save2(Team3 team3) {
        team3Dao.save(team3);
    }

    @Override
    public double calculateOdds(double f, double m, double m1) {
        double g = 1/Math.sqrt(1 + 3*Math.pow(f, 2)/Math.pow(Math.PI, 2));
        return 1/(1 + Math.exp(-g*(m - m1)));
    }

    @Override
    public Team1 getByName(String s) {
        return team1Dao.getByName(s);
    }

    @Override
    public Team1 calculateRankNew(List<Team1> teams, List<Double> s) {
        double tay = 0.5;

        //step 2

        for (Team1 team : teams) {
            team.setRating((team.getRating() - 1500) / 173.7178); //m
            team.setDeviation(team.getDeviation() / 173.7178); //f
        }

        //step 3

        double m = teams.get(0).getRating();
        double f = teams.get(0).getDeviation();
        double sigma = teams.get(0).getVolatility();
        double v = 0;
        for (int i = 1; i < teams.size(); i++) {
            v = v + g(Math.pow(teams.get(i).getDeviation(), 2)) * E(m, teams.get(i).getRating(), teams.get(i).getDeviation())
                    * (1 - E(m, teams.get(i).getRating(), teams.get(i).getDeviation()));
        }
        v = Math.pow(v, -1);

        //step 4

        double x = 0;
        for (int i = 1; i < teams.size(); i++) {
            x = x + g(teams.get(i).getDeviation()) * (s.get(i) - E(m, teams.get(i).getRating(), teams.get(i).getDeviation()));
        }
        double delta = v * x;

        //step 5.1

        double a = Math.log(Math.pow(sigma, 2));
        double epsilon = 0.000001;

        //step 5.2

        double b;
        if (Math.pow(delta, 2) > Math.pow(f, 2) + v){
            b = Math.log(Math.pow(delta, 2) - Math.pow(f, 2) - v);
        }else {
            int k = 1;
            while (function(a-k*tay,delta,f,v,a,tay) < 0){
                k++;
            }
            b = a - k*tay;
        }

        //step 5.3

        double fa = function(a,delta,f,v,a,tay);
        double fb = function(b,delta,f,v,a,tay);

        //step 5.4

        while (Math.abs(b-a) > epsilon){
            double c = a + (a - b) * fa / (fb - fa);
            double fc = function(c,delta,f,v,a,tay);
            if (fc * fb < 0){
                a = b;
                fa = fb;
            }else {
                fa = fa / 2;
            }
            b = c;
            fb = fc;
        }

        //step 5.5

        double sigmaNew = Math.exp(a / 2);

        //step 6

        double fPre = Math.sqrt(Math.pow(f, 2) + Math.pow(sigmaNew, 2));


        //step 7

        double fNew = 1 / Math.sqrt(1 / Math.pow(fPre, 2) + 1 / v);
        double mNew = m + Math.pow(fNew, 2) * x;

        //step 8

        teams.get(0).setRating(173.7178 * mNew + 1500);
        teams.get(0).setDeviation(173.7178 * fNew);
        teams.get(0).setVolatility(sigmaNew);

        for (int i = 1; i < teams.size(); i++) {
            teams.get(i).setRating(teams.get(i).getRating() * 173.7178 + 1500);
            teams.get(i).setDeviation(teams.get(i).getDeviation() * 173.7178);
        }


        //return teams.get(0);
        return new Team1(teams.get(0).getRating(), teams.get(0).getDeviation(), teams.get(0).getVolatility());
    }

    @Override
    public Team3 calculateRankNew3(List<Team3> teams, List<Double> s) {
        double tay = 0.5;

        //step 2

        for (Team3 team : teams) {
            team.setRating((team.getRating() - 1500) / 173.7178); //m
            team.setDeviation(team.getDeviation() / 173.7178); //f
        }

        //step 3

        double m = teams.get(0).getRating();
        double f = teams.get(0).getDeviation();
        double sigma = teams.get(0).getVolatility();
        double v = 0;
        for (int i = 1; i < teams.size(); i++) {
            v = v + g(Math.pow(teams.get(i).getDeviation(), 2)) * E(m, teams.get(i).getRating(), teams.get(i).getDeviation())
                    * (1 - E(m, teams.get(i).getRating(), teams.get(i).getDeviation()));
        }
        v = Math.pow(v, -1);

        //step 4

        double x = 0;
        for (int i = 1; i < teams.size(); i++) {
            x = x + g(teams.get(i).getDeviation()) * (s.get(i) - E(m, teams.get(i).getRating(), teams.get(i).getDeviation()));
        }
        double delta = v * x;

        //step 5.1

        double a = Math.log(Math.pow(sigma, 2));
        double epsilon = 0.000001;

        //step 5.2

        double b;
        if (Math.pow(delta, 2) > Math.pow(f, 2) + v){
            b = Math.log(Math.pow(delta, 2) - Math.pow(f, 2) - v);
        }else {
            int k = 1;
            while (function(a-k*tay,delta,f,v,a,tay) < 0){
                k++;
            }
            b = a - k*tay;
        }

        //step 5.3

        double fa = function(a,delta,f,v,a,tay);
        double fb = function(b,delta,f,v,a,tay);

        //step 5.4

        while (Math.abs(b-a) > epsilon){
            double c = a + (a - b) * fa / (fb - fa);
            double fc = function(c,delta,f,v,a,tay);
            if (fc * fb < 0){
                a = b;
                fa = fb;
            }else {
                fa = fa / 2;
            }
            b = c;
            fb = fc;
        }

        //step 5.5

        double sigmaNew = Math.exp(a / 2);

        //step 6

        double fPre = Math.sqrt(Math.pow(f, 2) + Math.pow(sigmaNew, 2));


        //step 7

        double fNew = 1 / Math.sqrt(1 / Math.pow(fPre, 2) + 1 / v);
        double mNew = m + Math.pow(fNew, 2) * x;

        //step 8

        teams.get(0).setRating(173.7178 * mNew + 1500);
        teams.get(0).setDeviation(173.7178 * fNew);
        teams.get(0).setVolatility(sigmaNew);

        for (int i = 1; i < teams.size(); i++) {
            teams.get(i).setRating(teams.get(i).getRating() * 173.7178 + 1500);
            teams.get(i).setDeviation(teams.get(i).getDeviation() * 173.7178);
        }


        //return teams.get(0);
        return new Team3(teams.get(0).getRating(), teams.get(0).getDeviation(), teams.get(0).getVolatility());
    }

    private static double g(double f){
        return 1 / Math.sqrt(1 + 3 * Math.pow(f, 2) / Math.pow(Math.PI, 2));
    }

    private static double E(double m0, double m1, double f1){
        return 1 / (1 + Math.exp(-g(f1) * (m0 - m1)));
    }
}
