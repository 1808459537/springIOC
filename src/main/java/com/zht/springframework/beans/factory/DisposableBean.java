package com.zht.springframework.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}
