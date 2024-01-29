package com.petproject.todo.service.serv;


import java.util.List;
import java.util.stream.Stream;

public interface SingleResult {
    static  <S> S getSingleResult(Stream<S> stream){
        List<S> list = stream.toList();
        if(list.isEmpty()) throw new RuntimeException();
        return list.get(0);
    }
}
