
package com.example.myapp;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * The module containing all dependencies required by the {@link App}.
 */
public class DependencyFactory {

    private DependencyFactory() {}

    /**
     * @return an instance of S3Client
     */
    public static S3Client s3Client() {
        AwsBasicCredentials credentialsProvider = AwsBasicCredentials.create(
                "A",
                "B");

        return S3Client.builder()
                       .credentialsProvider(StaticCredentialsProvider.create(credentialsProvider))
                       .region(Region.EU_WEST_3)
                       .build();
    }
}
