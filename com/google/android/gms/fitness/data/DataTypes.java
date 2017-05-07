// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

public class DataTypes
{
    public static final DataType ACTIVITY_EDGE;
    public static final DataType ACTIVITY_SAMPLE;
    public static final DataType ACTIVITY_SEGMENT;
    public static final DataType CALORIES_CONSUMED;
    public static final DataType CALORIES_EXPENDED;
    public static final DataType CYCLING_PEDALING_CADENCE;
    public static final DataType CYCLING_PEDALING_CUMULATIVE;
    public static final DataType CYCLING_WHEEL_REVOLUTION;
    public static final DataType CYCLING_WHEEL_RPM;
    public static final DataType DISTANCE_CUMULATIVE;
    public static final DataType DISTANCE_DELTA;
    public static final DataType HEART_RATE_BPM;
    public static final DataType HEIGHT;
    public static final DataType LOCATION_SAMPLE;
    public static final DataType POWER_SAMPLE;
    public static final DataType SO;
    public static final DataType SP;
    public static final DataType SPEED;
    public static final DataType STEP_COUNT_CADENCE;
    public static final DataType STEP_COUNT_CUMULATIVE;
    public static final DataType STEP_COUNT_DELTA;
    public static final DataType[] Sm;
    public static final String[] Sn;
    public static final DataType WEIGHT;
    
    static {
        STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", new Field[] { Fields.STEPS });
        STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", new Field[] { Fields.STEPS });
        STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", new Field[] { Fields.RPM });
        ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", new Field[] { Fields.ACTIVITY });
        CALORIES_CONSUMED = new DataType("com.google.calories.consumed", new Field[] { Fields.CALORIES });
        CALORIES_EXPENDED = new DataType("com.google.calories.expended", new Field[] { Fields.CALORIES });
        POWER_SAMPLE = new DataType("com.google.power.sample", new Field[] { Fields.WATTS });
        ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", new Field[] { Fields.ACTIVITY, Fields.CONFIDENCE });
        ACTIVITY_EDGE = new DataType("com.google.activity.edge", new Field[] { Fields.ACTIVITY, Fields.SU });
        SO = new DataType("com.google.accelerometer", new Field[] { Fields.SV, Fields.SW, Fields.SX });
        HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", new Field[] { Fields.BPM });
        LOCATION_SAMPLE = new DataType("com.google.location.sample", new Field[] { Fields.LATITUDE, Fields.LONGITUDE, Fields.ACCURACY, Fields.ALTITUDE });
        SP = new DataType("com.google.location", new Field[] { Fields.LATITUDE, Fields.LONGITUDE, Fields.ACCURACY });
        DISTANCE_DELTA = new DataType("com.google.distance.delta", new Field[] { Fields.DISTANCE });
        DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", new Field[] { Fields.DISTANCE });
        SPEED = new DataType("com.google.speed", new Field[] { Fields.SPEED });
        CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", new Field[] { Fields.REVOLUTIONS });
        CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", new Field[] { Fields.RPM });
        CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", new Field[] { Fields.REVOLUTIONS });
        CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", new Field[] { Fields.RPM });
        HEIGHT = new DataType("com.google.height", new Field[] { Fields.HEIGHT });
        WEIGHT = new DataType("com.google.weight", new Field[] { Fields.WEIGHT });
        Sm = new DataType[] { DataTypes.SO, DataTypes.ACTIVITY_EDGE, DataTypes.ACTIVITY_SAMPLE, DataTypes.ACTIVITY_SEGMENT, DataTypes.CALORIES_CONSUMED, DataTypes.CALORIES_EXPENDED, DataTypes.CYCLING_PEDALING_CADENCE, DataTypes.CYCLING_PEDALING_CUMULATIVE, DataTypes.CYCLING_WHEEL_REVOLUTION, DataTypes.CYCLING_WHEEL_RPM, DataTypes.DISTANCE_CUMULATIVE, DataTypes.DISTANCE_DELTA, DataTypes.HEART_RATE_BPM, DataTypes.HEIGHT, DataTypes.SP, DataTypes.LOCATION_SAMPLE, DataTypes.POWER_SAMPLE, DataTypes.SPEED, DataTypes.STEP_COUNT_CADENCE, DataTypes.STEP_COUNT_CUMULATIVE, DataTypes.STEP_COUNT_DELTA, DataTypes.WEIGHT };
        Sn = new String[] { DataTypes.SO.getName(), DataTypes.ACTIVITY_EDGE.getName(), DataTypes.ACTIVITY_SAMPLE.getName(), DataTypes.ACTIVITY_SEGMENT.getName(), DataTypes.CALORIES_CONSUMED.getName(), DataTypes.CALORIES_EXPENDED.getName(), DataTypes.CYCLING_PEDALING_CADENCE.getName(), DataTypes.CYCLING_PEDALING_CUMULATIVE.getName(), DataTypes.CYCLING_WHEEL_REVOLUTION.getName(), DataTypes.CYCLING_WHEEL_RPM.getName(), DataTypes.DISTANCE_CUMULATIVE.getName(), DataTypes.DISTANCE_DELTA.getName(), DataTypes.HEART_RATE_BPM.getName(), DataTypes.HEIGHT.getName(), DataTypes.SP.getName(), DataTypes.LOCATION_SAMPLE.getName(), DataTypes.POWER_SAMPLE.getName(), DataTypes.SPEED.getName(), DataTypes.STEP_COUNT_CADENCE.getName(), DataTypes.STEP_COUNT_CUMULATIVE.getName(), DataTypes.STEP_COUNT_DELTA.getName(), DataTypes.WEIGHT.getName() };
    }
}
