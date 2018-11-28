package ru.zakharov.market.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.zakharov.market.entities.Product;

@Aspect
@Component
public class AppLoggingAspect {

    @Before("execution(public void ru.zakharov.market.services.ProductService.saveProduct(..))")
    public void beforeProductCreateOrSave(JoinPoint joinPoint) {
        Product product = (Product) joinPoint.getArgs()[0];
        if (product == null) return;
        System.out.println("Сохранение продукта / Создание продукта - " + product.getTitle());
    }

    @After("execution(public * ru.zakharov.market.services.UserServiceImpl.findByUserName(..))")
    public void beforeFindUser(JoinPoint joinPoint) {
        System.out.println("Поиск пользователя по имени :" + joinPoint.getArgs()[0]);
    }
}
