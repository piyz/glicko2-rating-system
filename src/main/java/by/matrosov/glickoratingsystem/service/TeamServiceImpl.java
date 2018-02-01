package by.matrosov.glickoratingsystem.service;

import by.matrosov.glickoratingsystem.dao.TeamDao;
import by.matrosov.glickoratingsystem.model.Team1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public List<Team1> getAll() {
        return teamDao.findAll();
    }

    private static double function(double x, double delta, double f, double v, double a, double tay){
        return Math.exp(x) * (Math.pow(delta, 2) - Math.pow(f, 2) - v - Math.exp(x)) / 2*(Math.pow(Math.pow(f, 2) + v + Math.exp(x), 2)) - (x - a)/Math.pow(tay,2);
    }

    /**
     * <p></p>
     * @param team11
     * @param team12
     * @param s
     * @return
     */
    @Override
    public Team1 calculateRank(Team1 team11, Team1 team12, double s) {
        double tay = 0.2; //const

        double rate1 = team11.getRating();
        double rd1 = team11.getDeviation();
        double volatility = team11.getVolatility();
        double rate2 = team12.getRating();
        double rd2 = team12.getDeviation();

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
    public Team1 getById(long id) {
        return teamDao.getOne(id);
    }

    @Override
    public void save(Team1 team1) {
        teamDao.save(team1);
    }

    @Override
    public double calculateOdds(double f, double m, double m1) {
        double g = 1/Math.sqrt(1 + 3*Math.pow(f, 2)/Math.pow(Math.PI, 2));
        return 1/(1 + Math.exp(-g*(m - m1)));
    }

    @Override
    public Team1 getByName(String s) {
        return teamDao.getByName(s);
    }
}
