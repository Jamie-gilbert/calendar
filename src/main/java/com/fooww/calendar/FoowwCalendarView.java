package com.fooww.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author ggg
 * @version 1.0
 * @date 2019/2/21 17:09
 * @description
 */
public class FoowwCalendarView extends LinearLayout {
    private ListView mListView;
    private CalendarDateView mCalendarDateView;
    private Context context;
    /*
    列表背景颜色
     */
    private int listBgColorId;
    /*
    是否有列表
     */
    private boolean haveList;
    /**
     * 日历的背景颜色
     */
    private int calendarBgColor = -1;
    /**
     * 日历的背景图片
     */
    private int calendarBgDrawable = -1;

    public FoowwCalendarView(Context context) {
        super(context);
        inint(context, null);
    }

    public FoowwCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inint(context, attrs);
    }

    public FoowwCalendarView(Context context, @Nullable AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context, attrs);
    }

    private void inint(Context context, AttributeSet attrs) {
        this.context = context;
        haveList = false;
        listBgColorId = R.color.halftransparent;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FoowwCalendarView);
            haveList = typedArray.getBoolean(R.styleable.FoowwCalendarView_haveList, false);
            if (haveList) {
                listBgColorId = typedArray.getResourceId(R.styleable.FoowwCalendarView_listBgColor, R.color.halftransparent);
            }
            calendarBgColor = typedArray.getResourceId(R.styleable.FoowwCalendarView_calendarBgColor, -1);
            calendarBgDrawable = typedArray.getResourceId(R.styleable.FoowwCalendarView_calendarBgDrawable, -1);

        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_canledar, null);
        mListView = view.findViewById(R.id.list);
        if (!haveList) {
            noList();
        } else {
            mListView.setBackgroundColor(ContextCompat.getColor(getContext(), listBgColorId));
        }
        mCalendarDateView = view.findViewById(R.id.calendarDateView);
        initCaledar();
        addView(view);
    }

    public CalendarBean getSelectedDate(){

    }
    /**
     * 设置事件列表的adapter
     *
     * @param adapter
     */
    public void setListAdapter(ListAdapter adapter) {
        if (mListView != null) {
            mListView.setAdapter(adapter);
        }
    }

    /**
     * 设置日历的点击事件
     *
     * @param listener
     */
    public void setmCalendarDateViewClick(CalendarView.OnItemClickListener listener) {
        if (mCalendarDateView != null) {
            mCalendarDateView.setOnItemClickListener(listener);
        }
    }

    private void initCaledar() {
        if (calendarBgColor != -1) {
            mCalendarDateView.setBackgroundColor(ContextCompat.getColor(context, calendarBgColor));
        }
        if (calendarBgDrawable != -1) {
            mCalendarDateView.setBackground(ContextCompat.getDrawable(context, calendarBgDrawable));
        }
        mCalendarDateView.setAdapter((convertView, parentView, bean) -> {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_canledar_double, null);
            }

            TextView chinaText = convertView.findViewById(R.id.tv_china_date);
            TextView text = convertView.findViewById(R.id.tv_num_date);

            text.setText(String.valueOf(bean.day));
            if (bean.mothFlag != 0) {
                text.setTextColor(0xff9299a1);
            } else {
                text.setTextColor(0xff444444);
            }
            chinaText.setText(bean.chinaDay);

            return convertView;
        });

    }

    private void noList() {
        mListView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.halftransparent));
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return convertView;
            }
        });
    }

}
