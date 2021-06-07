package net.juanxxiii.womb.exceptions;

import lombok.NoArgsConstructor;
//Throwed if password can't encrypt
@NoArgsConstructor
public class PasswordMalFormedException extends Exception{
    public PasswordMalFormedException(String error) {
        super(error);
    }
}
