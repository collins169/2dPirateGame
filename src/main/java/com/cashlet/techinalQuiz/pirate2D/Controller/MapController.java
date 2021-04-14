package com.cashlet.techinalQuiz.pirate2D.Controller;

import com.cashlet.techinalQuiz.pirate2D.Model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController()
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);
    Object obj = new Object();
    @PostMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> MapItem(@RequestBody List<List<Request>> request){
        logger.info(request.toString());
        obj = request;
        return ResponseEntity.accepted().body("");
    }

    @GetMapping(value = "/findPath", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object GetItem(@RequestParam("startXPosition") int startXPosition,
                                        @RequestParam("startYPosition") int startYPosition,
                                        @RequestParam("targetXPosition") int targetXPosition,
                                        @RequestParam("targetYPosition") int targetYPosition){
        //Cast object to list of Request
        List<List<Request>> data = (List<List<Request>>) obj;
//        for (int i = startXPosition; i < data.size(); ++i) {
//            for(int j = targetYPosition; j < data.get(startYPosition).size(); ++j) {
//                System.out.println(data.get(targetXPosition).get(j));
//            }
//        }
        for (int i = data.size() - 1; i >= 0 ; i--) {
            System.out.println(data.get(i));
        }
        return data;
    }
}