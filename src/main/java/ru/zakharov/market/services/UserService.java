package ru.zakharov.market.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.zakharov.market.entities.SystemUser;
import ru.zakharov.market.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}
