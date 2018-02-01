package by.matrosov.glickoratingsystem.controller;

import by.matrosov.glickoratingsystem.model.Team1;
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

    @RequestMapping(value = "/calculateRank", method = RequestMethod.GET)
    public ModelAndView calculateRank(@RequestParam("id1") long id1, @RequestParam("id2") long id2, @RequestParam("s") double s){
        ModelAndView modelAndView = new ModelAndView();

        Team1 team11 = teamService.getById(id1);
        Team1 team12 = teamService.getById(id2);

        double s1;
        if (s == 1){
            s1 = 0;
        }else{
            s1 = 1;
        }

        Team1 team1New1 = teamService.calculateRank(team11, team12, s);
        Team1 team1New2 = teamService.calculateRank(team12, team11, s1);

        team11.setRating(team1New1.getRating());
        team11.setDeviation(team1New1.getDeviation());
        team11.setVolatility(team1New1.getVolatility());
        team12.setRating(team1New2.getRating());
        team12.setDeviation(team1New2.getDeviation());
        team12.setVolatility(team1New2.getVolatility());

        team11.setCount(team11.getCount() + 1);
        team12.setCount(team12.getCount() + 1);

        teamService.save(team11);

        modelAndView.addObject("successMessage1", "update done successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOddsById", method = RequestMethod.GET)
    public ModelAndView calculateOdds1(@RequestParam("id3") long id3, @RequestParam("id4") long id4){
        ModelAndView modelAndView = new ModelAndView();

        Team1 team11 = teamService.getById(id3);
        Team1 team12 = teamService.getById(id4);


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
