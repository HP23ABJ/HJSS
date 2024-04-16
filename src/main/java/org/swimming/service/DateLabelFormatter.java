package org.swimming.service;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        Calendar cal = null;
        if (value != null) {
            if(value instanceof Date) {
                Date date = (Date) value;
                return dateFormatter.format(date);
            }else{
                cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
        }
        return "";
    }
}
