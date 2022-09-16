## Didipay payment merchant java sdk

Official website (https://didipay.didiglobal.com)

## Dependencies
Java7 or higher is required


## Signature process

Get all request parameters, excluding byte-type parameters, such as files and byte streams, remove the sign field, and sort them in ascending order according to the key-value ASCII code of the first character (in ascending alphabetical order). The two-character key-value ASCII codes are sorted in ascending order, and so on.

Filter and sort

Splicing: Combine the sorted parameters and their corresponding values ​​into the format of "parameter=parameter value", and connect these parameters with the & character, and the generated string is the string to be signed.

Call the signature algorithm: Use the SHA256WithRSA signature function corresponding to the respective language to use the merchant's private key to sign the signature string to be signed, and perform Base64 encoding.
Assign the generated signature to the sign parameter and concatenate it into the request parameter.

Key format issue

The private key used in this project is in PKCS1 format.
The Java language needs to remove the BEGIN, END lines, line breaks, and spaces in the key. Non-Java languages ​​retain the original key format.

## Getting Started
We recommend managing third-party dependencies from Maven, which allows you to add new libraries and include them in your projects.

### Install the JAVA
The latest version of the Didipay Java server-side SDK is v0.0.6. It supports Java versions 1.7+.

Check your Java version:
```shell
java -version
```

### Install Maven

From the command-line, download Maven.

### Add the dependency
To install the library using Maven, place the following in your project’s pom.xml file:
```shell
<dependency>
  <groupId>io.github.didipay</groupId>
  <artifactId>merchant-java-sdk</artifactId>
  <version>${didipay.version}</version>
</dependency>
```

### Run your first request:
Now that you have the JAVA SDK installed, you can create API requests.
 ```java
public class Server {
    public static void main(String[] args) {

        String domain = "https://api.99pay.com.br";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String key = "key";
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .build();
        
        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.payQuery(payParameter);
        System.out.println("responseInfo:" + responseInfo);
    }
}
 ```
Save the file as MerchantClientTest.Java. From the command-line, cd to the directory containing the file you just saved then run:
 ```shell
Run MerchantClientTest.main()
 ```
If everything worked, the command-line shows the following response. Save these identifiers so you can use them while building your integration.
This wraps up the quickstart. See the link below for a few different ways to process a payment for the product you just created.

[Document](https://didipay.didiglobal.com/developer/docs/en/)