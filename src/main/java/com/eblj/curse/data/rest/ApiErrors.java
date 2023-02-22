package com.eblj.curse.data.rest;
import java.util.Arrays;
import java.util.List;


public class ApiErrors {
    private List<String> erros;

    public ApiErrors(List<String> erros){
        this.erros = erros;
    }
    public ApiErrors(String msg){
        this.erros = Arrays.asList(msg);
    }

    public List<String> getErros(){
        return erros;
    }
}
