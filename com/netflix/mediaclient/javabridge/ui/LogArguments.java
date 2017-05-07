// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import org.json.JSONException;
import org.json.JSONObject;

public final class LogArguments
{
    public String logLevel;
    public String msg;
    public String[] tags;
    public String traceArea;
    public String type;
    
    public LogArguments(final String logLevel, final String msg, final String type, final String[] tags) {
        this.logLevel = logLevel;
        this.msg = msg;
        this.type = type;
        this.tags = tags;
    }
    
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("logLevel", (Object)this.logLevel);
        jsonObject.put("msg", (Object)this.msg);
        jsonObject.put("traceArea", (Object)this.traceArea);
        jsonObject.put("type", (Object)this.type);
        jsonObject.put("tags", (Object)this.tags);
        return jsonObject;
    }
    
    public enum LogLevel
    {
        CONSOLE(-1), 
        DEBUG(20), 
        ERROR(50), 
        FATAL(60), 
        INFO(30), 
        TRACE(10), 
        WARN(40);
        
        private int mLevel;
        
        private LogLevel(final int mLevel) {
            this.mLevel = mLevel;
        }
        
        public int getLevel() {
            return this.mLevel;
        }
        
        public String getLevelInString() {
            return Integer.toString(this.mLevel);
        }
    }
}
