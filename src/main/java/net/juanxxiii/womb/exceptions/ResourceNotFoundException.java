package net.juanxxiii.womb.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String error) {
        super(error);
    }
}
