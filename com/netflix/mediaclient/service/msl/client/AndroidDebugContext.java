// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.msl.keyx.KeyResponseData;
import java.util.Iterator;
import java.util.Set;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.tokens.MasterToken;
import java.nio.charset.Charset;
import com.netflix.msl.tokens.ServiceToken;
import com.netflix.msl.keyx.KeyRequestData;
import com.netflix.android.org.json.JSONArray;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.msg.MessageHeader;
import com.netflix.msl.msg.Header;
import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.msl.msg.ErrorHeader;
import com.netflix.msl.msg.MessageDebugContext;

public class AndroidDebugContext implements MessageDebugContext
{
    private static final String MASTER_TOKEN_SCHEME = "MASTER_TOKEN";
    private static final String TAG = "nf_msl";
    private static final String USER_ID_TOKEN_SCHEME = "USER_ID_TOKEN";
    
    private void errorHeader(final String s, final ErrorHeader errorHeader) {
        final JSONObject marshalErrorHeaderAsJson = this.marshalErrorHeaderAsJson(errorHeader);
        marshalErrorHeaderAsJson.put("direction", s);
        Log.d("nf_msl", "MSL Error Header {}:\n{}" + s + marshalErrorHeaderAsJson.toString(4));
    }
    
    private void logHeader(final String s, final Header header) {
        try {
            if (header instanceof MessageHeader) {
                this.messageHeader(s, (MessageHeader)header);
                return;
            }
            if (header instanceof ErrorHeader) {
                this.errorHeader(s, (ErrorHeader)header);
                return;
            }
        }
        catch (Exception ex) {
            Log.e("nf_msl", ex, "Unable to marshal header in " + s, new Object[0]);
            return;
        }
        Log.e("nf_msl", "Unknown Header type " + header.getClass().toString() + " during " + s);
    }
    
