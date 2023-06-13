//package org.project.service.implementation;
//
//import org.project.dao.UserDAO;
//import org.project.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//    private final UserDAO userDAO;
//
//    @Autowired
//    public UserDetailServiceImpl(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userDAO.getUsername(s);
//        if (user == null)
//            throw new UsernameNotFoundException("User not found");
//        return new org.project.service.UserDetails(user);
//    }
//}