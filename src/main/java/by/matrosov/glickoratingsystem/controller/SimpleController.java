package by.matrosov.glickoratingsystem.controller;

import by.matrosov.glickoratingsystem.model.TeamBo1;
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
        List<TeamBo1> list = teamService.getAll();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateRank", method = RequestMethod.GET)
    public ModelAndView calculateRank(@RequestParam("id1") long id1, @RequestParam("id2") long id2, @RequestParam("s") double s){
        ModelAndView modelAndView = new ModelAndView();

        TeamBo1 teamBo11 = teamService.getById(id1);
        TeamBo1 teamBo12 = teamService.getById(id2);

        double s1;
        if (s == 1){
            s1 = 0;
        }else{
            s1 = 1;
        }

        TeamBo1 teamBo1New1 = teamService.calculateRank(teamBo11, teamBo12, s);
        TeamBo1 teamBo1New2 = teamService.calculateRank(teamBo12, teamBo11, s1);

        teamBo11.setRating(teamBo1New1.getRating());
        teamBo11.setDeviation(teamBo1New1.getDeviation());
        teamBo11.setVolatility(teamBo1New1.getVolatility());
        teamBo12.setRating(teamBo1New2.getRating());
        teamBo12.setDeviation(teamBo1New2.getDeviation());
        teamBo12.setVolatility(teamBo1New2.getVolatility());

        teamBo11.setCount(teamBo11.getCount() + 1);
        teamBo12.setCount(teamBo12.getCount() + 1);

        teamService.save(teamBo11);

        modelAndView.addObject("successMessage1", "update done successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOddsById", method = RequestMethod.GET)
    public ModelAndView calculateOdds1(@RequestParam("id3") long id3, @RequestParam("id4") long id4){
        ModelAndView modelAndView = new ModelAndView();

        TeamBo1 teamBo11 = teamService.getById(id3);
        TeamBo1 teamBo12 = teamService.getById(id4);


        double odds = 100*teamService.calculateOdds(teamBo12.getDeviation()/173.7178, (teamBo11.getRating()-1500)/173.7178, (teamBo12.getRating()-1500)/173.7178);
        //odds = Double.parseDouble(new DecimalFormat("##.##").format(odds));

        modelAndView.addObject("odds1", String.format("%.2f", odds));
        modelAndView.addObject("successMessage2", "odds calculated successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/calculateOddsByName", method = RequestMethod.GET)
    public ModelAndView calculateOdds2(@RequestParam("name1") String name1, @RequestParam("name2") String name2){
        ModelAndView modelAndView = new ModelAndView();

        TeamBo1 teamBo11 = teamService.getByName(name1);
        TeamBo1 teamBo12 = teamService.getByName(name2);

        double odds = 100*teamService.calculateOdds(teamBo12.getDeviation()/173.7178, (teamBo11.getRating()-1500)/173.7178, (teamBo12.getRating()-1500)/173.7178);

        modelAndView.addObject("odds2", String.format("%.2f", odds));
        modelAndView.addObject("successMessage3", "odds calculated successfully");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