    private void marshalEntityAuth(final JSONObject jsonObject, final EntityAuthenticationData entityAuthenticationData) {
        if (entityAuthenticationData == null) {
            return;
        }
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("scheme", entityAuthenticationData.getScheme().name());
            jsonObject2.put("identity", entityAuthenticationData.getIdentity());
            jsonObject2.put("authdata", entityAuthenticationData.getAuthData());
            jsonObject.put("entityauthdata", jsonObject2);
        }
        catch (MslCryptoException ex) {
            jsonObject.put("entityauthdata", "exception");
        }
        catch (MslEncodingException ex2) {
            jsonObject.put("entityauthdata", "exception");
        }
    }
    
    private JSONObject marshalErrorHeaderAsJson(final ErrorHeader errorHeader) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("errormessage", errorHeader.getErrorMessage());
        jsonObject.put("recipient", errorHeader.getRecipient());
        jsonObject.put("internalcode", errorHeader.getInternalCode());
        jsonObject.put("messageid", errorHeader.getMessageId());
        jsonObject.put("errorcode", errorHeader.getErrorCode());
        jsonObject.put("usermessage", errorHeader.getUserMessage());
        jsonObject.put("timestamp", errorHeader.getTimestamp());
        this.marshalEntityAuth(jsonObject, errorHeader.getEntityAuthenticationData());
        return jsonObject;
    }
    
    private JSONObject marshalHeaderAsJson(final MessageHeader messageHeader) {
        final JSONObject jsonObject = new JSONObject();
        this.marshalEntityAuth(jsonObject, messageHeader.getEntityAuthenticationData());
        final MasterToken masterToken = messageHeader.getMasterToken();
        if (masterToken != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("identity", masterToken.getIdentity());
            jsonObject2.put("encryptionkey", masterToken.getEncryptionKey());
            jsonObject2.put("expiration", masterToken.getExpiration().toString());
            jsonObject2.put("issuerdata", masterToken.getIssuerData());
            jsonObject2.put("renewalwindow", masterToken.getRenewalWindow());
            jsonObject2.put("seqnum", masterToken.getSequenceNumber());
            jsonObject2.put("sigkey", masterToken.getSignatureKey());
            jsonObject2.put("serialnum", masterToken.getSerialNumber());
            jsonObject.put("mastertokendata", jsonObject2);
        }
        final UserAuthenticationData userAuthenticationData = messageHeader.getUserAuthenticationData();
        if (userAuthenticationData != null) {
            new JSONObject(userAuthenticationData.toJSONString());
            jsonObject.put("userauthdata", userAuthenticationData);
        }
        final UserIdToken userIdToken = messageHeader.getUserIdToken();
        if (userIdToken != null) {
            final JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("serialnumber", userIdToken.getSerialNumber());
            jsonObject3.put("renewalwindow", userIdToken.getRenewalWindow());
            jsonObject3.put("issuerdata", userIdToken.getIssuerData());
            jsonObject3.put("expiration", userIdToken.getExpiration());
            jsonObject3.put("mastertokenserialnumber", userIdToken.getMasterTokenSerialNumber());
            if (userIdToken.getUser() != null) {
                jsonObject3.put("user", userIdToken.getUser().getEncoded());
            }
            jsonObject.put("userdata", jsonObject3);
        }
        jsonObject.put("renewable", messageHeader.isRenewable());
        jsonObject.put("decrypted", messageHeader.isDecrypted());
        jsonObject.put("encrypting", messageHeader.isEncrypting());
        jsonObject.put("handshake", messageHeader.isHandshake());
        jsonObject.put("verified", messageHeader.isVerified());
        jsonObject.put("messageid", messageHeader.getMessageId());
        if (messageHeader.getUser() != null) {
            jsonObject.put("user", messageHeader.getUser().getEncoded());
        }
        jsonObject.put("nonreplayableid", messageHeader.getNonReplayableId());
        jsonObject.put("recipient", messageHeader.getRecipient());
        if (messageHeader.getMessageCapabilities() != null) {
            jsonObject.put("messagecapabilities", new JSONObject(messageHeader.getMessageCapabilities().toJSONString()));
        }
        final Set keyRequestData = messageHeader.getKeyRequestData();
        if (keyRequestData != null) {
            final JSONArray jsonArray = new JSONArray();
            final Iterator<KeyRequestData> iterator = keyRequestData.iterator();
            while (iterator.hasNext()) {
                jsonArray.put(new JSONObject(iterator.next().toJSONString()));
            }
            jsonObject.put("keyrequests", jsonArray);
        }
        final KeyResponseData keyResponseData = messageHeader.getKeyResponseData();
        if (keyResponseData != null) {
            jsonObject.put("keyresponse", new JSONObject(keyResponseData.toJSONString()));
        }
        final Set serviceTokens = messageHeader.getServiceTokens();
        final JSONArray jsonArray2 = new JSONArray();
        for (final ServiceToken serviceToken : serviceTokens) {
            final JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("verified", serviceToken.isVerified());
            jsonObject4.put("decrypted", serviceToken.isDecrypted());
            jsonObject4.put("encrypted", serviceToken.isEncrypted());
            jsonObject4.put("unbound", serviceToken.isUnbound());
            jsonObject4.put("deleted", serviceToken.isDeleted());
            jsonObject4.put("mastertokenserialnumber", serviceToken.getMasterTokenSerialNumber());
            jsonObject4.put("name", serviceToken.getName());
            final byte[] data = serviceToken.getData();
            if (data != null && data.length > 0) {
                jsonObject4.put("data", new String(data, Charset.forName("UTF-8")));
            }
            jsonArray2.put(jsonObject4);
        }
        jsonObject.put("servicetokens", jsonArray2);
        return jsonObject;
    }
    
    private void messageHeader(final String s, final MessageHeader messageHeader) {
        final JSONObject marshalHeaderAsJson = this.marshalHeaderAsJson(messageHeader);
        marshalHeaderAsJson.put("direction", s);
        Log.d("nf_msl", "MSL Message Header {}:\n{}" + s + marshalHeaderAsJson.toString(4));
    }
    
    public void receivedHeader(final Header header) {
        if (Log.isLoggable()) {
            this.logHeader("Receive", header);
        }
    }
    
    public void sentHeader(final Header header) {
        if (Log.isLoggable()) {
            this.logHeader("Sent", header);
        }
    }
}
