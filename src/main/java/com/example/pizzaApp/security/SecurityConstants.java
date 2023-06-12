package com.example.pizzaApp.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "wUKDCI84McUk+78uy7ws3wZG1LEiZ3AdtWh4DTZLV5L1Q9/QGTYWNw03cekGG9FDSvG3LeB0XNLV+FWSCKjFlx/Z3HNJFHdXNDh7ovWth1Q="; //Secret should always be strong (uppercase, lowercase, numbers, symbols) so that nobody can potentially decode the signature.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
}
