package org.swimming.Test;

import org.junit.jupiter.api.Test;
import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.ui.AttendClassUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttendClassTest {

    @Test
    public void testAttendClass() {
        Learner selectedLearner = mock(Learner.class);
        Lesson selectedLesson = mock(Lesson.class);
        Coach coach = mock(Coach.class);

        when(selectedLesson.getDate()).thenReturn(new Date());
        when(selectedLesson.getCoach()).thenReturn(coach);

        AttendClassUI attendClassUI = new AttendClassUI();
        attendClassUI.attendClass(selectedLearner, selectedLesson);

        // Verify that the learner's lesson status has been updated to "Attended"
        assertEquals("Attended", selectedLearner.getLessonStatus());

        int expectedRating = 5;
        assertEquals(expectedRating, coach.getRatings());

    }
}
