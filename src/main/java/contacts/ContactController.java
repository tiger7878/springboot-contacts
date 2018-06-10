package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author: monkey
 * @date: 2018/6/10 10:43
 */
@Controller
@RequestMapping("/")
public class ContactController {

    @Autowired
    private ContactsRepository contactsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String,Object> model){
        List<Contact> contacts = contactsRepository.findAll();
        model.put("contacts",contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact){
        contactsRepository.save(contact);
        return "redirect:/";
    }

}
