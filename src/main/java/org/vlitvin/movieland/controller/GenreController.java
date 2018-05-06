package org.vlitvin.movieland.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vlitvin.movieland.model.Genre;
import org.vlitvin.movieland.service.GenreService;

import javax.servlet.GenericServlet;
import java.util.List;

@RestController
@RequestMapping("/v1/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping()
    @ResponseBody
    public List<Genre> getAllGenre(){
        return genreService.getAll();
    }
}
