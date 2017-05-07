// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson;

import java.util.Map;
import java.util.Set;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.StringMap;

public final class JsonObject extends JsonElement
{
    private final StringMap<JsonElement> members;
    
    public JsonObject() {
        this.members = new StringMap<JsonElement>();
    }
    
    private JsonElement createJsonElement(final Object o) {
        if (o == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(o);
    }
    
    public void add(final String s, final JsonElement jsonElement) {
        JsonElement instance = jsonElement;
        if (jsonElement == null) {
            instance = JsonNull.INSTANCE;
        }
        this.members.put($Gson$Preconditions.checkNotNull(s), instance);
    }
    
    public void addProperty(final String s, final Boolean b) {
        this.add(s, this.createJsonElement(b));
    }
    
    public void addProperty(final String s, final Character c) {
        this.add(s, this.createJsonElement(c));
    }
    
    public void addProperty(final String s, final Number n) {
        this.add(s, this.createJsonElement(n));
    }
    
    public void addProperty(final String s, final String s2) {
        this.add(s, this.createJsonElement(s2));
    }
    
    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof JsonObject && ((JsonObject)o).members.equals(this.members));
    }
    
    public JsonElement get(final String s) {
        if (this.members.containsKey(s)) {
            JsonElement instance;
            if ((instance = this.members.get(s)) == null) {
                instance = JsonNull.INSTANCE;
            }
            return instance;
        }
        return null;
    }
    
    public JsonArray getAsJsonArray(final String s) {
        return this.members.get(s);
    }
    
    public JsonObject getAsJsonObject(final String s) {
        return this.members.get(s);
    }
    
    public JsonPrimitive getAsJsonPrimitive(final String s) {
        return this.members.get(s);
    }
    
    public boolean has(final String s) {
        return this.members.containsKey(s);
    }
    
    @Override
    public int hashCode() {
        return this.members.hashCode();
    }
    
    public JsonElement remove(final String s) {
        return this.members.remove(s);
    }
}
