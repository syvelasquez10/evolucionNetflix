// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.handshake;

import java.util.Set;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeMap;

public class HandshakedataImpl1 implements HandshakeBuilder
{
    private byte[] content;
    private TreeMap<String, String> map;
    
    public HandshakedataImpl1() {
        this.map = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    }
    
    @Override
    public byte[] getContent() {
        return this.content;
    }
    
    @Override
    public String getFieldValue(String s) {
        if ((s = this.map.get(s)) == null) {
            s = "";
        }
        return s;
    }
    
    @Override
    public boolean hasFieldValue(final String s) {
        return this.map.containsKey(s);
    }
    
    @Override
    public Iterator<String> iterateHttpFields() {
        return Collections.unmodifiableSet((Set<? extends String>)this.map.keySet()).iterator();
    }
    
    @Override
    public void put(final String s, final String s2) {
        this.map.put(s, s2);
    }
    
    @Override
    public void setContent(final byte[] content) {
        this.content = content;
    }
}
