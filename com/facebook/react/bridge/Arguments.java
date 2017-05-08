// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

public class Arguments
{
    public static WritableArray createArray() {
        return new WritableNativeArray();
    }
    
    public static WritableMap createMap() {
        return new WritableNativeMap();
    }
    
    public static WritableNativeArray fromJavaArgs(final Object[] array) {
        final WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i = 0; i < array.length; ++i) {
            final Object o = array[i];
            if (o == null) {
                writableNativeArray.pushNull();
            }
            else {
                final Class<? extends Boolean> class1 = ((Boolean)o).getClass();
                if (class1 == Boolean.class) {
                    writableNativeArray.pushBoolean((boolean)o);
                }
                else if (class1 == Integer.class) {
                    writableNativeArray.pushDouble((double)o);
                }
                else if (class1 == Double.class) {
                    writableNativeArray.pushDouble((double)o);
                }
                else if (class1 == Float.class) {
                    writableNativeArray.pushDouble((double)o);
                }
                else if (class1 == String.class) {
                    writableNativeArray.pushString(o.toString());
                }
                else if (class1 == WritableNativeMap.class) {
                    writableNativeArray.pushMap((WritableMap)o);
                }
                else {
                    if (class1 != WritableNativeArray.class) {
                        throw new RuntimeException("Cannot convert argument of type " + class1);
                    }
                    writableNativeArray.pushArray((WritableArray)o);
                }
            }
        }
        return writableNativeArray;
    }
}
