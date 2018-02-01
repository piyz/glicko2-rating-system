package by.matrosov.glickoratingsystem.controller;

import by.matrosov.glickoratingsystem.model.Team;
import by.matrosov.glickoratingsystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SimpleController {

    @Autowired
    private TeamService teamService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateRank", method = RequestMethod.GET)
    public ModelAndView calculateRank(@RequestParam("id1") long id1, @RequestParam("id2") long id2, @RequestParam("s") double s){
        ModelAndView modelAndView = new ModelAndView();

        Team team1 = teamService.getById(id1);
        Team team2 = teamService.getById(id2);

        double s1;
        if (s == 1){
            s1 = 0;
        }else{
            s1 = 1;
        }

        Team teamNew1 = teamService.calculateRank(team1, team2, s);
        Team teamNew2 = teamService.calculateRank(team2, team1, s1);

        team1.setRating(teamNew1.getRating());
        team1.setDeviation(teamNew1.getDeviation());
        team1.setVolatility(teamNew1.getVolatility());
        team2.setRating(teamNew2.getRating());
        team2.setDeviation(teamNew2.getDeviation());
        team2.setVolatility(teamNew2.getVolatility());

        team1.setCount(team1.getCount() + 1);
        team2.setCount(team2.getCount() + 1);

        teamService.save(team1);

        modelAndView.addObject("successMessage1", "update done successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOdds", method = RequestMethod.GET)
    public ModelAndView calculateOdd(@RequestParam("id3") long id3, @RequestParam("id4") long id4){
        ModelAndView modelAndView = new ModelAndView();

        Team team1 = teamService.getById(id3);
        Team team2 = teamService.getById(id4);


        double odds = teamService.calculateOdds(team2.getDeviation()/173.7178, (team1.getRating()-1500)/173.7178, (team2.getRating()-1500)/173.7178);


        modelAndView.addObject("odds", odds);
        modelAndView.addObject("successMessage2", "odds calculate successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
