package hello.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.clases.Age;
import hello.clases.AjaxResponseBody;
import hello.clases.Bitacora;
import hello.clases.Diabetes;
import hello.clases.DiabetesType;
import hello.clases.InsulinaType;
import hello.clases.Usuario;
import hello.dao.UsuarioDAO;


@Controller
public class UserController {
		
    @GetMapping("/registrar")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //model.addAttribute("name", name);
    	 UsuarioDAO dao = new UsuarioDAO();
 	   List<DiabetesType> listaDiabetes = new ArrayList<DiabetesType>();
       listaDiabetes = dao.getDiabetesT();
       model.addAttribute("diabetype", listaDiabetes);
    	
        model.addAttribute("usuario", new Usuario());
        return "registrar";
    }
    
    @PostMapping("/registrar")
    public String empSubmit(@ModelAttribute Usuario user,Model model) {
    	 UsuarioDAO dao = new UsuarioDAO();
	   System.out.println("controller usuario "+user.getIdusuario());
	      
	   try {
		   Usuario userRegistered = new Usuario();
		   userRegistered = dao.save(user);
		   System.out.println("usuario ->>>_" +userRegistered.getUsuario() +" has been sucessfully saved!");
		   
		   id = userRegistered.getIdusuario();
	    	System.out.println("new id ->>>_"+ id);
		   //String nombre = dao.getUserName(id);
		   //System.out.println("id ->>>_"+id + " nombre ->>>_"+nombre);
		  
		   //userRegistered.setUsuario(nombre); 
		   model.addAttribute("usuario", userRegistered);
		   
		   
		  // model.addAttribute("usuario", user);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
	}
	   

	   
	   
	   
	   /*  List<Customer> listContact = dao.getCustomers();
	   
	   System.out.println("customers : "+ listContact.size());
		for (int i =0; i< listContact.size(); i++)
			System.out.println("id : "+listContact.get(i).getId() + " NAME: "+listContact.get(i).getName() +" E-MAIL :"+ listContact.get(i).getEmail());
	   
   	   model.addAttribute("customers", listContact);
	   */
	   return "menu";
    }
    
    
    @GetMapping("/food")
    public String food_beverage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //model.addAttribute("name", name);

        return "food";
    }
    
    
    
    @GetMapping("/bitacora")
    public String bitacora(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //model.addAttribute("name", name);
    	UsuarioDAO dao = new UsuarioDAO();
  	   List<InsulinaType> listaInsuline = new ArrayList<InsulinaType>();
  	   listaInsuline = dao.getInsuline();
       model.addAttribute("insulinetype", listaInsuline);
        model.addAttribute("bitacora", new Bitacora());
        return "bitacora";
    }
    
    
    @PostMapping("/bitacora")
    public String empBitacora(@ModelAttribute Bitacora bita,Model model) {
    	 UsuarioDAO dao = new UsuarioDAO();
    	 System.out.println("bitacota -->>> id -->>>" + id);
    //	 if (bita.getIdregistro()>0) {
			   System.out.println("controller bitacora registro "+bita.getIdregistro());
			   
			   try {
				   dao.saveBitac(bita,id);
				   System.out.println("bitacora reg ->>>_" +bita.getIdregistro() +" has been sucessfully saved!");
				   
				   model.addAttribute("bitacora", bita);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
	   
    //	 }
	   /*  List<Customer> listContact = dao.getCustomers();
	   
	   System.out.println("customers : "+ listContact.size());
		for (int i =0; i< listContact.size(); i++)
			System.out.println("id : "+listContact.get(i).getId() + " NAME: "+listContact.get(i).getName() +" E-MAIL :"+ listContact.get(i).getEmail());
	   
   	   model.addAttribute("customers", listContact);
	   */
	   return "redirect:menu";
    }
    
    
    
    @GetMapping("/menu")
    public String menu(@RequestParam(name="name", required=false, defaultValue="World") String usuario, Model model) {
        //model.addAttribute("name", name);
    	/*System.out.println("usuario ->>>_"+usuario);
    	Usuario user = new Usuario();
    	user.setUsuario(usuario);    	
        model.addAttribute("usuario", user);*/
    	System.out.println("usuario ->>>_"+usuario);
    	UsuarioDAO dao = new UsuarioDAO();
    	String nombre = dao.getUserName(id);
    	System.out.println("id ->>>_"+id + " nombre ->>>_"+nombre);
    	Usuario user = new Usuario();
    	user.setUsuario(nombre); 
    	model.addAttribute("usuario", user);
    	
        return "menu";
    }
    
    
    
    @GetMapping("/login")
    public String longin(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model,@ModelAttribute Usuario user) {
        //model.addAttribute("name", name);

      //  model.addAttribute("usuario", user);
        return "login";
    }
    
    
    int id=0;
	@PostMapping("/checkUsr")
	@ResponseBody
	public AjaxResponseBody insertMethod(@RequestBody Map<?, ?> params) {
		
		AjaxResponseBody result = new AjaxResponseBody();
		 UsuarioDAO dao = new UsuarioDAO();
		System.out.println("param USUARIO: "+params.get("user"));
		System.out.println("param PASSWORD :"+params.get("pass"));
		boolean exists = dao.isUserExists(params.get("user").toString(), params.get("pass").toString());
		System.out.println(exists);
		
		if (exists) {
			result.setMsg("exists");
			id = dao.getUserId(params.get("user").toString(), params.get("pass").toString());
			System.out.println("id ->>> "+ id);
		}else
			result.setMsg("error");
	
			//result.setError("");
			//result.setObj(params);
			//result.setMsg("OK");
			//result.setStatus("completed");
		
		
		
		return result;
		
	}
    
	
	@PostMapping("/dateFilter")
	@ResponseBody
	public AjaxResponseBody dateFilter(@RequestBody Map<?, ?> params) {
		
	//	System.out.println("user ->>_"+params.get("nombreUsr").toString());
		System.out.println("date ->>_"+params.get("datepicker").toString());
		System.out.println("nombre ->>_"+nombre);
		dateFilter = params.get("datepicker").toString();
		AjaxResponseBody result = new AjaxResponseBody();
		 UsuarioDAO dao = new UsuarioDAO();
		 dao.getBitaRegisters(nombre, params.get("datepicker").toString());
		 result.setMsg("ok");
		
	return result;
	}
	
	
	String nombre="",dateFilter="";
    @GetMapping("/history")
    public String history(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //model.addAttribute("name", name);

    	
    	UsuarioDAO dao = new UsuarioDAO();
    	nombre = dao.getUserName(id);
    	System.out.println("id ->>>_"+id + " nombre ->>>_"+nombre);
    	
        model.addAttribute("usuario", nombre);
        List<Bitacora> listaRegis = new ArrayList<Bitacora>();
        listaRegis = dao.getBitaRegisters(nombre, dateFilter);
        model.addAttribute("registro", listaRegis);
        return "history";
    }
	
	
    @GetMapping("/statistics")
    public String stadistics(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //model.addAttribute("name", name);
    	 UsuarioDAO dao = new UsuarioDAO();
    	 int women = dao.countPeople("f");
    	 int men = dao.countPeople("m");
        model.addAttribute("mujeres",women);
        model.addAttribute("hombres",men);
        List<Age> listaRanges = new ArrayList<Age>();
        listaRanges = dao.getAgeRanges();
        model.addAttribute("agerange", listaRanges);
        List<Diabetes> listaDiabetes = new ArrayList<Diabetes>();
        listaDiabetes = dao.getDiabetesType();
        model.addAttribute("diabetype", listaDiabetes);
       
        List<Diabetes> listaDiabetesStat = new ArrayList<Diabetes>();
        listaDiabetesStat = dao.getDiabetesTypeStat();
        model.addAttribute("diabetesDes", listaDiabetesStat);
        return "statistics";
    }
	
    
}
