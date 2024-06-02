package com.develhope.spring.validators;

import com.develhope.spring.entities.Course;
import com.develhope.spring.entities.User;
import org.springframework.stereotype.Component;

@Component
public class IscrizioneValidator {

    public boolean isIscrizioneValid(User u, Course c) {
        return (isCourseNotNull(c)&&isUserNotNull(u));
    }

    private boolean isUserNotNull(User u) {
        return u != null;
    }
    private boolean isCourseNotNull(Course c) {
        return c != null;
    }

}
