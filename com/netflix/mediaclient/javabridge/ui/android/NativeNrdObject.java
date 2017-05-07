// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.CallbackEvent;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import java.util.List;
import java.util.Map;
import com.netflix.mediaclient.javabridge.ui.Callback;
import android.util.SparseArray;
import com.netflix.mediaclient.javabridge.ui.BaseNrdObject;

public abstract class NativeNrdObject extends BaseNrdObject
{
    protected static final String TAG = "nf_object";
    protected SparseArray<Callback> callbacks;
    protected Map<String, List<EventListener>> listeners;
    protected int nextCallbackId;
    
    public NativeNrdObject(final Bridge bridge) {
        super(bridge);
        this.listeners = new HashMap<String, List<EventListener>>();
        this.callbacks = (SparseArray<Callback>)new SparseArray();
    }
    
    public int addCallback(final Callback callback) {
        synchronized (this) {
            ++this.nextCallbackId;
            this.callbacks.append(this.nextCallbackId, (Object)callback);
            return this.nextCallbackId;
        }
    }
    
    public void addEventListener(final String s, final EventListener eventListener) {
        // monitorenter(this)
        Label_0043: {
            if (eventListener != null) {
                break Label_0043;
            }
            while (true) {
                try {
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.w("nf_object", "Do not add! Listener is null for event type " + s);
                    }
                    return;
                    while (true) {
                        Log.e("nf_object", "Event type is null. Do not add event listener! It should NOT happen!");
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0063:, s != null)
                finally {
                }
                // monitorexit(this)
                final String s2;
                final List<EventListener> list;
                Label_0063: {
                    list = this.listeners.get(s2);
                }
                List<EventListener> list2;
                if (list == null) {
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.d("nf_object", "Listeners not found for event type " + s2);
                    }
                    list2 = new ArrayList<EventListener>();
                    this.listeners.put(s2, list2);
                }
                else {
                    list2 = list;
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.d("nf_object", "Listeners found for event type " + s2 + ": " + list.size());
                        list2 = list;
                    }
                }
                list2.add(eventListener);
            }
        }
    }
    
    protected int handleCallback(final CallbackEvent callbackEvent) {
        synchronized (this) {
            final int callerId = callbackEvent.getCallerId();
            final Callback removeCallback = this.removeCallback(callerId);
            if (removeCallback != null) {
                if (Log.isLoggable("nf_object", 3)) {
                    Log.d("nf_object", "Callback found: " + callerId);
                }
                removeCallback.done(callbackEvent);
                Log.d("nf_object", "Callback executed.");
            }
            else if (Log.isLoggable("nf_object", 3)) {
                Log.e("nf_object", "Callback not found for key " + callerId);
            }
            return 1;
        }
    }
    
    protected int handleListener(final String s, final UIEvent uiEvent) {
        synchronized (this) {
            final List<EventListener> list = this.listeners.get(s);
            if (list == null) {
                if (Log.isLoggable("nf_object", 3)) {
                    Log.d("nf_object", "Listeners not found for event type " + s);
                }
            }
            else {
                final Iterator<EventListener> iterator = list.iterator();
                while (iterator.hasNext()) {
                    iterator.next().received(uiEvent);
                }
            }
            return 1;
        }
    }
    
    protected int handleNccpEvent(final String s, final JSONObject jsonObject) {
        if (Log.isLoggable("nf_object", 3)) {
            Log.d("nf_object", "NCCP event " + s);
        }
        if (jsonObject.has("origin") || jsonObject.getString("origin").equalsIgnoreCase("complete")) {
            Log.d("nf_object", "NCCP event with origin equal complete. Ignore.");
            return 1;
        }
        Log.d("nf_object", "NCCP event: handle by implementation");
        return 0;
    }
    
    protected void invokeMethodWithCallback(final String s, final String s2, final JSONObject jsonObject, final Callback callback) {
        JSONObject jsonObject2 = jsonObject;
        Label_0016: {
            if (jsonObject != null) {
                break Label_0016;
            }
            try {
                jsonObject2 = new JSONObject();
                jsonObject2.put("idx", this.addCallback(callback));
                this.bridge.getNrdProxy().invokeMethod(s, s2, jsonObject2.toString());
            }
            catch (JSONException ex) {
                Log.e("nf_object", "Create device account failed because of ", (Throwable)ex);
            }
        }
    }
    
    public Callback removeCallback(final int n) {
        synchronized (this) {
            final Callback callback = (Callback)this.callbacks.get(n);
            this.callbacks.delete(n);
            return callback;
        }
    }
    
    public void removeEventListener(final String s, final EventListener eventListener) {
        // monitorenter(this)
        Label_0043: {
            if (eventListener != null) {
                break Label_0043;
            }
            while (true) {
                try {
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.w("nf_object", "Do not remove! Listener is null for event type " + s);
                    }
                    return;
                    // iftrue(Label_0063:, s != null)
                    Log.e("nf_object", "Event type is null. Can not remove event listener! It should NOT happen!");
                    return;
                }
                finally {
                }
                // monitorexit(this)
                final String s2;
                final List<EventListener> list;
                Label_0063: {
                    list = this.listeners.get(s2);
                }
                if (list == null) {
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.d("nf_object", "Listeners not found for event type " + s2);
                    }
                }
                else {
                    final boolean remove = list.remove(eventListener);
                    if (Log.isLoggable("nf_object", 3)) {
                        Log.d("nf_object", "Listeners found for event type " + s2 + ": " + list.size() + " and listener was " + remove);
                    }
                }
            }
        }
    }
}
