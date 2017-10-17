package com.example.hmac;

public class RequestParam {
    String accsess_key;
    int nonce;
    String signature;
    String payload;

    public RequestParam(String accsess_key, int nonce, String payload,String signature) {
        this.accsess_key = accsess_key;
        this.nonce = nonce;
        this.signature = signature;
        this.payload = payload;
    }

    public RequestParam(String accsess_key, int nonce, String payload) {
        this.accsess_key = accsess_key;
        this.nonce = nonce;
        this.payload = payload;
    }
}
