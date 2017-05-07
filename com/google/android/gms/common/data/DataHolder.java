// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.HashMap;
import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import android.database.CursorWindow;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DataHolder implements SafeParcelable
{
    public static final f CREATOR;
    private static final DataHolder$a Kc;
    private final int BR;
    private final int HF;
    private final String[] JU;
    Bundle JV;
    private final CursorWindow[] JW;
    private final Bundle JX;
    int[] JY;
    int JZ;
    private Object Ka;
    private boolean Kb;
    boolean mClosed;
    
    static {
        CREATOR = new f();
        Kc = new DataHolder$1(new String[0], null);
    }
    
    DataHolder(final int br, final String[] ju, final CursorWindow[] jw, final int hf, final Bundle jx) {
        this.mClosed = false;
        this.Kb = true;
        this.BR = br;
        this.JU = ju;
        this.JW = jw;
        this.HF = hf;
        this.JX = jx;
    }
    
    private DataHolder(final DataHolder$a dataHolder$a, final int n, final Bundle bundle) {
        this(dataHolder$a.JU, a(dataHolder$a, -1), n, bundle);
    }
    
    public DataHolder(final String[] array, final CursorWindow[] array2, final int hf, final Bundle jx) {
        this.mClosed = false;
        this.Kb = true;
        this.BR = 1;
        this.JU = n.i(array);
        this.JW = n.i(array2);
        this.HF = hf;
        this.JX = jx;
        this.gB();
    }
    
    public static DataHolder a(final int n, final Bundle bundle) {
        return new DataHolder(DataHolder.Kc, n, bundle);
    }
    
    private static CursorWindow[] a(final DataHolder$a dataHolder$a, int i) {
        final int n = 0;
        if (dataHolder$a.JU.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList<CursorWindow> list2 = null;
        while (true) {
            final int size2;
            Label_0088: {
                List<Map<K, String>> list = null;
                Label_0037: {
                    if (i < 0 || i >= dataHolder$a.Kd.size()) {
                        list = (List<Map<K, String>>)dataHolder$a.Kd;
                        break Label_0037;
                    }
                    CursorWindow cursorWindow = null;
                    int n2 = 0;
                    boolean b = false;
                    Label_0210: {
                        break Label_0210;
                        Map<K, String> map;
                        int n3 = 0;
                        String s;
                        String value;
                        long n4;
                        int size;
                        Label_0244_Outer:Label_0398_Outer:
                        while (true) {
                            while (true) {
                            Label_0695:
                                while (true) {
                                Label_0688:
                                    while (true) {
                                        Label_0685: {
                                            try {
                                                if (cursorWindow.allocRow()) {
                                                    break Label_0685;
                                                }
                                                Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i + ")");
                                                cursorWindow = new CursorWindow(false);
                                                cursorWindow.setStartPosition(i);
                                                cursorWindow.setNumColumns(dataHolder$a.JU.length);
                                                list2.add(cursorWindow);
                                                if (!cursorWindow.allocRow()) {
                                                    Log.e("DataHolder", "Unable to allocate row to hold data.");
                                                    list2.remove(cursorWindow);
                                                    return list2.toArray(new CursorWindow[list2.size()]);
                                                }
                                                n2 = 0;
                                                map = list.get(i);
                                                b = true;
                                                n3 = 0;
                                                if (n3 >= dataHolder$a.JU.length || !b) {
                                                    break;
                                                }
                                                s = dataHolder$a.JU[n3];
                                                value = map.get(s);
                                                if (value == null) {
                                                    b = cursorWindow.putNull(n2, n3);
                                                    break Label_0688;
                                                }
                                                if (value instanceof String) {
                                                    b = cursorWindow.putString((String)value, n2, n3);
                                                    break Label_0688;
                                                }
                                                if (value instanceof Long) {
                                                    b = cursorWindow.putLong((long)value, n2, n3);
                                                    break Label_0688;
                                                }
                                                if (value instanceof Integer) {
                                                    b = cursorWindow.putLong((long)(int)value, n2, n3);
                                                    break Label_0688;
                                                }
                                                if (value instanceof Boolean) {
                                                    if (value) {
                                                        n4 = 1L;
                                                        b = cursorWindow.putLong(n4, n2, n3);
                                                        break Label_0688;
                                                    }
                                                    break Label_0695;
                                                }
                                                else {
                                                    if (value instanceof byte[]) {
                                                        b = cursorWindow.putBlob((byte[])(byte[])(Object)value, n2, n3);
                                                        break Label_0688;
                                                    }
                                                    if (value instanceof Double) {
                                                        b = cursorWindow.putDouble((double)value, n2, n3);
                                                        break Label_0688;
                                                    }
                                                    if (value instanceof Float) {
                                                        b = cursorWindow.putDouble((double)(float)value, n2, n3);
                                                        break Label_0688;
                                                    }
                                                    throw new IllegalArgumentException("Unsupported object for column " + s + ": " + (Object)value);
                                                }
                                                list = dataHolder$a.Kd.subList(0, i);
                                                break Label_0037;
                                            }
                                            catch (RuntimeException ex) {
                                                for (size = list2.size(), i = n; i < size; ++i) {
                                                    list2.get(i).close();
                                                }
                                                throw ex;
                                            }
                                            break;
                                        }
                                        continue Label_0244_Outer;
                                    }
                                    ++n3;
                                    continue Label_0398_Outer;
                                }
                                n4 = 0L;
                                continue;
                            }
                        }
                    }
                    int n5;
                    if (!b) {
                        Log.d("DataHolder", "Couldn't populate window data for row " + i + " - allocating new window.");
                        cursorWindow.freeLastRow();
                        cursorWindow = new CursorWindow(false);
                        cursorWindow.setNumColumns(dataHolder$a.JU.length);
                        list2.add(cursorWindow);
                        n5 = i - 1;
                        i = 0;
                    }
                    else {
                        final int n6 = n2 + 1;
                        n5 = i;
                        i = n6;
                    }
                    final int n7 = n5 + 1;
                    n2 = i;
                    i = n7;
                    break Label_0088;
                }
                size2 = list.size();
                CursorWindow cursorWindow = new CursorWindow(false);
                list2 = new ArrayList<CursorWindow>();
                list2.add(cursorWindow);
                cursorWindow.setNumColumns(dataHolder$a.JU.length);
                i = 0;
                int n2 = 0;
            }
            if (i < size2) {
                continue;
            }
            break;
        }
        return list2.toArray(new CursorWindow[list2.size()]);
    }
    
    public static DataHolder as(final int n) {
        return a(n, null);
    }
    
    private void g(final String s, final int n) {
        if (this.JV == null || !this.JV.containsKey(s)) {
            throw new IllegalArgumentException("No such column: " + s);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.JZ) {
            throw new CursorIndexOutOfBoundsException(n, this.JZ);
        }
    }
    
    public long a(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].getLong(n, this.JV.getInt(s));
    }
    
    public void a(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.g(s, n);
        this.JW[n2].copyStringToBuffer(n, this.JV.getInt(s), charArrayBuffer);
    }
    
    public boolean aQ(final String s) {
        return this.JV.containsKey(s);
    }
    
    public int ar(int n) {
        int n2 = 0;
        n.I(n >= 0 && n < this.JZ);
        int n3;
        while (true) {
            n3 = n2;
            if (n2 >= this.JY.length) {
                break;
            }
            if (n < this.JY[n2]) {
                n3 = n2 - 1;
                break;
            }
            ++n2;
        }
        if ((n = n3) == this.JY.length) {
            n = n3 - 1;
        }
        return n;
    }
    
    public int b(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].getInt(n, this.JV.getInt(s));
    }
    
    public String c(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].getString(n, this.JV.getInt(s));
    }
    
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.JW.length; ++i) {
                    this.JW[i].close();
                }
            }
        }
    }
    
    public boolean d(final String s, final int n, final int n2) {
        this.g(s, n);
        return Long.valueOf(this.JW[n2].getLong(n, this.JV.getInt(s))) == 1L;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public float e(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].getFloat(n, this.JV.getInt(s));
    }
    
    public void e(final Object ka) {
        this.Ka = ka;
    }
    
    public byte[] f(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].getBlob(n, this.JV.getInt(s));
    }
    
    @Override
    protected void finalize() {
        try {
            if (this.Kb && this.JW.length > 0 && !this.isClosed()) {
                String s;
                if (this.Ka == null) {
                    s = "internal object: " + this.toString();
                }
                else {
                    s = this.Ka.toString();
                }
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + s + ")");
                this.close();
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public Uri g(String c, final int n, final int n2) {
        c = this.c(c, n, n2);
        if (c == null) {
            return null;
        }
        return Uri.parse(c);
    }
    
    public void gB() {
        final int n = 0;
        this.JV = new Bundle();
        for (int i = 0; i < this.JU.length; ++i) {
            this.JV.putInt(this.JU[i], i);
        }
        this.JY = new int[this.JW.length];
        final int n2 = 0;
        int j = n;
        int jz = n2;
        while (j < this.JW.length) {
            this.JY[j] = jz;
            jz += this.JW[j].getNumRows() - (jz - this.JW[j].getStartPosition());
            ++j;
        }
        this.JZ = jz;
    }
    
    String[] gC() {
        return this.JU;
    }
    
    CursorWindow[] gD() {
        return this.JW;
    }
    
    public int getCount() {
        return this.JZ;
    }
    
    public int getStatusCode() {
        return this.HF;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public Bundle gz() {
        return this.JX;
    }
    
    public boolean h(final String s, final int n, final int n2) {
        this.g(s, n);
        return this.JW[n2].isNull(n, this.JV.getInt(s));
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
