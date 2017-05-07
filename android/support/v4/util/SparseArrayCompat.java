// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public class SparseArrayCompat<E> implements Cloneable
{
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;
    
    static {
        DELETED = new Object();
    }
    
    public SparseArrayCompat() {
        this(10);
    }
    
    public SparseArrayCompat(int idealIntArraySize) {
        this.mGarbage = false;
        if (idealIntArraySize == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            idealIntArraySize = ContainerHelpers.idealIntArraySize(idealIntArraySize);
            this.mKeys = new int[idealIntArraySize];
            this.mValues = new Object[idealIntArraySize];
        }
        this.mSize = 0;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        final int[] mKeys = this.mKeys;
        final Object[] mValues = this.mValues;
        int i = 0;
        int mSize2 = 0;
        while (i < mSize) {
            final Object o = mValues[i];
            int n = mSize2;
            if (o != SparseArrayCompat.DELETED) {
                if (i != mSize2) {
                    mKeys[mSize2] = mKeys[i];
                    mValues[mSize2] = o;
                    mValues[i] = null;
                }
                n = mSize2 + 1;
            }
            ++i;
            mSize2 = n;
        }
        this.mGarbage = false;
        this.mSize = mSize2;
    }
    
    public void clear() {
        final int mSize = this.mSize;
        final Object[] mValues = this.mValues;
        for (int i = 0; i < mSize; ++i) {
            mValues[i] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }
    
    public SparseArrayCompat<E> clone() {
        SparseArrayCompat sparseArrayCompat;
        try {
            final SparseArrayCompat<E> sparseArrayCompat2;
            sparseArrayCompat = (sparseArrayCompat2 = (SparseArrayCompat)super.clone());
            final SparseArrayCompat sparseArrayCompat3 = this;
            final int[] array = sparseArrayCompat3.mKeys;
            final int[] array2 = array.clone();
            final int[] array3 = array2;
            sparseArrayCompat2.mKeys = array3;
            final SparseArrayCompat sparseArrayCompat4 = sparseArrayCompat;
            final SparseArrayCompat sparseArrayCompat5 = this;
            final Object[] array4 = sparseArrayCompat5.mValues;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            sparseArrayCompat4.mValues = array6;
            return sparseArrayCompat;
        }
        catch (CloneNotSupportedException sparseArrayCompat) {
            return null;
        }
        try {
            final SparseArrayCompat<E> sparseArrayCompat2 = sparseArrayCompat;
            final SparseArrayCompat sparseArrayCompat3 = this;
            final int[] array = sparseArrayCompat3.mKeys;
            final int[] array2 = array.clone();
            final int[] array3 = array2;
            sparseArrayCompat2.mKeys = array3;
            final SparseArrayCompat sparseArrayCompat4 = sparseArrayCompat;
            final SparseArrayCompat sparseArrayCompat5 = this;
            final Object[] array4 = sparseArrayCompat5.mValues;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            sparseArrayCompat4.mValues = array6;
            return sparseArrayCompat;
        }
        catch (CloneNotSupportedException ex) {
            return sparseArrayCompat;
        }
    }
    
    public int keyAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mKeys[n];
    }
    
    public int size() {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mSize;
    }
    
    @Override
    public String toString() {
        if (this.size() <= 0) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.keyAt(i));
            sb.append('=');
            final E value = this.valueAt(i);
            if (value != this) {
                sb.append(value);
            }
            else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
    
    public E valueAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return (E)this.mValues[n];
    }
}
