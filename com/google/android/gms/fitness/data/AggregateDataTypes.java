// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AggregateDataTypes
{
    public static final DataType ACTIVITY_SUMMARY;
    public static final DataType DISTANCE_DELTA;
    public static final DataType HEART_RATE_SUMMARY;
    public static final Set<DataType> INPUT_TYPES;
    public static final DataType LOCATION_BOUNDING_BOX;
    public static final DataType POWER_SUMMARY;
    public static final DataType SPEED_SUMMARY;
    public static final DataType STEP_COUNT_DELTA;
    public static final DataType[] Sm;
    public static final String[] Sn;
    private static final Map<DataType, List<DataType>> So;
    public static final DataType WEIGHT_SUMMARY;
    
    static {
        STEP_COUNT_DELTA = DataTypes.STEP_COUNT_DELTA;
        ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", new Field[] { Fields.ACTIVITY, Fields.DURATION, Fields.NUM_SEGMENTS });
        DISTANCE_DELTA = DataTypes.DISTANCE_DELTA;
        HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", new Field[] { Fields.AVERAGE, Fields.MAX, Fields.MIN });
        LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", new Field[] { Fields.LOW_LATITUDE, Fields.LOW_LONGITUDE, Fields.HIGH_LATITUDE, Fields.HIGH_LONGITUDE });
        POWER_SUMMARY = new DataType("com.google.power.summary", new Field[] { Fields.AVERAGE, Fields.MAX, Fields.MIN });
        SPEED_SUMMARY = new DataType("com.google.speed.summary", new Field[] { Fields.AVERAGE, Fields.MAX, Fields.MIN });
        WEIGHT_SUMMARY = new DataType("com.google.weight.summary", new Field[] { Fields.AVERAGE, Fields.MAX, Fields.MIN });
        INPUT_TYPES = new HashSet<DataType>(Arrays.asList(DataTypes.STEP_COUNT_DELTA, DataTypes.DISTANCE_DELTA, DataTypes.ACTIVITY_SEGMENT, DataTypes.SPEED, DataTypes.HEART_RATE_BPM, DataTypes.WEIGHT, DataTypes.LOCATION_SAMPLE));
        Sm = new DataType[] { AggregateDataTypes.ACTIVITY_SUMMARY, AggregateDataTypes.DISTANCE_DELTA, AggregateDataTypes.HEART_RATE_SUMMARY, AggregateDataTypes.LOCATION_BOUNDING_BOX, AggregateDataTypes.POWER_SUMMARY, AggregateDataTypes.SPEED_SUMMARY, AggregateDataTypes.STEP_COUNT_DELTA, AggregateDataTypes.WEIGHT_SUMMARY };
        Sn = new String[] { AggregateDataTypes.ACTIVITY_SUMMARY.getName(), AggregateDataTypes.DISTANCE_DELTA.getName(), AggregateDataTypes.HEART_RATE_SUMMARY.getName(), AggregateDataTypes.LOCATION_BOUNDING_BOX.getName(), AggregateDataTypes.POWER_SUMMARY.getName(), AggregateDataTypes.SPEED_SUMMARY.getName(), AggregateDataTypes.STEP_COUNT_DELTA.getName(), AggregateDataTypes.WEIGHT_SUMMARY.getName() };
        So = new AggregateDataTypes$1();
    }
    
    public static List<DataType> getForInput(final DataType dataType) {
        final List<DataType> list = AggregateDataTypes.So.get(dataType);
        if (list == null) {
            return Collections.emptyList();
        }
        return (List<DataType>)Collections.unmodifiableList((List<?>)list);
    }
}
