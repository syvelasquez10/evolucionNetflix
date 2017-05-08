// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils
{
    public static int[] append(final int[] array, final int n, final int n2) {
        assert n <= array.length;
        int[] array2 = array;
        if (n + 1 > array.length) {
            array2 = new int[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n);
        }
        array2[n] = n2;
        return array2;
    }
    
    public static <T> T[] append(T[] array, final int n, final T t) {
        assert n <= array.length;
        if (n + 1 > array.length) {
            final Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), growSize(n));
            System.arraycopy(array, 0, array2, 0, n);
            array = (T[])array2;
        }
        array[n] = t;
        return array;
    }
    
    public static int growSize(final int n) {
        if (n <= 4) {
            return 8;
        }
        return n * 2;
    }
}
