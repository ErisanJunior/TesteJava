package com.apispring.testeapi.Utils;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Exceptions {

    public static ResponseEntity returnErro(String mensagem){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson(mensagem));
    }
}