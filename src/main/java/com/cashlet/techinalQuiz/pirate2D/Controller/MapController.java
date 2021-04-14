package com.cashlet.techinalQuiz.pirate2D.Controller;

import com.cashlet.techinalQuiz.pirate2D.Model.Coordinate;
import com.cashlet.techinalQuiz.pirate2D.Model.Request;
import com.cashlet.techinalQuiz.pirate2D.Utils.Game2DPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RestController()
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);
    Object obj = new Object();
    @PostMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> MapItem(@RequestBody Object request[][]){
        logger.info(String.valueOf(request.length));
        obj = request;
        return ResponseEntity.accepted().body("");
    }

    @GetMapping(value = "/findPath", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object GetItem(@RequestParam("startXPosition") int startXPosition,
                                        @RequestParam("startYPosition") int startYPosition,
                                        @RequestParam("targetXPosition") int targetXPosition,
                                        @RequestParam("targetYPosition") int targetYPosition){

        Object data[][] = (Object[][]) obj;
        Coordinate startCoordinate = new Coordinate(startXPosition,startYPosition);
        Coordinate endCoordinate = new Coordinate(targetXPosition,targetYPosition);
        Object response[] = {};
//        for (int a = 0; a <= data.length; a ++) {
//            for (int i = targetXPosition; i <= a; i++) {
//                for (int j = a; j <= targetYPosition; j++) {
//                    System.out.println("======================");
//                    System.out.println("Index J: " + j);
//                    System.out.println("Index I: " + i);
//                    System.out.println(data[i][j]);
////                    respone[]
//                    System.out.println("======================");
//                }
//            }
//        }
//        System.out.println(data.get(0).get(0));
//        System.out.println(data.get(1).get(0));
        Game2DPath game2DPath = new Game2DPath();
        game2DPath.Init(data, startCoordinate, endCoordinate);
        return data;
    }
}