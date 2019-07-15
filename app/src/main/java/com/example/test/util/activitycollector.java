package com.example.test.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class activitycollector {
    public static List<Activity> list=new ArrayList<>();
    public static void addactivity(Activity activity){
        list.add(activity);
    }
    public static void removeactivity(Activity activity){
        list.remove(activity);
    }
    public static void finishall(){
        for(Activity a:list){
            if(!a.isFinishing()){
                a.finish();
            }
        }
        list.clear();
    }
}
