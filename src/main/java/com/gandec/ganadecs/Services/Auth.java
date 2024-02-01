package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.security.Login;
import com.gandec.ganadecs.DTO.security.ResponseJwt;
import com.gandec.ganadecs.DTO.security.Signup;

public interface Auth {
    ResponseJwt login(Login login);
    String signup(Signup signup);
}
