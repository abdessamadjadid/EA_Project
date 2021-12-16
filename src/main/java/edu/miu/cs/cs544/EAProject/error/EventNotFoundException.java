package edu.miu.cs.cs544.EAProject.error;

import edu.miu.cs.cs544.EAProject.i18n.DefaultMessageSource;
import org.springframework.context.support.MessageSourceAccessor;

public class EventNotFoundException extends RuntimeException {

    private static final MessageSourceAccessor messages = DefaultMessageSource.getAccessor();

    public EventNotFoundException() {
        super(messages.getMessage("error.event.notFound"));
    }
}
