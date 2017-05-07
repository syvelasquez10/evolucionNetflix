// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

public abstract class ResponseWebApiPostCommand extends WebApiPostCommand
{
    public ResponseWebApiPostCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    public String execute() {
        return this.doExecute();
    }
}
