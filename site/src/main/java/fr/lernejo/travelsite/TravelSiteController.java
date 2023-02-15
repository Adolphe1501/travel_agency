package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelSiteController {

    private final List <User> userList = new ArrayList<>();

    @PostMapping("/api/inscription")
    public String registerRecord(@RequestBody User user){
        this.userList.add(user);
        return "User save succesfully";
    }
    @GetMapping("/api/travels")
    public List<String> getRecordsUser(@RequestParam(name = "userName") String userName){
        return  new ArrayList<>();
    }



}
