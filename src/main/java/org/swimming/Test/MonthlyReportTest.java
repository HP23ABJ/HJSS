package org.swimming.Test;

import org.junit.Test;
import org.swimming.domain.Coach;
import org.swimming.domain.Learner;
import org.swimming.domain.Lesson;
import org.swimming.domain.Timetable;
import org.swimming.ui.HomeUi;
import org.swimming.ui.MonthlyLearnerReport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonthlyReportTest {

    @Test
    public void MonthlyReportTest() throws ParseException {
        Timetable timetable = new Timetable();
        HashMap<String, Learner> learners = new HashMap<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        HashMap<String,Coach> coaches = new HashMap<>();

        HomeUi.initializeData(learners,timetable,lessons,coaches);
        MonthlyLearnerReport monthlyLearnerReport = new MonthlyLearnerReport(timetable,learners,lessons,coaches);
        Learner selectedLearner = mock(Learner.class);
        Integer selectedMonth = 4;

        Map<String, List<Lesson>> lessonStatus = new HashMap<>();
        Lesson lesson1 = mock(Lesson.class);
        when(lesson1.getId()).thenReturn(1);
        when(lesson1.getGradeLevel()).thenReturn(5);
        when(lesson1.getTime()).thenReturn("10:00 AM");
        when(lesson1.getDay()).thenReturn("Monday");
        when(lesson1.getDate()).thenReturn("2024-04-01");
        when(lesson1.getCoach()).thenReturn(mock(Coach.class));
        List<Lesson> lessonsList = new ArrayList<>();
        lessonsList.add(lesson1);
        lessonStatus.put("Booked", lessonsList);
        when(selectedLearner.getLessonStatus()).thenReturn(lessonStatus);

        Object[][] data =monthlyLearnerReport.prepareLessonData(selectedLearner,selectedMonth);
        assertEquals(1, data.length);
        assertEquals("1", data[0][0]);
        assertEquals(5, data[0][1]);
        assertEquals("10:00 AM", data[0][2]);
        assertEquals("Monday", data[0][3]);
        assertEquals("2024-04-01", data[0][4]);

    }

}
