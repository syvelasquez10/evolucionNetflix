// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookServiceException extends FacebookException
{
    private final FacebookRequestError error;
    
    public FacebookServiceException(final FacebookRequestError error, final String s) {
        super(s);
        this.error = error;
    }
    
    public final FacebookRequestError getRequestError() {
        return this.error;
    }
    
    @Override
    public final String toString() {
        return "{FacebookServiceException: " + "httpResponseCode: " + this.error.getRequestStatusCode() + ", facebookErrorCode: " + this.error.getErrorCode() + ", facebookErrorType: " + this.error.getErrorType() + ", message: " + this.error.getErrorMessage() + "}";
    }
}
