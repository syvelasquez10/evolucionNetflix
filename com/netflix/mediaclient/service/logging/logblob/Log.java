// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments;

public class Log extends BaseLogblob
{
    private String mType;
    
    public Log(final LogArguments logArguments) {
        this.populate(logArguments);
    }
    
    private void populate(final LogArguments logArguments) {
        int i = 1;
        if (logArguments == null) {
            throw new IllegalStateException("Log arguments are null!");
        }
        this.mType = logArguments.type;
        this.mJson.put("logLevel", (Object)logArguments.logLevel.getLevelInString());
        this.mJson.put("msg", (Object)logArguments.msg);
        if (StringUtils.isNotEmpty(logArguments.traceArea)) {
            this.mJson.put("traceArea", (Object)logArguments.traceArea);
        }
        if (logArguments.tags != null && logArguments.tags.length > 0) {
            final StringBuilder sb = new StringBuilder(logArguments.tags[0]);
            if (logArguments.tags.length > 1) {
                while (i < logArguments.tags.length) {
                    sb.append(", ").append(logArguments.tags[i]);
                    ++i;
                }
            }
            this.mJson.put("traceArea", (Object)logArguments.traceArea);
        }
    }
    
    @Override
    public Logblob$Severity getSeverity() {
        return null;
    }
    
    @Override
    public String getType() {
        return this.mType;
    }
}
