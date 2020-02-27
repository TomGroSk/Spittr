package pl.gromadzki.spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gromadzki.spittr.model.Spittle;
import pl.gromadzki.spittr.model.User;
import pl.gromadzki.spittr.model.pojo.SpittleToAPI;
import pl.gromadzki.spittr.repository.SpittleRepository;
import pl.gromadzki.spittr.repository.TopSpittlersRepository;
import pl.gromadzki.spittr.repository.UserRepository;
import pl.gromadzki.spittr.service.AdminPanelService;
import pl.gromadzki.spittr.service.TopSpittlersService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/panel")
public class AdminPanelController {
    private final AdminPanelService adminPanelService;
    private final SpittleRepository spittleRepository;
    private final TopSpittlersRepository topSpittlersRepository;
    private final TopSpittlersService topSpittlersService;
    private final UserRepository userRepository;

    public AdminPanelController(SpittleRepository spittleRepository, UserRepository userRepository,
                                AdminPanelService adminPanelService, TopSpittlersRepository topSpittlersRepository,
                                TopSpittlersService topSpittlersService) {
        this.spittleRepository = spittleRepository;
        this.userRepository = userRepository;
        this.adminPanelService = adminPanelService;
        this.topSpittlersRepository = topSpittlersRepository;
        this.topSpittlersService = topSpittlersService;
    }

    @GetMapping
    public String viewAdminPanel(){
        return "adminPanel";
    }

    @GetMapping(value = "/spittle")
    public String spittlesControlPanel(){
        return "spittlesControlPanel";
    }

    @ResponseBody
    @GetMapping(value = "/spittle/showAll")
    public List<SpittleToAPI> showAllSpittles(){
        List<SpittleToAPI> spittleToJsons = new ArrayList<>();
        for(Spittle s: spittleRepository.findAll()){
            spittleToJsons.add(new SpittleToAPI().convertSpittleToJson(s));
        }
        return spittleToJsons;
    }

    @GetMapping(value = "/spittle/showUserSpittle")
    public String showUserSpittle(Model model){
        model.addAttribute("username", "");
        model.addAttribute("usersList", userRepository.findAll());
        return "userSpittlesForm";
    }

    @PostMapping(value = "/spittle/showUserSpittle")
    public String showUserSpittlePostForm(@ModelAttribute("username") String user){
        return "redirect:/panel/spittle/showUserSpittle/" + user;
    }

    @ResponseBody
    @GetMapping(value = "/spittle/showUserSpittle/{user}")
    public List<SpittleToAPI> showUserSpittleGet(@PathVariable("user") String user){
        if(userRepository.findByUsername(user) == null){
            return null;
        }
        return adminPanelService.getAllUserSpittles(user);
    }

    @GetMapping(value = "/spittle/deleteSpittlePanel")
    public String deleteSpittleForm(Model model){
        model.addAttribute("spittleId", "");
        model.addAttribute("spittleList", spittleRepository.findAll());
        return "deleteSpittlePanel";
    }

    @ResponseBody
    @PostMapping(value = "/spittle/deleteSpittlePanel")
    public String deleteSpittle(@ModelAttribute("spittleId") String id){
        try{
            int i = Integer.parseInt(id);
            Spittle spittle = spittleRepository.findById(i);
            if(spittle!=null){
                topSpittlersService.decreaseCountListOfSpittlers(spittle);
                spittleRepository.delete(spittle);
                return "Deleted.";
            }
            else{
                return "Something gone wrong!";
            }
        }catch (NumberFormatException nfe){
            return "Pick number!";
        }
    }

    @GetMapping(value = "/user")    //change role
    public String usersControlPanel(){
        return "usersControlPanel";
    }

    @ResponseBody
    @GetMapping(value = "/user/showUsers")
    public List<User> showUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/deleteUser")
    public String deleteUser(Model model){
        model.addAttribute("username", "");
        model.addAttribute("userList", userRepository.findAll());
        return "deleteUserPanel";
    }

    @ResponseBody
    @PostMapping(value = "/user/deleteUser")
    public String postDelete(@ModelAttribute("username") String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            if(user.getRole().equals("ADMIN")){
                return "You cannot delete admin!";
            }
            adminPanelService.deleteSpittlesFromDeletedUser(user.getUsername());
            topSpittlersRepository.delete(topSpittlersRepository.findByName(user.getUsername()));
            userRepository.delete(user);
            return "Deleted.";
        }
        else{
            return "Something gone wrong!";
        }
    }

    @GetMapping(value = "/user/changeUserRole")
    public String changeUserRole(Model model){
        model.addAttribute("username", "");
        model.addAttribute("userRole", "");
        model.addAttribute("userList", userRepository.findAll());
        return "changeUserRole";
    }

    @ResponseBody
    @PostMapping(value = "/user/changeUserRole")
    public String changeUserRolePost(@ModelAttribute("username") String username, @ModelAttribute("userRole") String userRole){
        if(username.equals("admin")){
            return "You cannot change admin role!";
        }
        User user = userRepository.findByUsername(username);
        if(user!=null){
            user.setRole(userRole);
            userRepository.save(user);
            return "Role has been changed.";
        }
        else{
            return "Something gone wrong!";
        }
    }
}
