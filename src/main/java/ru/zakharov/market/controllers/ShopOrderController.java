package ru.zakharov.market.controllers;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ru.zakharov.market.entities.ShopOrder;
import ru.zakharov.market.entities.User;
import ru.zakharov.market.searches.ProductSearch;
import ru.zakharov.market.services.EmailService;
import ru.zakharov.market.services.ShopOrderService;
import ru.zakharov.market.services.UserService;
import ru.zakharov.market.utils.Cart;

import javax.mail.MessagingException;
import java.io.IOException;


@Controller
@RequestMapping("/order")
@SessionAttributes("shoppingCart")
public class ShopOrderController {
    private ShopOrderService shopOrderService;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setShopOrderService(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByUserName(userDetails.getUsername());
    }

    @ModelAttribute(name = "shoppingCart")
    public Cart cart() {
        return new Cart();
    }

    @RequestMapping("/create")
    public String fillOrder(Model model, @ModelAttribute("shoppingCart") Cart cart) {
        ShopOrder shopOrder = shopOrderService.makeOrder(cart, getUser());
        model.addAttribute("shopOrder", shopOrder);
        model.addAttribute("search", new ProductSearch());
        return "fillorder";
    }

    @RequestMapping("/save")
    public String saveOrder(Model model, ShopOrder shopOrder) {
        model.addAttribute("search", new ProductSearch());
        System.out.println(shopOrder);
        shopOrder.setUser(getUser());
        shopOrder = shopOrderService.save(shopOrder);
        ShopOrder theShopOrder = shopOrderService.getShopOrderById(shopOrder.getId());
        model.addAttribute("shopOrder", theShopOrder);
        try {
            emailService.sendSimpleMessage("vh1@mail.ru", theShopOrder);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "ordercomplete";
    }
}
