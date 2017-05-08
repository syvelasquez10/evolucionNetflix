// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Iterator;
import java.util.Map;

public class IMSLClient$MSLApiUnwrappedParams
{
    public String method;
    public Map<String, String> mslHeaders;
    public String mslPayload;
    public String mslQuery;
    public String uri;
    
    public IMSLClient$MSLApiUnwrappedParams(final String uri, final String method, final Map<String, String> mslHeaders, final String mslQuery, final String mslPayload) {
        this.uri = uri;
        this.method = method;
        this.mslHeaders = mslHeaders;
        this.mslQuery = mslQuery;
        this.mslPayload = mslPayload;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MSLApiUnwrappedParams{ uri=").append(this.uri).append(", method='").append(this.method).append(", additional headers=");
        if (this.mslHeaders == null || this.mslHeaders.size() < 1) {
            sb.append("null");
        }
        else {
            sb.append("{");
            final Iterator<String> iterator = this.mslHeaders.keySet().iterator();
            int n = 1;
            while (iterator.hasNext()) {
                final String s = iterator.next();
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(", ");
                }
                sb.append(s).append('=').append(this.mslHeaders.get(s));
            }
            sb.append("}");
        }
        sb.append(", query='").append(this.mslQuery).append(", payload='").append(this.mslPayload).append("}");
        return sb.toString();
    }
}
