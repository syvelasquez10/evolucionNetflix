// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import com.google.android.gms.internal.ds;
import com.google.android.gms.internal.ee;
import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import java.util.List;
import java.util.Map;
import android.util.Log;
import java.util.ArrayList;
import com.google.android.gms.internal.eg;
import android.database.AbstractWindowedCursor;
import java.util.HashMap;
import android.content.ContentValues;
import android.database.CursorWindow;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataHolder implements SafeParcelable
{
    public static final DataHolderCreator CREATOR;
    private static final Builder nS;
    private final int kg;
    private final int mC;
    boolean mClosed;
    private final String[] nK;
    Bundle nL;
    private final CursorWindow[] nM;
    private final Bundle nN;
    int[] nO;
    int nP;
    private Object nQ;
    private boolean nR;
    
    static {
        CREATOR = new DataHolderCreator();
        nS = (Builder)new Builder(new String[0], null) {
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
    
    DataHolder(final int kg, final String[] nk, final CursorWindow[] nm, final int mc, final Bundle nn) {
        this.mClosed = false;
        this.nR = true;
        this.kg = kg;
        this.nK = nk;
        this.nM = nm;
        this.mC = mc;
        this.nN = nn;
    }
    
    public DataHolder(final AbstractWindowedCursor abstractWindowedCursor, final int n, final Bundle bundle) {
        this(abstractWindowedCursor.getColumnNames(), a(abstractWindowedCursor), n, bundle);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle) {
        this(builder.nK, a(builder, -1), n, bundle);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle, final int n2) {
        this(builder.nK, a(builder, n2), n, bundle);
    }
    
    public DataHolder(final String[] array, final CursorWindow[] array2, final int mc, final Bundle nn) {
        this.mClosed = false;
        this.nR = true;
        this.kg = 1;
        this.nK = eg.f(array);
        this.nM = eg.f(array2);
        this.mC = mc;
        this.nN = nn;
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
        if (builder.nK.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList<CursorWindow> list2 = null;
        while (true) {
            final int size2;
            Label_0088: {
                List<Map<K, String>> list = null;
                Label_0037: {
                    if (i < 0 || i >= builder.nT.size()) {
                        list = (List<Map<K, String>>)builder.nT;
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
                            Label_0642:
                                while (true) {
                                Label_0635:
                                    while (true) {
                                        Label_0632: {
                                            try {
                                                if (cursorWindow.allocRow()) {
                                                    break Label_0632;
                                                }
                                                Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i + ")");
                                                cursorWindow = new CursorWindow(false);
                                                cursorWindow.setStartPosition(i);
                                                cursorWindow.setNumColumns(builder.nK.length);
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
                                                if (n3 >= builder.nK.length || !b) {
                                                    break;
                                                }
                                                s = builder.nK[n3];
                                                value = map.get(s);
                                                if (value == null) {
                                                    b = cursorWindow.putNull(n2, n3);
                                                    break Label_0635;
                                                }
                                                if (value instanceof String) {
                                                    b = cursorWindow.putString((String)value, n2, n3);
                                                    break Label_0635;
                                                }
                                                if (value instanceof Long) {
                                                    b = cursorWindow.putLong((long)value, n2, n3);
                                                    break Label_0635;
                                                }
                                                if (value instanceof Integer) {
                                                    b = cursorWindow.putLong((long)(int)value, n2, n3);
                                                    break Label_0635;
                                                }
                                                if (value instanceof Boolean) {
                                                    if (value) {
                                                        n4 = 1L;
                                                        b = cursorWindow.putLong(n4, n2, n3);
                                                        break Label_0635;
                                                    }
                                                    break Label_0642;
                                                }
                                                else {
                                                    if (value instanceof byte[]) {
                                                        b = cursorWindow.putBlob((byte[])(byte[])(Object)value, n2, n3);
                                                        break Label_0635;
                                                    }
                                                    throw new IllegalArgumentException("Unsupported object for column " + s + ": " + (Object)value);
                                                }
                                                list = builder.nT.subList(0, i);
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
                        cursorWindow.setNumColumns(builder.nK.length);
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
                cursorWindow.setNumColumns(builder.nK.length);
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
    
    private void b(final String s, final int n) {
        if (this.nL == null || !this.nL.containsKey(s)) {
            throw new IllegalArgumentException("No such column: " + s);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.nP) {
            throw new CursorIndexOutOfBoundsException(n, this.nP);
        }
    }
    
    public static Builder builder(final String[] array) {
        return new Builder(array, (String)null);
    }
    
    public static Builder builder(final String[] array, final String s) {
        eg.f(s);
        return new Builder(array, s);
    }
    
    public static DataHolder empty(final int n) {
        return empty(n, null);
    }
    
    public static DataHolder empty(final int n, final Bundle bundle) {
        return new DataHolder(DataHolder.nS, n, bundle);
    }
    
    public int C(int n) {
        int n2 = 0;
        eg.p(n >= 0 && n < this.nP);
        int n3;
        while (true) {
            n3 = n2;
            if (n2 >= this.nO.length) {
                break;
            }
            if (n < this.nO[n2]) {
                n3 = n2 - 1;
                break;
            }
            ++n2;
        }
        if ((n = n3) == this.nO.length) {
            n = n3 - 1;
        }
        return n;
    }
    
    String[] bv() {
        return this.nK;
    }
    
    CursorWindow[] bw() {
        return this.nM;
    }
    
    public void c(final Object nq) {
        this.nQ = nq;
    }
    
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.nM.length; ++i) {
                    this.nM[i].close();
                }
            }
        }
    }
    
    public void copyToBuffer(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.b(s, n);
        this.nM[n2].copyStringToBuffer(n, this.nL.getInt(s), charArrayBuffer);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.nR && this.nM.length > 0 && !this.isClosed()) {
                String s;
                if (this.nQ == null) {
                    s = "internal object: " + this.toString();
                }
                else {
                    s = this.nQ.toString();
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
        this.b(s, n);
        return Long.valueOf(this.nM[n2].getLong(n, this.nL.getInt(s))) == 1L;
    }
    
    public byte[] getByteArray(final String s, final int n, final int n2) {
        this.b(s, n);
        return this.nM[n2].getBlob(n, this.nL.getInt(s));
    }
    
    public int getCount() {
        return this.nP;
    }
    
    public int getInteger(final String s, final int n, final int n2) {
        this.b(s, n);
        return this.nM[n2].getInt(n, this.nL.getInt(s));
    }
    
    public long getLong(final String s, final int n, final int n2) {
        this.b(s, n);
        return this.nM[n2].getLong(n, this.nL.getInt(s));
    }
    
    public Bundle getMetadata() {
        return this.nN;
    }
    
    public int getStatusCode() {
        return this.mC;
    }
    
    public String getString(final String s, final int n, final int n2) {
        this.b(s, n);
        return this.nM[n2].getString(n, this.nL.getInt(s));
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public boolean hasColumn(final String s) {
        return this.nL.containsKey(s);
    }
    
    public boolean hasNull(final String s, final int n, final int n2) {
        this.b(s, n);
        return this.nM[n2].isNull(n, this.nL.getInt(s));
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
        this.nL = new Bundle();
        for (int i = 0; i < this.nK.length; ++i) {
            this.nL.putInt(this.nK[i], i);
        }
        this.nO = new int[this.nM.length];
        final int n2 = 0;
        int j = n;
        int np = n2;
        while (j < this.nM.length) {
            this.nO[j] = np;
            np += this.nM[j].getNumRows() - (np - this.nM[j].getStartPosition());
            ++j;
        }
        this.nP = np;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DataHolderCreator.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private final String[] nK;
        private final ArrayList<HashMap<String, Object>> nT;
        private final String nU;
        private final HashMap<Object, Integer> nV;
        private boolean nW;
        private String nX;
        
        private Builder(final String[] array, final String nu) {
            this.nK = eg.f(array);
            this.nT = new ArrayList<HashMap<String, Object>>();
            this.nU = nu;
            this.nV = new HashMap<Object, Integer>();
            this.nW = false;
            this.nX = null;
        }
        
        private void a(final HashMap<String, Object> hashMap) {
            final Object value = hashMap.get(this.nU);
            if (value == null) {
                return;
            }
            final Integer n = this.nV.remove(value);
            if (n != null) {
                this.nT.remove((int)n);
            }
            this.nV.put(value, this.nT.size());
        }
        
        private void bx() {
            if (this.nU != null) {
                this.nV.clear();
                for (int size = this.nT.size(), i = 0; i < size; ++i) {
                    final Object value = this.nT.get(i).get(this.nU);
                    if (value != null) {
                        this.nV.put(value, i);
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
            return this.nT.size();
        }
        
        public Builder removeRowsWithValue(final String s, final Object o) {
            for (int i = this.nT.size() - 1; i >= 0; --i) {
                if (ee.equal(this.nT.get(i).get(s), o)) {
                    this.nT.remove(i);
                }
            }
            return this;
        }
        
        public Builder sort(final String nx) {
            ds.d(nx);
            if (this.nW && nx.equals(this.nX)) {
                return this;
            }
            Collections.sort(this.nT, new a(nx));
            this.bx();
            this.nW = true;
            this.nX = nx;
            return this;
        }
        
        public Builder withRow(final ContentValues contentValues) {
            ds.d(contentValues);
            final HashMap<String, Object> hashMap = new HashMap<String, Object>(contentValues.size());
            for (final Map.Entry<Object, V> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return this.withRow(hashMap);
        }
        
        public Builder withRow(final HashMap<String, Object> hashMap) {
            ds.d(hashMap);
            if (this.nU != null) {
                this.a(hashMap);
            }
            this.nT.add(hashMap);
            this.nW = false;
            return this;
        }
    }
    
    private static final class a implements Comparator<HashMap<String, Object>>
    {
        private final String nY;
        
        a(final String s) {
            this.nY = eg.f(s);
        }
        
        public int a(final HashMap<String, Object> hashMap, final HashMap<String, Object> hashMap2) {
            final Boolean f = eg.f((Boolean)hashMap.get(this.nY));
            final String f2 = eg.f((String)hashMap2.get(this.nY));
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
