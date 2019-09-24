package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.*;

@org.springframework.stereotype.Controller

public class Controller {
	@RequestMapping("/")
	public String execute() {
		return"loginPage";    
	}
	
	@RequestMapping("assignments")
	public String asg() {
		return"assignments";
	}
	
	@RequestMapping("moveEditAssignment")
	public String moveEditAsg(int asgId, HttpSession session) {
		session.setAttribute("asgId", asgId);
		return "editAssignment";
	}
	
	@RequestMapping("returnLogin")
	public String returnLogin() {
		return"redirect:/";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String LogIn(User user, HttpSession session) {
		String s = new String();
		DAO dao = new DAO();
		List<User> userList = dao.getUsers();
		
		List<String> passwords = new ArrayList<>();
		List<String> usernames = new ArrayList<>();

		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		for (User u : userList) {
			usernames.add(u.getUsername());
			passwords.add(u.getPassword());
		}
		
		if(usernames.contains(user.getUsername()) && passwords.contains(user.getPassword())) {
			System.out.println("welcome!!");
			
			s = "redirect:assignments";
			user.setId(Integer.parseInt(dao.getId(user)));
			session.setAttribute("userId", user.getId());
		}
		else {
			s = "wrongUserOrPassword";
		}
		
		return s;
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(String username, String password1, String password2) throws IOException {
		
		DAO dao = new DAO();
		
		System.out.println(username);
		System.out.println(password1);
		System.out.println(password2);
		
		if (!password1.equals(password2)) {
			return "differentPasswords";
		}
		else if (password1.length() < 6) {
			return "shortPassword";
		}
		else if (username == null || username.isEmpty()) {
			return "noUsername";
		}
		else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password1);
			dao.addUser(user);
			return"redirect:/";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="AddAssignment", method=RequestMethod.POST)
	public String addAsg(String sub, String note, String date, int userId, HttpSession session) {
		Assignment asg = new Assignment();
		asg.setSub(sub);
		asg.setNote(note);
		asg.setDate(date);
		asg.setUserId(userId);
		
		DAO dao = new DAO();
		
		dao.createAssignment(asg);
		
		return "redirect:assignments";
	}
	
	
	@RequestMapping(value = "RemoveAssignment", method = RequestMethod.POST)
	public String rmvAsg(int asgId, HttpSession session) throws IOException{
		
		DAO dao = new DAO();
		dao.removeAssignment(asgId);
		
		return "redirect:assignments";
	}
	
	@RequestMapping(value = "editAsg", method = RequestMethod.POST)
	public String editAsg(int asgId, String note, String date, HttpSession session) throws IOException{
		DAO dao = new DAO();
		Assignment asg = new Assignment();
		asg.setNote(note);
		asg.setDate(date);
		asg.setId(asgId);
		dao.editAssginment(asg);
		
		return "redirect:assignments";	
	}
	
}










