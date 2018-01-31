package by.matrosov.glickoratingsystem.controller;

import by.matrosov.glickoratingsystem.model.Team;
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
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public ModelAndView hello(@RequestParam("id1") long id1, @RequestParam("id2") long id2, @RequestParam("s") int s){
        ModelAndView modelAndView = new ModelAndView();
        Team team1 = teamService.getById(id1);
        Team team2 = teamService.getById(id2);


        Team teamNew = teamService.calculateRank(team1, team2, s);

        team1.setRating(teamNew.getRating());
        team1.setDeviation(teamNew.getDeviation());
        team1.setVolatility(teamNew.getVolatility());

        teamService.save(team1);
        //List<Team> list = teamService.getAll();
        //modelAndView.addObject("list", list);

        //modelAndView.addObject("rank", teamNew.toString());
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
