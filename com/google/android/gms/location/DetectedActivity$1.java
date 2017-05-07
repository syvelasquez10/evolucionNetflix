// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.Comparator;

final class DetectedActivity$1 implements Comparator<DetectedActivity>
{
    public int a(final DetectedActivity detectedActivity, final DetectedActivity detectedActivity2) {
        int n;
        if ((n = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()))) == 0) {
            n = Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType()));
        }
        return n;
    }
}
