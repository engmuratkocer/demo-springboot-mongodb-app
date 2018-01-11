package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Car car){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("car");
        modelAndView.addObject("car", car);

        mongoTemplate.save(car);

        return modelAndView;
    }


    @RequestMapping(path="/list", method = RequestMethod.POST)
    public ModelAndView findAll() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("carList");

        List<Car>  carList = mongoTemplate.findAll(Car.class);

        modelAndView.addObject("carList", carList);

        return modelAndView;

    }

}