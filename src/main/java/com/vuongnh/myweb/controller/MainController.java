package com.vuongnh.myweb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vuongnh.myweb.entity.Item;
import com.vuongnh.myweb.entity.Member;
import com.vuongnh.myweb.entity.Product;
import com.vuongnh.myweb.service.CartService;
import com.vuongnh.myweb.service.ProductService;
import com.vuongnh.myweb.service.SignUpService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/")
	public String trangchu(Model model) {
		
		model.addAttribute("Products", productService.findAll());
		return "trangchu";
	}
	
	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("Products", productService.findAll());
		return "adminPage";
	}
	
	@GetMapping("/admin/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		return "formAddProduct";
	}
	
	@PostMapping("/admin/save")
	public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			return "formAddProduct";
		}
		product.setUpLoadDate(new Date());
		productService.save(product);
		redirect.addFlashAttribute("successMessage", "Add new Product Sucessfully");
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/{pid}/edit")
	public String edit(@PathVariable("pid") Long pid, Model model) {
		model.addAttribute("product", productService.findOne(pid));
		return "formAddProduct";
	}
	
	@GetMapping("/admin/{pid}/delete")
	public String delete(@PathVariable("pid") Long pid, RedirectAttributes redirect) {
		productService.delete(pid);
		redirect.addFlashAttribute("successMessage","Delete Product SuccessFul");
		return "redirect:/admin";
	}
	@GetMapping("/admin/search")
	public String search(@RequestParam(name="productName") String pname, Model model) {
		if(StringUtils.isEmpty(pname)) {
			return "redirect:/admin";
		}
		model.addAttribute("Products", productService.search(pname));
		return "adminPage";
	}
	
	/*==================================Spring Security==================================*/
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("member", new Member());
		return "signup";
	}
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    redirect.addFlashAttribute("successMessage", "Đăng Xuất Thành Công");
	    return "redirect:/";
	}
	
	@PostMapping("/register/submit")
	public String submitSignUp(@Valid Member member,BindingResult result,
			RedirectAttributes redirect) {
		if(result.hasErrors()) {
			return "signup";
		}else {
			if(signUpService.isSignUp(member)==true) {
				
				signUpService.save(member);
				redirect.addFlashAttribute("messageSuccessful", "Tạo tài khoản thành công");
				return "redirect:/login";
				
				
			}else {
				redirect.addFlashAttribute("messageSuccessful", "Tên email đã tồn tại ");
				return "signup";
			}
		}
		
		
	}
	
	
	/*===================================Cart Shopping===============================================*/
	
	@RequestMapping("/cartShopping")
	public String cart() {
		return "cart";
	}
	
	@RequestMapping(value="/cartShopping/{pid}/addToCart", method=RequestMethod.GET)
	public String addtocart(@PathVariable("pid") Long pid, HttpSession session) {
		List<Item> cart;
		if(session.getAttribute("cart")==null) {
			cart= new ArrayList<>();
			cart.add(new Item(productService.findOne(pid),1));
			session.setAttribute("cart", cart);
		}else {
			cart =(List<Item>) session.getAttribute("cart");
			int index=cartService.isExists(pid, cart);
			if(index==-1) {
				cart.add(new Item(productService.findOne(pid),1));
			}else {
				int Iquantity= cart.get(index).getIquantity() + 1;
				cart.get(index).setIquantity(Iquantity);
			}
			session.setAttribute("cart", cart);
		}
		session.setAttribute("tong", cartService.tinhtong(cart));
		return "redirect:/cartShopping";
	}
	
	@RequestMapping(value="/cartShopping/{pid}/dropFromCart", method=RequestMethod.GET)
	public String dropFromCart(@PathVariable("pid") Long pid, HttpSession session) {
		List<Item> cart= (List<Item>) session.getAttribute("cart");
		cartService.dropFromCart(pid, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("tong", cartService.tinhtong(cart));
		return "redirect:/cartShopping";
	}
}
