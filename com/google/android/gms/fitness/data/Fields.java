// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

public class Fields
{
    public static final Field ACCURACY;
    public static final Field ACTIVITY;
    public static final Field ALTITUDE;
    public static final Field AVERAGE;
    public static final Field BPM;
    public static final Field CALORIES;
    public static final Field CONFIDENCE;
    public static final Field DISTANCE;
    public static final Field DURATION;
    public static final Field HEIGHT;
    public static final Field HIGH_LATITUDE;
    public static final Field HIGH_LONGITUDE;
    public static final Field LATITUDE;
    public static final Field LONGITUDE;
    public static final Field LOW_LATITUDE;
    public static final Field LOW_LONGITUDE;
    public static final Field MAX;
    public static final Field MIN;
    public static final Field NUM_SEGMENTS;
    public static final Field REVOLUTIONS;
    public static final Field RPM;
    public static final Field SPEED;
    public static final Field STEPS;
    public static final Field SU;
    public static final Field SV;
    public static final Field SW;
    public static final Field SX;
    public static final Field WATTS;
    public static final Field WEIGHT;
    
    static {
        ACTIVITY = new Field("activity", 1);
        CONFIDENCE = new Field("confidence", 2);
        STEPS = new Field("steps", 1);
        DURATION = new Field("duration", 1);
        BPM = new Field("bpm", 2);
        LATITUDE = new Field("latitude", 2);
        LONGITUDE = new Field("longitude", 2);
        ACCURACY = new Field("accuracy", 2);
        ALTITUDE = new Field("altitude", 2);
        DISTANCE = new Field("distance", 2);
        HEIGHT = new Field("height", 2);
        WEIGHT = new Field("weight", 2);
        SPEED = new Field("speed", 2);
        RPM = new Field("rpm", 2);
        REVOLUTIONS = new Field("revolutions", 1);
        CALORIES = new Field("calories", 2);
        WATTS = new Field("watts", 2);
        NUM_SEGMENTS = new Field("num_segments", 1);
        AVERAGE = new Field("average", 2);
        MAX = new Field("max", 2);
        MIN = new Field("min", 2);
        LOW_LATITUDE = new Field("low_latitude", 2);
        LOW_LONGITUDE = new Field("low_longitude", 2);
        HIGH_LATITUDE = new Field("high_latitude", 2);
        HIGH_LONGITUDE = new Field("high_longitude", 2);
        SU = new Field("edge_type", 1);
        SV = new Field("x", 2);
        SW = new Field("y", 2);
        SX = new Field("z", 2);
    }
}
