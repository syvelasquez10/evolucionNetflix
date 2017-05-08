// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.MslException;
import com.netflix.msl.MslInternalException;
import java.io.OutputStream;
import com.netflix.msl.util.MslContext;
import java.util.concurrent.Callable;

class MslControl$ErrorService implements Callable<Boolean>
{
    private final MslControl$ApplicationError appError;
    private final MslContext ctx;
    private final MessageContext msgCtx;
    private final OutputStream out;
    private final MessageInputStream request;
    final /* synthetic */ MslControl this$0;
    
    public MslControl$ErrorService(final MslControl this$0, final MslContext ctx, final MessageContext msgCtx, final MslControl$ApplicationError appError, final OutputStream out, final MessageInputStream request) {
        this.this$0 = this$0;
        if (request.getErrorHeader() != null) {
            throw new MslInternalException("Error service created for an error message.");
        }
        this.ctx = ctx;
        this.msgCtx = msgCtx;
        this.appError = appError;
        this.out = out;
        this.request = request;
    }
    
    @Override
    public Boolean call() {
        while (true) {
            this.msgCtx.getDebugContext();
            this.request.getMessageHeader();
            while (true) {
                Label_0274: {
                    try {
                        switch (MslControl$1.$SwitchMap$com$netflix$msl$msg$MslControl$ApplicationError[this.appError.ordinal()]) {
                            case 1: {
                                goto Label_0100;
                                goto Label_0100;
                            }
                            case 2: {
                                goto Label_0221;
                                goto Label_0221;
                            }
                            default: {
                                break Label_0274;
                            }
                        }
                        throw new MslInternalException("Unhandled application error " + this.appError + ".");
                    }
                    catch (MslException ex) {
                        if (MslControl.cancelled(ex)) {
                            return false;
                        }
                        goto Label_0248;
                    }
                    catch (Throwable t) {
                        if (MslControl.cancelled(t)) {
                            return false;
                        }
                        throw new MslInternalException("Error building the error response.", t);
                    }
                }
                continue;
            }
        }
    }
}
