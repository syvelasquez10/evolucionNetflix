// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import com.android.volley.NetworkError;
import com.android.volley.Cache$Entry;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.io.IOException;
import com.android.volley.VolleyLog;
import com.android.volley.NetworkResponse;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Request$Priority;
import com.android.volley.Request;

public abstract class ProgressiveRequest extends Request<Void>
{
    private static final boolean DEBUG_CONTROL_NETWORK_SPEED = false;
    private static final int IO_SIZE = 8192;
    private static final int SOCKET_READ_TIMEOUT = 10000;
    private static final String TAG = "nf_download_prog_req";
    private final byte[] mByteArray;
    private ProgressiveRequestListener mListener;
    private final Request$Priority mPriority;
    
    public ProgressiveRequest(final String s, final Request$Priority mPriority) {
        super(0, s, null);
        this.mPriority = mPriority;
        this.setShouldCache(false);
        this.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 1.0f));
        this.mByteArray = new byte[8192];
    }
    
    private void closeResponse(final NetworkResponse networkResponse) {
        try {
            ((ProgressiveNetworkResponse)networkResponse).getHttpEntity().consumeContent();
            this.releaseResources();
        }
        catch (IOException ex) {
            VolleyLog.e("Error occurred when calling consumingContent", new Object[0]);
        }
    }
    
    @Override
    public void deliverError(final VolleyError volleyError) {
        final ProgressiveRequestListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onError(volleyError);
        }
    }
    
    @Override
    protected void deliverResponse(final Void void1) {
    }
    
    @Override
    public Request$Priority getPriority() {
        return this.mPriority;
    }
    
    protected abstract void onResponseStart(final long p0);
    
    @Override
    protected Response<Void> parseNetworkResponse(final NetworkResponse networkResponse) {
        if (this.isCanceled()) {
            this.closeResponse(networkResponse);
            return Response.success((Void)null, null);
        }
        Response<Void> response;
        if (networkResponse == null) {
            response = Response.error(new VolleyError("Network response is null"));
        }
        else if (!(networkResponse instanceof ProgressiveNetworkResponse)) {
            response = Response.error(new VolleyError("Expecting ProgressiveNetworkResponse but got=" + networkResponse));
        }
        else {
            while (true) {
                final HttpEntity httpEntity = ((ProgressiveNetworkResponse)networkResponse).getHttpEntity();
                this.onResponseStart(httpEntity.getContentLength());
            Label_0150_Outer:
                while (true) {
                    while (true) {
                        int read = 0;
                        Label_0227: {
                            try {
                                final InputStream content = httpEntity.getContent();
                                if (!this.isCanceled()) {
                                    read = content.read(this.mByteArray);
                                    if (this.mListener != null) {
                                        this.mListener.onNext(this.mByteArray, read);
                                    }
                                    break Label_0227;
                                }
                                else {
                                    if (content != null) {
                                        content.close();
                                    }
                                    response = Response.success((Void)null, null);
                                }
                            }
                            catch (IOException ex) {
                                VolleyLog.e("nf_download_prog_req", "parseNetworkResponse I/O error " + ex.toString());
                                response = Response.error(new VolleyError(new NetworkError(ex)));
                            }
                            break;
                        }
                        if (read < 0) {
                            continue;
                        }
                        break;
                    }
                    continue Label_0150_Outer;
                }
            }
        }
        this.closeResponse(networkResponse);
        return response;
    }
    
    protected void setProgressiveRequestListener(final ProgressiveRequestListener mListener) {
        this.mListener = mListener;
    }
}
