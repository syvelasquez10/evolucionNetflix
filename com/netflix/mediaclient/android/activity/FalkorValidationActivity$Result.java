// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.StringUtils;

class FalkorValidationActivity$Result extends Exception
{
    private static final FalkorValidationActivity$Result EXCEPTION;
    private static final FalkorValidationActivity$Result INTERFACE_NOT_FOUND_IN_MAP;
    private static final FalkorValidationActivity$Result INTERFACE_NOT_IMPLEMENTED;
    private static final FalkorValidationActivity$Result INVOCATION_EXCEPTION;
    private static final FalkorValidationActivity$Result LIST_SIZE_MISMATCH;
    private static final FalkorValidationActivity$Result NULL_LIST;
    private static final FalkorValidationActivity$Result NULL_OBJECT;
    private static final FalkorValidationActivity$Result OK;
    private static final FalkorValidationActivity$Result UNKNOWN;
    private static final FalkorValidationActivity$Result VALUE_MISMATCH;
    private final String msg;
    private final FalkorValidationActivity$Result$ResultType type;
    
    static {
        OK = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.OK);
        UNKNOWN = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.NULL);
        EXCEPTION = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.EXCEPTION);
        NULL_OBJECT = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.NULL_OBJECT);
        LIST_SIZE_MISMATCH = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.LIST_SIZE_MISMATCH);
        NULL_LIST = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.NULL_LIST);
        INVOCATION_EXCEPTION = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.INVOCATION_EXCEPTION);
        VALUE_MISMATCH = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.VALUE_MISMATCH);
        INTERFACE_NOT_FOUND_IN_MAP = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.INTERFACE_NOT_FOUND_IN_MAP);
        INTERFACE_NOT_IMPLEMENTED = new FalkorValidationActivity$Result(FalkorValidationActivity$Result$ResultType.INTERFACE_NOT_IMPLEMENTED);
    }
    
    public FalkorValidationActivity$Result(final FalkorValidationActivity$Result$ResultType falkorValidationActivity$Result$ResultType) {
        this(falkorValidationActivity$Result$ResultType, null);
    }
    
    public FalkorValidationActivity$Result(final FalkorValidationActivity$Result$ResultType type, final String msg) {
        this.type = type;
        this.msg = msg;
    }
    
    public FalkorValidationActivity$Result append(final String s) {
        String string = s;
        if (StringUtils.isNotEmpty(this.msg)) {
            string = this.msg + ", " + s;
        }
        return new FalkorValidationActivity$Result(this.type, string);
    }
    
    @Override
    public String getMessage() {
        return this.toString();
    }
    
    public boolean isError() {
        return !this.isSucces();
    }
    
    public boolean isSucces() {
        return this.type == FalkorValidationActivity$Result$ResultType.OK;
    }
    
    @Override
    public String toString() {
        return "Result [type=" + this.type + ", msg=" + this.msg + "]";
    }
}
