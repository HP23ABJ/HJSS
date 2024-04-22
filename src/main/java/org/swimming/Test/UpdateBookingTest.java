package org.swimming.Test;

import org.junit.Test;
import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.ui.UpdateBookingUi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class UpdateBookingTest {

    @Test
    public void testBookLesson() {
        // Create necessary objects and dependencies
        UpdateBookingUi updatebookingUi = new UpdateBookingUi(new Timetable(),
                new Learner(),
                new HashMap<String,Learner>(),
                1, new ArrayList<Lesson>(),new HashMap<String,Coach>());
        String selectedDate = "2024-04-22";
        int selectedGrade = 5;
        Learner selectedLearner = mock(Learner.class);
        Lesson selectedLesson = mock(Lesson.class);

        Timetable timetableMock = mock(Timetable.class);
        when(updatebookingUi.timetable).thenReturn(timetableMock);

        Map<String, Lesson> lessons = new HashMap<>();
        lessons.put("lessonId", selectedLesson);
        when(timetableMock.getLessons(selectedDate)).thenReturn(lessons);


        updatebookingUi.bookLesson(selectedDate, selectedGrade, selectedLearner);

        verify(selectedLesson).addLearner(selectedLearner);
        verify(selectedLearner).addLesson(eq("Booked"), any(Lesson.class));
    }
}
