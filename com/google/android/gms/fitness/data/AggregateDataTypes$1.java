// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

final class AggregateDataTypes$1 extends HashMap<DataType, List<DataType>>
{
    AggregateDataTypes$1() {
        this.put(DataTypes.STEP_COUNT_DELTA, Arrays.asList(DataTypes.STEP_COUNT_DELTA));
        this.put(DataTypes.DISTANCE_DELTA, Arrays.asList(DataTypes.DISTANCE_DELTA));
        this.put(DataTypes.ACTIVITY_SEGMENT, Arrays.asList(AggregateDataTypes.ACTIVITY_SUMMARY));
        this.put(DataTypes.SPEED, Arrays.asList(AggregateDataTypes.SPEED_SUMMARY));
        this.put(DataTypes.HEART_RATE_BPM, Arrays.asList(AggregateDataTypes.HEART_RATE_SUMMARY));
        this.put(DataTypes.WEIGHT, Arrays.asList(AggregateDataTypes.WEIGHT_SUMMARY));
        this.put(DataTypes.LOCATION_SAMPLE, Arrays.asList(AggregateDataTypes.LOCATION_BOUNDING_BOX));
    }
}
