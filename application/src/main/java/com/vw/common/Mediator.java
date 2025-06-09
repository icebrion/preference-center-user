package com.vw.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Mediator {

    private final Map<Class<?>, Handler<?, ?>> handlers;

    @Autowired
    public Mediator(List<Handler<?, ?>> handlerList) {
        handlers = new HashMap<>();
        for (Handler<?, ?> handler : handlerList) {
            Class<?> requestType = (Class<?>) ((ParameterizedType) handler.getClass()
                    .getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];
            handlers.put(requestType, handler);
        }
    }

    @SuppressWarnings("unchecked")
    public <R, T extends Request<R>> R handle(T request) {
        Handler<T, R> handler = (Handler<T, R>) handlers.get(request.getClass());
        if (handler == null) {
            throw new IllegalStateException("No handler found for " + request.getClass().getName());
        }
        return handler.handle(request);
    }
}


