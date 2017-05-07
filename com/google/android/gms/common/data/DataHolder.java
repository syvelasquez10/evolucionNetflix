// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import com.google.android.gms.internal.fb;
import com.google.android.gms.internal.fo;
import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import java.util.List;
import java.util.Map;
import android.util.Log;
import java.util.ArrayList;
import com.google.android.gms.internal.fq;
import android.database.AbstractWindowedCursor;
import java.util.HashMap;
import android.content.ContentValues;
import android.database.CursorWindow;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DataHolder implements SafeParcelable
{
    private static final Builder BP;
    public static final DataHolderCreator CREATOR;
    private final int Ah;
    private final String[] BH;
    Bundle BI;
    private final CursorWindow[] BJ;
    private final Bundle BK;
    int[] BL;
    int BM;
    private Object BN;
    private boolean BO;
    boolean mClosed;
    private final int xH;
    
    static {
        CREATOR = new DataHolderCreator();
        BP = (Builder)new Builder(new String[0], null) {
            @Override
            public Builder withRow(final ContentValues contentValues) {
                throw new UnsupportedOperationException("Cannot add data to empty builder");
            }
            
            @Override
            public Builder withRow(final HashMap<String, Object> hashMap) {
                throw new UnsupportedOperationException("Cannot add data to empty builder");
            }
        };
    }
    
    DataHolder(final int xh, final String[] bh, final CursorWindow[] bj, final int ah, final Bundle bk) {
        this.mClosed = false;
        this.BO = true;
        this.xH = xh;
        this.BH = bh;
        this.BJ = bj;
        this.Ah = ah;
        this.BK = bk;
    }
    
    public DataHolder(final AbstractWindowedCursor abstractWindowedCursor, final int n, final Bundle bundle) {
        this(abstractWindowedCursor.getColumnNames(), a(abstractWindowedCursor), n, bundle);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle) {
        this(builder.BH, a(builder, -1), n, bundle);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle, final int n2) {
        this(builder.BH, a(builder, n2), n, bundle);
    }
    
    public DataHolder(final String[] array, final CursorWindow[] array2, final int ah, final Bundle bk) {
        this.mClosed = false;
        this.BO = true;
        this.xH = 1;
        this.BH = fq.f(array);
        this.BJ = fq.f(array2);
        this.Ah = ah;
        this.BK = bk;
        this.validateContents();
    }
    
    private static CursorWindow[] a(final AbstractWindowedCursor abstractWindowedCursor) {
        while (true) {
            final ArrayList<CursorWindow> list = new ArrayList<CursorWindow>();
            while (true) {
                Label_0184: {
                    Label_0157: {
                        try {
                            final int count = abstractWindowedCursor.getCount();
                            final CursorWindow window = abstractWindowedCursor.getWindow();
                            if (window != null && window.getStartPosition() == 0) {
                                window.acquireReference();
                                abstractWindowedCursor.setWindow((CursorWindow)null);
                                list.add(window);
                                final int numRows = window.getNumRows();
                                if (numRows < count && abstractWindowedCursor.moveToPosition(numRows)) {
                                    CursorWindow window2 = abstractWindowedCursor.getWindow();
                                    if (window2 != null) {
                                        window2.acquireReference();
                                        abstractWindowedCursor.setWindow((CursorWindow)null);
                                    }
                                    else {
                                        window2 = new CursorWindow(false);
                                        window2.setStartPosition(numRows);
                                        abstractWindowedCursor.fillWindow(numRows, window2);
                                    }
                                    if (window2.getNumRows() != 0) {
                                        break Label_0157;
                                    }
                                }
                                abstractWindowedCursor.close();
                                return list.toArray(new CursorWindow[list.size()]);
                            }
                            break Label_0184;
                        }
                        finally {
                            abstractWindowedCursor.close();
                        }
                    }
                    final CursorWindow cursorWindow;
                    list.add(cursorWindow);
                    final int numRows = cursorWindow.getNumRows() + cursorWindow.getStartPosition();
                    continue;
                }
                final int numRows = 0;
                continue;
            }
        }
    }
    
    private static CursorWindow[] a(final Builder builder, int i) {
        final int n = 0;
        if (builder.BH.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList<CursorWindow> list2 = null;
        while (true) {
            final int size2;
            Label_0088: {
                List<Map<K, String>> list = null;
                Label_0037: {
                    if (i < 0 || i >= builder.BQ.size()) {
                        list = (List<Map<K, String>>)builder.BQ;
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
                            Label_0670:
                                while (true) {
                                Label_0663:
                                    while (true) {
                                        Label_0660: {
                                            try {
                                                if (cursorWindow.allocRow()) {
                                                    break Label_0660;
                                                }
                                                Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i + ")");
                                                cursorWindow = new CursorWindow(false);
                                                cursorWindow.setStartPosition(i);
                                                cursorWindow.setNumColumns(builder.BH.length);
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
                                                if (n3 >= builder.BH.length || !b) {
                                                    break;
                                                }
                                                s = builder.BH[n3];
                                                value = map.get(s);
                                                if (value == null) {
                                                    b = cursorWindow.putNull(n2, n3);
                                                    break Label_0663;
                                                }
                                                if (value instanceof String) {
                                                    b = cursorWindow.putString((String)value, n2, n3);
                                                    break Label_0663;
                                                }
                                                if (value instanceof Long) {
                                                    b = cursorWindow.putLong((long)value, n2, n3);
                                                    break Label_0663;
                                                }
                                                if (value instanceof Integer) {
                                                    b = cursorWindow.putLong((long)(int)value, n2, n3);
                                                    break Label_0663;
                                                }
                                                if (value instanceof Boolean) {
                                                    if (value) {
                                                        n4 = 1L;
                                                        b = cursorWindow.putLong(n4, n2, n3);
                                                        break Label_0663;
                                                    }
                                                    break Label_0670;
                                                }
                                                else {
                                                    if (value instanceof byte[]) {
                                                        b = cursorWindow.putBlob((byte[])(byte[])(Object)value, n2, n3);
                                                        break Label_0663;
                                                    }
                                                    if (value instanceof Double) {
                                                        b = cursorWindow.putDouble((double)value, n2, n3);
                                                        break Label_0663;
                                                    }
                                                    throw new IllegalArgumentException("Unsupported object for column " + s + ": " + (Object)value);
                                                }
                                                list = builder.BQ.subList(0, i);
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
                        cursorWindow.setNumColumns(builder.BH.length);
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
                cursorWindow.setNumColumns(builder.BH.length);
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
    
    public static Builder builder(final String[] array) {
        return new Builder(array, (String)null);
    }
    
    public static Builder builder(final String[] array, final String s) {
        fq.f(s);
        return new Builder(array, s);
    }
    
    private void e(final String s, final int n) {
        if (this.BI == null || !this.BI.containsKey(s)) {
            throw new IllegalArgumentException("No such column: " + s);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.BM) {
            throw new CursorIndexOutOfBoundsException(n, this.BM);
        }
    }
    
    public static DataHolder empty(final int n) {
        return empty(n, null);
    }
    
    public static DataHolder empty(final int n, final Bundle bundle) {
        return new DataHolder(DataHolder.BP, n, bundle);
    }
    
    public int G(int n) {
        int n2 = 0;
        fq.x(n >= 0 && n < this.BM);
        int n3;
        while (true) {
            n3 = n2;
            if (n2 >= this.BL.length) {
                break;
            }
            if (n < this.BL[n2]) {
                n3 = n2 - 1;
                break;
            }
            ++n2;
        }
        if ((n = n3) == this.BL.length) {
            n = n3 - 1;
        }
        return n;
    }
    
    public void c(final Object bn) {
        this.BN = bn;
    }
    
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.BJ.length; ++i) {
                    this.BJ[i].close();
                }
            }
        }
    }
    
    public void copyToBuffer(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.e(s, n);
        this.BJ[n2].copyStringToBuffer(n, this.BI.getInt(s), charArrayBuffer);
    }
    
    public int describeContents() {
        return 0;
    }
    
    String[] er() {
        return this.BH;
    }
    
    CursorWindow[] es() {
        return this.BJ;
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.BO && this.BJ.length > 0 && !this.isClosed()) {
                String s;
                if (this.BN == null) {
                    s = "internal object: " + this.toString();
                }
                else {
                    s = this.BN.toString();
                }
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call close() on all DataBuffer extending objects when you are done with them. (" + s + ")");
                this.close();
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public boolean getBoolean(final String s, final int n, final int n2) {
        this.e(s, n);
        return Long.valueOf(this.BJ[n2].getLong(n, this.BI.getInt(s))) == 1L;
    }
    
    public byte[] getByteArray(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].getBlob(n, this.BI.getInt(s));
    }
    
    public int getCount() {
        return this.BM;
    }
    
    public double getDouble(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].getDouble(n, this.BI.getInt(s));
    }
    
    public int getInteger(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].getInt(n, this.BI.getInt(s));
    }
    
    public long getLong(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].getLong(n, this.BI.getInt(s));
    }
    
    public Bundle getMetadata() {
        return this.BK;
    }
    
    public int getStatusCode() {
        return this.Ah;
    }
    
    public String getString(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].getString(n, this.BI.getInt(s));
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public boolean hasColumn(final String s) {
        return this.BI.containsKey(s);
    }
    
    public boolean hasNull(final String s, final int n, final int n2) {
        this.e(s, n);
        return this.BJ[n2].isNull(n, this.BI.getInt(s));
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public Uri parseUri(String string, final int n, final int n2) {
        string = this.getString(string, n, n2);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }
    
    public void validateContents() {
        final int n = 0;
        this.BI = new Bundle();
        for (int i = 0; i < this.BH.length; ++i) {
            this.BI.putInt(this.BH[i], i);
        }
        this.BL = new int[this.BJ.length];
        final int n2 = 0;
        int j = n;
        int bm = n2;
        while (j < this.BJ.length) {
            this.BL[j] = bm;
            bm += this.BJ[j].getNumRows() - (bm - this.BJ[j].getStartPosition());
            ++j;
        }
        this.BM = bm;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DataHolderCreator.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private final String[] BH;
        private final ArrayList<HashMap<String, Object>> BQ;
        private final String BR;
        private final HashMap<Object, Integer> BS;
        private boolean BT;
        private String BU;
        
        private Builder(final String[] array, final String br) {
            this.BH = fq.f(array);
            this.BQ = new ArrayList<HashMap<String, Object>>();
            this.BR = br;
            this.BS = new HashMap<Object, Integer>();
            this.BT = false;
            this.BU = null;
        }
        
        private void a(final HashMap<String, Object> hashMap) {
            final Object value = hashMap.get(this.BR);
            if (value == null) {
                return;
            }
            final Integer n = this.BS.remove(value);
            if (n != null) {
                this.BQ.remove((int)n);
            }
            this.BS.put(value, this.BQ.size());
        }
        
        private void et() {
            if (this.BR != null) {
                this.BS.clear();
                for (int size = this.BQ.size(), i = 0; i < size; ++i) {
                    final Object value = this.BQ.get(i).get(this.BR);
                    if (value != null) {
                        this.BS.put(value, i);
                    }
                }
            }
        }
        
        public DataHolder build(final int n) {
            return new DataHolder(this, n, null, null);
        }
        
        public DataHolder build(final int n, final Bundle bundle) {
            return new DataHolder(this, n, bundle, -1, null);
        }
        
        public DataHolder build(final int n, final Bundle bundle, final int n2) {
            return new DataHolder(this, n, bundle, n2, null);
        }
        
        public int getCount() {
            return this.BQ.size();
        }
        
        public Builder removeRowsWithValue(final String s, final Object o) {
            for (int i = this.BQ.size() - 1; i >= 0; --i) {
                if (fo.equal(this.BQ.get(i).get(s), o)) {
                    this.BQ.remove(i);
                }
            }
            return this;
        }
        
        public Builder sort(final String bu) {
            fb.d(bu);
            if (this.BT && bu.equals(this.BU)) {
                return this;
            }
            Collections.sort(this.BQ, new a(bu));
            this.et();
            this.BT = true;
            this.BU = bu;
            return this;
        }
        
        public Builder withRow(final ContentValues contentValues) {
            fb.d(contentValues);
            final HashMap<String, Object> hashMap = new HashMap<String, Object>(contentValues.size());
            for (final Map.Entry<Object, V> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return this.withRow(hashMap);
        }
        
        public Builder withRow(final HashMap<String, Object> hashMap) {
            fb.d(hashMap);
            if (this.BR != null) {
                this.a(hashMap);
            }
            this.BQ.add(hashMap);
            this.BT = false;
            return this;
        }
    }
    
    private static final class a implements Comparator<HashMap<String, Object>>
    {
        private final String BV;
        
        a(final String s) {
            this.BV = fq.f(s);
        }
        
        public int a(final HashMap<String, Object> hashMap, final HashMap<String, Object> hashMap2) {
            final Boolean f = fq.f((Boolean)hashMap.get(this.BV));
            final String f2 = fq.f((String)hashMap2.get(this.BV));
            if (f.equals(f2)) {
                return 0;
            }
            if (f instanceof Boolean) {
                return f.compareTo((Boolean)f2);
            }
            if (f instanceof Long) {
                return ((Long)(Object)f).compareTo((Long)f2);
            }
            if (f instanceof Integer) {
                return ((Integer)(Object)f).compareTo((Integer)f2);
            }
            if (f instanceof String) {
                return ((String)f).compareTo((String)f2);
            }
            throw new IllegalArgumentException("Unknown type for lValue " + f);
        }
    }
}
