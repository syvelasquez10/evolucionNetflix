// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

public final class ApiHttpWrapper
{
    private final byte[] data;
    private final Map<String, String> headers;
    private final int status;
    private final String version;
    
    public ApiHttpWrapper(final String version, final Map<String, String> headers, final int status, final byte[] data) {
        this.version = version;
        this.headers = headers;
        this.status = status;
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ApiHttpWrapper)) {
                return false;
            }
            final ApiHttpWrapper apiHttpWrapper = (ApiHttpWrapper)o;
            final String version = this.getVersion();
            final String version2 = apiHttpWrapper.getVersion();
            Label_0049: {
                if (version == null) {
                    if (version2 == null) {
                        break Label_0049;
                    }
                }
                else if (version.equals(version2)) {
                    break Label_0049;
                }
                return false;
            }
            final Map<String, String> headers = this.getHeaders();
            final Map<String, String> headers2 = apiHttpWrapper.getHeaders();
            Label_0077: {
                if (headers == null) {
                    if (headers2 == null) {
                        break Label_0077;
                    }
                }
                else if (headers.equals(headers2)) {
                    break Label_0077;
                }
                return false;
            }
            if (this.getStatus() != apiHttpWrapper.getStatus()) {
                return false;
            }
            if (!Arrays.equals(this.getData(), apiHttpWrapper.getData())) {
                return false;
            }
        }
        return true;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public String getDataAsString() {
        return new String(this.data, Charset.forName("UTF-8"));
    }
    
    public Map<String, String> getHeaders() {
        return this.headers;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 43;
        final String version = this.getVersion();
        int hashCode2;
        if (version == null) {
            hashCode2 = 43;
        }
        else {
            hashCode2 = version.hashCode();
        }
        final Map<String, String> headers = this.getHeaders();
        if (headers != null) {
            hashCode = headers.hashCode();
        }
        return (((hashCode2 + 59) * 59 + hashCode) * 59 + this.getStatus()) * 59 + Arrays.hashCode(this.getData());
    }
    
    @Override
    public String toString() {
        return "ApiHttpWrapper(version=" + this.getVersion() + ", headers=" + this.getHeaders() + ", status=" + this.getStatus() + ", data=" + Arrays.toString(this.getData()) + ")";
    }
}
