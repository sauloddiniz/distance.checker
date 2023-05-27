//package com.distancechecker.config;
//
//public class AwsSecretManager {
//
//    public static void getSecret() {
//
//        String secretName = "api_geocode";
//        Region region = Region.of("us-east-1");
//
//        // Create a Secrets Manager client
//        SecretsManagerClient client = SecretsManagerClient.builder()
//                .region(region)
//                .build();
//
//        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
//                .secretId(secretName)
//                .build();
//
//        GetSecretValueResponse getSecretValueResponse;
//
//        try {
//            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
//        } catch (Exception e) {
//            // For a list of exceptions thrown, see
//            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
//            throw e;
//        }
//
//        String secret = getSecretValueResponse.secretString();
//
//        // Your code goes here.
//    }
//
//}
