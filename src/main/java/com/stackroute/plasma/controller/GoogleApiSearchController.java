package com.stackroute.plasma.controller;

import com.stackroute.plasma.model.Api;
import com.stackroute.plasma.service.GoogleApiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class GoogleApiSearchController {
    @Autowired
    GoogleApiSearchService googleApiSearchService;
    Api api = new Api();
    String newConcepts[] = api.getConcepts();

    List<String[]> list ;
    //int j=0;
    //public String[] result;
    private int initial;
    private int finall;
//    @GetMapping("/search")
//    public List<String[]> getApi(){
//        for(int i=0;i<2;i++){
//            String[] result;
//            result = googleApiSearchService.read(api.getDomain()+newConcepts[j++],1,10);
//            list.add(result);
//        }
//        return list;
//    }


    @PostMapping(value = "/search")
    public ResponseEntity<?> getPostApi(@RequestBody Api api)  {
        list = new ArrayList();
        ResponseEntity responseEntity;
        int j=0;
        for(int i=0;i<api.getConcepts().length;i++){
            String[] result;
            //int j=0;
            result = googleApiSearchService.read(api.getDomain() + api.getConcepts()[j++],1,10);
            list.add(result);
        }
        //googleApiSearchService.read(api.getDomain() + api.getConcepts()[j],1,10);
        //trackService.saveTrack(track);
        responseEntity = new ResponseEntity<List<String[]>>(list, HttpStatus.CREATED);

        return responseEntity;
    }
}
