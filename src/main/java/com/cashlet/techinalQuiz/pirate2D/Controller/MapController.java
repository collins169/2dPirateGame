package com.cashlet.techinalQuiz.pirate2D.Controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RestController()
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);
    Object obj = new Object();
    @PostMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<Object, Object>> MapItem(@RequestBody Object request[][]){
        obj = request;
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "Map added successfully");
        response.put("success", true);
        return ResponseEntity.accepted().body(response);
    }

    @GetMapping(value = "/findPath", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> GetItem(@RequestParam("startXPosition") int startXPosition,
                                        @RequestParam("startYPosition") int startYPosition,
                                        @RequestParam("targetXPosition") int targetXPosition,
                                        @RequestParam("targetYPosition") int targetYPosition){

        Map<String, Object> response = new HashMap<>();
        try {
            //Assign save map and cast to 2d array object
            Object data[][] = (Object[][]) obj;
            Set<ArrayList<Integer>> paths = new HashSet<>();
            Set<Object> items = new HashSet<>();
            int TotalAmount = 0;
            //Let loop through the columns
            for (int i = 0; i < data.length; i++) {
                //let loop through the rows
                for (int j = 0; j < data[i].length; j++) {
                    //let check if the position is lesser than or equals to the target positions
                    if (targetXPosition <= i && targetYPosition >= i) {
                        ArrayList<Integer> patha = new ArrayList<>();
                        logger.info(data[i][j] + " ");
                        logger.info("point [{}, {}]", targetXPosition - i, targetYPosition - j);
                        patha.add(i);
                        patha.add(j);
                        paths.add(patha);
                        //let store item in a set list to prevent duplication
                        items.add(data[i][j]);
                    }
                    if (targetXPosition <= j && targetYPosition >= j) {
                        ArrayList<Integer> addPath = new ArrayList<>();
                        logger.info(data[i][j] + " ");
                        logger.info("point [{}, {}]", targetXPosition - i, targetYPosition - j);
                        addPath.add(i);
                        addPath.add(j);
                        paths.add(addPath);
                        //let store item in a set list to prevent duplication
                        items.add(data[i][j]);
                    }
                }
            }
            //Let calculate the total coins
            for (Object obj : items) {
                Map<Map<Object, Object>, Map<Object, Object>> newArry2 = (Map<Map<Object, Object>, Map<Object, Object>>) obj;
                if (newArry2.containsValue("coin")) {
                    TotalAmount += Integer.parseInt(String.valueOf(newArry2.get("amount")));
                }
            }
            ;
            logger.info("Total Amount: {}", TotalAmount);
            logger.info("All Paths: {}", new Gson().toJson(paths));
            logger.info("All Items: {}", new Gson().toJson(items));
            response.put("paths", paths);
            response.put("coins", TotalAmount);
        }catch(Exception ex){
            response.put("error", ex.getMessage());
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.ok(response);
    }
}