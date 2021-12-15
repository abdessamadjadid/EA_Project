package edu.miu.cs.cs544.EAProject.advice;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("No registration Event found.");
    }
}
