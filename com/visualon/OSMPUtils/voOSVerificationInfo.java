// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSVerificationInfo
{
    private int DataFlag;
    private int DataSize;
    private byte[] ResponseData;
    private int ResponseDataSize;
    private int UserData;
    private String VerificationData;
    
    public voOSVerificationInfo() {
        this.UserData = 0;
        this.VerificationData = null;
        this.DataSize = 0;
        this.DataFlag = 0;
        this.ResponseData = null;
        this.ResponseDataSize = 0;
    }
    
    public voOSVerificationInfo(final int userData, final String verificationData, final int dataFlag, final byte[] responseData) {
        this.UserData = userData;
        this.VerificationData = verificationData;
        this.DataFlag = dataFlag;
        this.ResponseData = responseData;
    }
    
    public int getDataFlag() {
        return this.DataFlag;
    }
    
    public byte[] getResponseData() {
        return this.ResponseData;
    }
    
    public int getUserData() {
        return this.UserData;
    }
    
    public String getVerificationData() {
        return this.VerificationData;
    }
    
    public void setDataFlag(final int dataFlag) {
        this.DataFlag = dataFlag;
    }
    
    public void setResponseData(final byte[] responseData) {
        this.ResponseData = responseData;
    }
    
    public void setUserData(final int userData) {
        this.UserData = userData;
    }
    
    public void setVerificationData(final String verificationData) {
        this.VerificationData = verificationData;
    }
}
