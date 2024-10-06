package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.dtos.UserInfoDto;

public class CreateUserCommand implements ICommand {

    private final IUserService userService;

    public CreateUserCommand(IUserService userService)
    {
        this.userService = userService;
    }


    @Override
    public void execute(List<String> tokens) 
    {
        String name = tokens.get(1);
        UserInfoDto u = userService.create(name);
        System.out.println(u.getId() + " " + u.getName());
    }
}
    

