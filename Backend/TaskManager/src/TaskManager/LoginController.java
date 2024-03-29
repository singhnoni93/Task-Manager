package TaskManager;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Login")
@SessionAttributes("personObj")

public class LoginController {
	
	@RequestMapping("/showLogin")
	public String showLogin() {
		return "loginPage";
	}
	@RequestMapping("/processLogin")
	public String processLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("company") String company_name,
			Model model, HttpSession session) {
		String data[] = {username, password,company_name};
		LoginHandler lh = new LoginHandler(data);
		User user = lh.logInUser(model);
		//String name, String workPhone, String workEmail, String location, String username,
		//String title, String department
		user = new Admin("Akshar Patel", "6692418220", "akshar@ecs.csus.edu", "E240", "akshar24", "Software Developer","Software", "Google");
		
		if(user != null) {
			session.setAttribute("User", user);
			String role = user.getRole();
			if(role.equals("Admin")) {
				return "adminDash";
			}else if(role.equals("Manager")) {
				return "managerDash";
			}else if(role.equals("teamuser")) {
				return "userDash";
			}else {
				return "loginPage";
			}
		}else {
			return "loginPage";
		}
		
		
		
	}
}
