package com.example;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ApiRequestHandler {
    private String algorithm;
    private RequestParam requestParam;
    private String secret_key;

    public ApiRequestHandler(RequestParam requestParam, String secret_key) {
        this.algorithm = "HmacSHA256";
        this.requestParam = requestParam;
        this.secret_key = secret_key;
    }

    public ApiRequestHandler(RequestParam requestParam, String secret_key, String algorithm) {
        this.algorithm = algorithm;
        this.requestParam = requestParam;
        this.secret_key = secret_key;
    }

    /**
     * Create Hmac signature for request parameters.
     */
     public String createHmacSignature() {
        try {
            SecretKeySpec sk = new SecretKeySpec(secret_key.getBytes(), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);

            byte[] mac_bytes = mac.doFinal(requestParam.payload.getBytes());

            String signature = "";
            for (byte b : mac_bytes) {
                signature += String.format("%02x", b & 0xff);
            }

            return signature;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * check if request has valid signature from valid sender.
     *
     */
    public Boolean isValidRequest(){
        if(!checkAccessKey()){
            return false;
        }
        if(!checkNonce()){
            return false;
        }
        if(!checkSignature()){
            return false;
        }
        return true;
    }

    private Boolean checkAccessKey(){
        //TODO
        String accsess_key = requestParam.accsess_key;
        //find accesskey from key list?
        return true;
    }

    private Boolean checkNonce(){
        //TODO
//        String accsess_key = requestParam.accsess_key;
        //find accesskey from key list?
        return true;
    }
    private Boolean checkSignature(){
        String signature = createHmacSignature();
        return requestParam.signature.equals(signature);
    }
}
