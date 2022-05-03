package edu.school21.spring;

public class PreProcessorToLowerImpl implements PreProcessor{
    @Override
    public String changeRegister(String message) {
        return message.toLowerCase();
    }
}
