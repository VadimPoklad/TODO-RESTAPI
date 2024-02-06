package com.petproject.todo.service.serv;


import com.petproject.todo.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Stream;

public interface SingleResult {
    static  <S> S getSingleResult(Stream<S> stream){
        List<S> list = stream.toList();
        if(list.isEmpty()) throw new EntityNotFoundException(new Exception());
        return list.get(0);
    }
}
