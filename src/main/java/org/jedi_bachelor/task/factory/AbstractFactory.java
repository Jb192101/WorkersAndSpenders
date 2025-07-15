package org.jedi_bachelor.task.factory;

public interface AbstractFactory<T> {
    T create(String name);
}