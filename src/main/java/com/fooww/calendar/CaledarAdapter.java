package com.fooww.calendar;

import android.view.View;
import android.view.ViewGroup;


public interface CaledarAdapter {
     View getView(View convertView, ViewGroup parentView, CalendarBean bean);
}
