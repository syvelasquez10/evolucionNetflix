// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.fitness.data.DataSource;
import android.content.Intent;

public class FitnessIntents
{
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    public static final String EXTRA_STATUS = "actionStatus";
    public static final String MIME_TYPE_ACTIVITY_PREFIX = "vnd.google.fitness.activity/";
    public static final String MIME_TYPE_DATA_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    public static final String MIME_TYPE_SESSION_PREFIX = "vnd.google.fitness.session/";
    public static final String STATUS_ACTIVE = "ActiveActionStatus";
    public static final String STATUS_COMPLETED = "CompletedActionStatus";
    
    public static String getActivityMimeType(final int n) {
        return "vnd.google.fitness.activity/" + FitnessActivities.getName(n);
    }
    
    public static DataSource getDataSource(final Intent intent) {
        return c.a(intent, "vnd.google.fitness.data_source", DataSource.CREATOR);
    }
    
    public static String getDataTypeMimeType(final DataType dataType) {
        return "vnd.google.fitness.data_type/" + dataType.getName();
    }
    
    public static long getEndTime(final Intent intent) {
        return intent.getLongExtra("vnd.google.fitness.end_time", -1L);
    }
    
    public static String getSessionMimeType(final int n) {
        return "vnd.google.fitness.session/" + FitnessActivities.getName(n);
    }
    
    public static long getStartTime(final Intent intent) {
        return intent.getLongExtra("vnd.google.fitness.start_time", -1L);
    }
}
