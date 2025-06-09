package com.vw.common;

public interface Handler<T extends Request<R>, R> {
    R handle(T request);
}
