// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.react.bridge.ReadableArray;

class PropHelper
{
    static int toFloatArray(final ReadableArray readableArray, final float[] array) {
        int n;
        if (readableArray.size() > array.length) {
            n = array.length;
        }
        else {
            n = readableArray.size();
        }
        for (int i = 0; i < n; ++i) {
            array[i] = (float)readableArray.getDouble(i);
        }
        return readableArray.size();
    }
    
    static float[] toFloatArray(final ReadableArray readableArray) {
        if (readableArray != null) {
            final float[] array = new float[readableArray.size()];
            toFloatArray(readableArray, array);
            return array;
        }
        return null;
    }
}
