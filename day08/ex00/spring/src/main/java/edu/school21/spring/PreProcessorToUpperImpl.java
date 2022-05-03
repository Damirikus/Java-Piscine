package edu.school21.spring;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String changeRegister(String message) {
        return message.toUpperCase();
    }
}
