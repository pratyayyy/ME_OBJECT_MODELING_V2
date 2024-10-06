package com.crio.jukebox.services;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.dtos.UserInfoDto;
import com.crio.jukebox.entities.User;

public class UserService implements IUserService{
    
    private final IUserRepository userRepository;
   
    public UserService(IUserRepository userRepository)
    {
      this.userRepository = userRepository;
    }

    public UserInfoDto create(String name)
    {
        User u = new User(name);
        User user= userRepository.save(u);
        UserInfoDto udto = new UserInfoDto(user.getId(), user.getName());
        return udto;
    }

}
