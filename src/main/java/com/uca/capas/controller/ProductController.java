package com.uca.capas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Product;

@Controller
public class ProductController {

	private List<Product> productos = new ArrayList<Product>();
	
	@GetMapping("/compraProducto")
	public ModelAndView compraProducto() {
		ModelAndView mav = new ModelAndView();
		
		productos.add(new Product(0, "Fresa", 10));
		productos.add(new Product(1, "Naranja", 20));
		productos.add(new Product(2, "Manzana", 30));
		productos.add(new Product(3, "Banana", 40));
		productos.add(new Product(4, "Sandia", 50));
		
		mav.setViewName("productos");
		mav.addObject("producto", new Product());
		mav.addObject("productos", productos);
		
		return mav;
	}

	
	@PostMapping("/validar")
	@ResponseBody
	public ModelAndView validar(Product product) {
		ModelAndView mav = new ModelAndView();

		Integer compra = productos.get(product.getId()).getCantidad() - product.getCantidad();		
		if (compra < 0) mav.setViewName("error");
		else mav.setViewName("compra");
		
		mav.addObject("producto", productos.get(product.getId()).getNombre());
		
		return mav;
	}
}
