package com.aquaone.srv.login.services;


import com.aquaone.srv.login.domain.user.User;
import com.aquaone.srv.login.domain.user.UserRequestDTO;
import com.aquaone.srv.login.domain.user.UserUpdateDTO;
import com.aquaone.srv.login.repositories.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUsr (UserRequestDTO data){
      

        User newUsr = new User();

        newUsr.setNome(data.nome());
        newUsr.setCargo(data.cargo());
        newUsr.setEmail(data.email());
        newUsr.setDescription(data.description());
        newUsr.setDate(LocalDate.now());
        newUsr.setPassword(passwordEncoder.encode(data.password()));
        newUsr.setAdmin(data.admin());

        return userRepository.save(newUsr);
    }



    public User updateUsr (UUID id, UserUpdateDTO data){
        
        User existiUser = (User) userRepository.findAllById(id);

        existiUser.setNome(data.nome());
        existiUser.setCargo(data.cargo());
        existiUser.setDescription(data.description());
        existiUser.setAdmin(data.admin());
        existiUser.setEmail(data.email());
        existiUser.setPassword(data.password() != null ? passwordEncoder.encode((data.password())) : existiUser.getPassword());
        
        return userRepository.save(existiUser);
    }


   // private String passwordEncoder(String password) {
  //      return new BCryptPasswordEncoder().toString();

   // }

    public List<User> listUser(){
        List<User> users = userRepository.findAll();     
        return users;
    }
    
    public UserDetails findUserById(UUID id){
        return userRepository.findAllById(id);
    }


    @Transactional
    public void deleteUserById(UUID id){
        Optional<User> existUser = userRepository.findById(id);

        if (existUser.isPresent()) {
            User currentUser = existUser.get();
            currentUser.setIcUso(false);
            userRepository.save(currentUser);
        }else{
            throw new RuntimeException("Usuário não encontrado");
        }
       
    }
 
}
