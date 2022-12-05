package com.example.myapp;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String BUCKET_NAME = "fortisveridis-local";
    private static final String FOLDER_NAME = "folder_1";

    public static void main(String[] args) {
        S3Client s3Client = DependencyFactory.s3Client();
        ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .prefix(FOLDER_NAME)
                .bucket(BUCKET_NAME)
                .build();

        ListObjectsResponse res = s3Client.listObjects(listObjects);
        List<S3Object> objects = res.contents();
        ArrayList<ObjectIdentifier> fileArrayList = new ArrayList<>();

        for (S3Object object : objects) {
            fileArrayList.add(ObjectIdentifier.builder()
                    .key(object.key())
                    .build());
        }

        Delete del = Delete.builder()
                .objects(fileArrayList)
                .build();

        DeleteObjectsRequest dor = DeleteObjectsRequest.builder()
                .bucket(BUCKET_NAME)
                .delete(del)
                .build();

        s3Client.deleteObjects(dor);
    }
}
