// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ReadableMap;

public class TransformHelper
{
    private static ThreadLocal<double[]> sHelperMatrix;
    
    static {
        TransformHelper.sHelperMatrix = new TransformHelper$1();
    }
    
    private static double convertToRadians(final ReadableMap readableMap, String string) {
        int n = 1;
        double double1;
        if (readableMap.getType(string) == ReadableType.String) {
            string = readableMap.getString(string);
            String s;
            if (string.endsWith("rad")) {
                s = string.substring(0, string.length() - 3);
            }
            else {
                s = string;
                if (string.endsWith("deg")) {
                    s = string.substring(0, string.length() - 3);
                    n = 0;
                }
            }
            double1 = Float.parseFloat(s);
        }
        else {
            double1 = readableMap.getDouble(string);
            n = 1;
        }
        if (n != 0) {
            return double1;
        }
        return MatrixMathHelper.degreesToRadians(double1);
    }
    
    public static void processTransform(final ReadableArray readableArray, final double[] array) {
        final double[] array2 = TransformHelper.sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(array);
        for (int size = readableArray.size(), i = 0; i < size; ++i) {
            final ReadableMap map = readableArray.getMap(i);
            final String nextKey = map.keySetIterator().nextKey();
            MatrixMathHelper.resetIdentityMatrix(array2);
            if ("matrix".equals(nextKey)) {
                final ReadableArray array3 = map.getArray(nextKey);
                for (int j = 0; j < 16; ++j) {
                    array2[j] = array3.getDouble(j);
                }
            }
            else if ("perspective".equals(nextKey)) {
                MatrixMathHelper.applyPerspective(array2, map.getDouble(nextKey));
            }
            else if ("rotateX".equals(nextKey)) {
                MatrixMathHelper.applyRotateX(array2, convertToRadians(map, nextKey));
            }
            else if ("rotateY".equals(nextKey)) {
                MatrixMathHelper.applyRotateY(array2, convertToRadians(map, nextKey));
            }
            else if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                MatrixMathHelper.applyRotateZ(array2, convertToRadians(map, nextKey));
            }
            else if ("scale".equals(nextKey)) {
                final double double1 = map.getDouble(nextKey);
                MatrixMathHelper.applyScaleX(array2, double1);
                MatrixMathHelper.applyScaleY(array2, double1);
            }
            else if ("scaleX".equals(nextKey)) {
                MatrixMathHelper.applyScaleX(array2, map.getDouble(nextKey));
            }
            else if ("scaleY".equals(nextKey)) {
                MatrixMathHelper.applyScaleY(array2, map.getDouble(nextKey));
            }
            else if ("translate".equals(nextKey)) {
                final ReadableArray array4 = map.getArray(nextKey);
                final double double2 = array4.getDouble(0);
                final double double3 = array4.getDouble(1);
                double double4;
                if (array4.size() > 2) {
                    double4 = array4.getDouble(2);
                }
                else {
                    double4 = 0.0;
                }
                MatrixMathHelper.applyTranslate3D(array2, double2, double3, double4);
            }
            else if ("translateX".equals(nextKey)) {
                MatrixMathHelper.applyTranslate2D(array2, map.getDouble(nextKey), 0.0);
            }
            else if ("translateY".equals(nextKey)) {
                MatrixMathHelper.applyTranslate2D(array2, 0.0, map.getDouble(nextKey));
            }
            else if ("skewX".equals(nextKey)) {
                MatrixMathHelper.applySkewX(array2, convertToRadians(map, nextKey));
            }
            else {
                if (!"skewY".equals(nextKey)) {
                    throw new JSApplicationIllegalArgumentException("Unsupported transform type: " + nextKey);
                }
                MatrixMathHelper.applySkewY(array2, convertToRadians(map, nextKey));
            }
            MatrixMathHelper.multiplyInto(array, array, array2);
        }
    }
}
