package net.juanxxiii.womb.exceptions;

import lombok.NoArgsConstructor;
//Throwed if repositories don't found the resource requested by user
@NoArgsConstructor
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String error) {
        super(error);
    }
}
