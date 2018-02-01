package by.matrosov.glickoratingsystem.controller;

import by.matrosov.glickoratingsystem.model.Team1;
import by.matrosov.glickoratingsystem.model.Team3;
import by.matrosov.glickoratingsystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class SimpleController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView();
        List<Team1> list = teamService.getAll();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateRank1", method = RequestMethod.GET)
    public ModelAndView calculateRank(@RequestParam("id1") long id1, @RequestParam("id2") long id2, @RequestParam("s1") double s){
        ModelAndView modelAndView = new ModelAndView();

        Team1 team1 = teamService.getById1(id1);
        Team1 team2 = teamService.getById1(id2);

        double s1;
        if (s == 1){
            s1 = 0;
        }else{
            s1 = 1;
        }

        Team1 team1new = teamService.calculateRank1(team1, team2, s);
        Team1 team2new = teamService.calculateRank1(team2, team1, s1);

        team1.setRating(team1new.getRating());
        team1.setDeviation(team1new.getDeviation());
        team1.setVolatility(team1new.getVolatility());
        team2.setRating(team2new.getRating());
        team2.setDeviation(team2new.getDeviation());
        team2.setVolatility(team2new.getVolatility());

        team1.setCount(team1.getCount() + 1);
        team2.setCount(team2.getCount() + 1);

        teamService.save1(team1);
        teamService.save1(team2);

        modelAndView.addObject("success1", "update done successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateRank3", method = RequestMethod.GET)
    public ModelAndView calculateRank3(@RequestParam("id5") long id5, @RequestParam("id6") long id6, @RequestParam("s2") double s){
        ModelAndView modelAndView = new ModelAndView();

        Team3 team1 = teamService.getById3(id5);
        Team3 team2 = teamService.getById3(id6);

        double s1;
        if (s == 1){
            s1 = 0;
        }else{
            s1 = 1;
        }

        Team3 team1new = teamService.calculateRank3(team1, team2, s);
        Team3 team2new = teamService.calculateRank3(team2, team1, s1);

        team1.setRating(team1new.getRating());
        team1.setDeviation(team1new.getDeviation());
        team1.setVolatility(team1new.getVolatility());
        team2.setRating(team2new.getRating());
        team2.setDeviation(team2new.getDeviation());
        team2.setVolatility(team2new.getVolatility());

        team1.setCount(team1.getCount() + 1);
        team2.setCount(team2.getCount() + 1);

        teamService.save2(team1);
        teamService.save2(team2);

        modelAndView.addObject("success2", "update done successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOddsById", method = RequestMethod.GET)
    public ModelAndView calculateOdds1(@RequestParam("id3") long id3, @RequestParam("id4") long id4){
        ModelAndView modelAndView = new ModelAndView();

        Team1 team11 = teamService.getById1(id3);
        Team1 team12 = teamService.getById1(id4);


        double odds = 100*teamService.calculateOdds(team12.getDeviation()/173.7178, (team11.getRating()-1500)/173.7178, (team12.getRating()-1500)/173.7178);
        //odds = Double.parseDouble(new DecimalFormat("##.##").format(odds));

        modelAndView.addObject("odds1", String.format("%.2f", odds));
        modelAndView.addObject("successMessage2", "odds calculated successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOddsByName", method = RequestMethod.GET)
    public ModelAndView calculateOdds2(@RequestParam("name1") String name1, @RequestParam("name2") String name2){
        ModelAndView modelAndView = new ModelAndView();

        Team1 team11 = teamService.getByName(name1);
        Team1 team12 = teamService.getByName(name2);

        double odds = 100*teamService.calculateOdds(team12.getDeviation()/173.7178, (team11.getRating()-1500)/173.7178, (team12.getRating()-1500)/173.7178);
        double oddsX = 100*teamService.calculateOdds(team12.getTeamBo3().getDeviation()/173.7178, (team11.getTeamBo3().getRating()-1500)/173.7178, (team12.getTeamBo3().getRating()-1500)/173.7178);

        modelAndView.addObject("odds2", String.format("%.2f", odds));
        modelAndView.addObject("oddsX", String.format("%.2f", oddsX));
        modelAndView.addObject("successMessage3", "odds calculated successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
