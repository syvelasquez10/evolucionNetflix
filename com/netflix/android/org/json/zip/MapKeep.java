// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import com.netflix.android.org.json.Kim;
import java.util.HashMap;

class MapKeep extends Keep
{
    private Object[] list;
    private HashMap map;
    
    public MapKeep(final int n) {
        super(n);
        this.list = new Object[this.capacity];
        this.map = new HashMap(this.capacity);
    }
    
    private void compact() {
        int length = 0;
        for (int i = 0; i < this.capacity; ++i) {
            final Object o = this.list[i];
            final long age = Keep.age(this.uses[i]);
            if (age > 0L) {
                this.uses[length] = age;
                this.list[length] = o;
                this.map.put(o, new Integer(length));
                ++length;
            }
            else {
                this.map.remove(o);
            }
        }
        if (length < this.capacity) {
            this.length = length;
        }
        else {
            this.map.clear();
            this.length = 0;
        }
        this.power = 0;
    }
    
    public int find(Object value) {
        value = this.map.get(value);
        if (value instanceof Integer) {
            return (int)value;
        }
        return -1;
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        final MapKeep mapKeep = (MapKeep)postMortem;
        if (this.length != mapKeep.length) {
            JSONzip.log(this.length + " <> " + mapKeep.length);
            return false;
        }
        for (int i = 0; i < this.length; ++i) {
            boolean b;
            if (this.list[i] instanceof Kim) {
                b = ((Kim)this.list[i]).equals(mapKeep.list[i]);
            }
            else {
                final Object o = this.list[i];
                final Object o2 = mapKeep.list[i];
                Object string = o;
                if (o instanceof Number) {
                    string = o.toString();
                }
                Object string2 = o2;
                if (o2 instanceof Number) {
                    string2 = o2.toString();
                }
                b = string.equals(string2);
            }
            if (!b) {
                JSONzip.log("\n[" + i + "]\n " + this.list[i] + "\n " + mapKeep.list[i] + "\n " + this.uses[i] + "\n " + mapKeep.uses[i]);
                return false;
            }
        }
        return true;
    }
    
    public void register(final Object o) {
        if (this.length >= this.capacity) {
            this.compact();
        }
        this.list[this.length] = o;
        this.map.put(o, new Integer(this.length));
        this.uses[this.length] = 1L;
        ++this.length;
    }
    
    @Override
    public Object value(final int n) {
        return this.list[n];
    }
}
