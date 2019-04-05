package hello;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import hello.clases.Usuario;


@Controller
public class GreetingController {
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	model.addAttribute("name", name);
    	 model.addAttribute("usuario", new Usuario());
    	return "greeting";
    }
    
    
    @GetMapping("/empform")
    public String empForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "empform";
    }

   @PostMapping("/empform")
    public String empSubmit(@ModelAttribute Customer customer,Model model) {
	  CustomDao dao = new CustomDao();
	   System.out.println("controller customer "+customer.getName());
	   dao.save(customer);
	   
	   List<Customer> listContact = dao.getCustomers();
	   
	   System.out.println("customers : "+ listContact.size());
		for (int i =0; i< listContact.size(); i++)
			System.out.println("id : "+listContact.get(i).getId() + " NAME: "+listContact.get(i).getName() +" E-MAIL :"+ listContact.get(i).getEmail());
	   
   	   model.addAttribute("customers", listContact);
	   
	   return "viewlist";
    }
	  
   
	@GetMapping("/editemp")
	public String edit(HttpServletRequest request, Model model){
		 int id = Integer.parseInt(request.getParameter("id"));
		 System.out.println("request id " + id);
		  CustomDao dao = new CustomDao();
		  Customer customer = new Customer();
		  customer = dao.getEmpById(id);
		  System.out.println("name ->>_"+customer.getName());
		  model.addAttribute("customer",customer);
		return "empeditform";
	}
	
	
	@PostMapping("/editsave")
	public String editsave(@ModelAttribute Customer cust, Model model){
		  CustomDao dao = new CustomDao();
		  dao.update(cust);
		  List<Customer> listContact = dao.getCustomers();
		  model.addAttribute("customers", listContact);
		return "/viewlist";
	}
	
	
	@GetMapping("/deleteemp")
	public String delete(HttpServletRequest request, Model model){
		 int id = Integer.parseInt(request.getParameter("id"));
		 System.out.println("request id " + id);
		 CustomDao dao = new CustomDao();
		 dao.delete(id);
		 List<Customer> listContact = dao.getCustomers();
		 model.addAttribute("customers", listContact);
		 
		return "viewlist";
	}
	
  
   @GetMapping("/viewlist")
   public String listCustom(Model model) {
   	
	   CustomDao dao = new CustomDao();
	   List<Customer> listContact = dao.getCustomers();   
   	   model.addAttribute("customers", listContact);
   
   	JSONObject jo = new JSONObject();
   	jo.put("name", "jon doe");
   	jo.put("age", "22");
   	jo.put("city", "chicago");
   	
   	System.out.println("jo ->>>_"+jo);
   	   
   	   
       return "viewlist";
   }
   
	//https://www.codejava.net/frameworks/spring/spring-mvc-with-jdbctemplate-example
}
