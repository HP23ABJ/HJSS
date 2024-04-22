package org.swimming.Test;

import org.junit.jupiter.api.Test;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.ui.BookingUi;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LessonBookingTest {

    @Test
    public void testBookLesson() {
        // Mock objects
        Timetable timetable = mock(Timetable.class);
        Learner learner = mock(Learner.class);
        Lesson lesson = mock(Lesson.class);

        // Set up mock behavior
        when(timetable.getLessons(anyString())).thenReturn(Map.of("lesson1", lesson));
        when(lesson.getGradeLevel()).thenReturn(1);
        when(learner.getGradeLevel()).thenReturn(1);
        when(lesson.addLearner(any())).thenReturn(true);

        BookingUi bookLessonInstance = new BookingUi(timetable,learner);

        bookLessonInstance.bookLesson("2024-04-22", 1, learner);

        verify(lesson, times(1)).addLearner(learner);
    }
}