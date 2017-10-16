package com.example.hmac;

/**
 * REST API認証処理のサンプル
 * json format
 * {
 *       key: "1234abc",
 *       nonce: 1234,
 *       signature: "SIGNATURE",
 *       payload: "{JSON}" (String)
 *       }
 *
 */

public class HmacSample {
    public static void main(String[] args) {
        String accsess_key = "accsesskey1";
        String secret_key = "akhjla";
        int nonce = 122121;
        String payload = "test payload"; // json.toString();

        //リクエストパラメータのオブジェクト作成
        RequestParam requestParam = new RequestParam(accsess_key, nonce, payload);

        //この形式のJSONをリクエストBDOYまたはGETで送りたい。
        ApiRequestHandler apiHandler = new ApiRequestHandler(requestParam, secret_key);
        String sig = apiHandler.createHmacSignature();
        System.out.println(sig);

//        String secret_key2 = "aa";

        //署名の認証
        RequestParam requestParam2 = new RequestParam(accsess_key, nonce, payload, sig);
        ApiRequestHandler apiHandler2 = new ApiRequestHandler(requestParam2, secret_key);
//        ApiRequestHandler apiHandler2 = new ApiRequestHandler(requestParam2, secret_key2, "aaa");

        if(apiHandler2.isValidRequest()){
            System.out.println("valid signature");
        }else{
            System.out.println("invalid signature");
        }
    }
}
