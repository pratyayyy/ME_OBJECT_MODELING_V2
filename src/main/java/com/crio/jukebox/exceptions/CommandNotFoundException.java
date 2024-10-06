package com.crio.jukebox.exceptions;

public class CommandNotFoundException extends RuntimeException{

    public CommandNotFoundException(){
        super();
    }
    
    public CommandNotFoundException(String mssg){
        super(mssg);
    }
}