// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class PathBoundValue<T> implements Comparable<T>, PathBound
{
    private final Gson gson;
    private final PQL path;
    private final Option<Object> value;
    
    public PathBoundValue(final PQL path, final Option<Object> value) {
        this.gson = new GsonBuilder().registerTypeAdapter(PQL.class, new PQLAdapter()).create();
        this.path = path;
        this.value = value;
    }
    
    @Override
    public int compareTo(final T t) {
        if (!(t instanceof PathBoundValue)) {
            throw new ClassCastException("Invalid object");
        }
        final List<Object> keySegments = ((PathBoundValue)t).getPath().getKeySegments();
        final List<Object> keySegments2 = this.getPath().getKeySegments();
        if (keySegments2.size() != keySegments.size()) {
            throw new ClassCastException("Invalid object");
        }
        for (int i = 1; i < keySegments2.size() - 1; ++i) {
            final int compareTo = Integer.valueOf(keySegments2.get(i).toString()).compareTo(Integer.valueOf(keySegments.get(i).toString()));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }
    
    @Override
    public PQL getPath() {
        return this.path;
    }
    
    public Option<Object> getValue() {
        return this.value;
    }
    
    @Override
    public void setPath(final PQL pql) {
        throw new UnsupportedOperationException("Path is immutable");
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder("new PathBoundValue(").append(this.gson.toJson(this.getPath()));
        if (this.getValue().getHasValue()) {
            append.append(", ").append(this.gson.toJson(this.getValue().getValue()));
        }
        append.append(")");
        return append.toString();
    }
}
