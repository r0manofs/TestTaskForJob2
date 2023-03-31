package ru.romanov.StoreTZ.util;

public class EntityNotCreatedException extends RuntimeException{
    public EntityNotCreatedException(String message){
        super(message);
    }
}