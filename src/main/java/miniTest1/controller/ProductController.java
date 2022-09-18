package miniTest1.controller;

import miniTest1.model.Product;
import miniTest1.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("productList", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/create", "newProduct", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("newProduct") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("newProduct", new Product());
        modelAndView.addObject("message", "New Product has been added");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable String id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/delete", "delPro", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("delPro") Product product){
        productService.remove(product.getId());
        return "redirect:home";
    }
}
