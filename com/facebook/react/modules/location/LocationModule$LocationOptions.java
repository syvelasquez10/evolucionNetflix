// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import com.facebook.react.bridge.ReadableMap;

class LocationModule$LocationOptions
{
    private final float distanceFilter;
    private final boolean highAccuracy;
    private final double maximumAge;
    private final long timeout;
    
    private LocationModule$LocationOptions(final long timeout, final double maximumAge, final boolean highAccuracy, final float distanceFilter) {
        this.timeout = timeout;
        this.maximumAge = maximumAge;
        this.highAccuracy = highAccuracy;
        this.distanceFilter = distanceFilter;
    }
    
    private static LocationModule$LocationOptions fromReactMap(final ReadableMap readableMap) {
        long n;
        if (readableMap.hasKey("timeout")) {
            n = (long)readableMap.getDouble("timeout");
        }
        else {
            n = Long.MAX_VALUE;
        }
        double double1;
        if (readableMap.hasKey("maximumAge")) {
            double1 = readableMap.getDouble("maximumAge");
        }
        else {
            double1 = Double.POSITIVE_INFINITY;
        }
        final boolean b = readableMap.hasKey("enableHighAccuracy") && readableMap.getBoolean("enableHighAccuracy");
        float n2;
        if (readableMap.hasKey("distanceFilter")) {
            n2 = (float)readableMap.getDouble("distanceFilter");
        }
        else {
            n2 = 100.0f;
        }
        return new LocationModule$LocationOptions(n, double1, b, n2);
    }
}
