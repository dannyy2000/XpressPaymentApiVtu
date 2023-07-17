package com.example.airtimevtuapi.utils;

import com.example.airtimevtuapi.common.ApiResponse;
import com.example.airtimevtuapi.enums.ResponseMessage;
import org.springframework.stereotype.Component;


    @Component
    public class ResponseUtils {
        // Returns a successful ApiResponse with status code 200 and success message
        public static ApiResponse getResponse() {
            return ApiResponse.builder ()
                    .statusCode (200)
                    .success (true)
                    .message (ResponseMessage.SENT)
                    .build ();
        }

        // Returns a failed ApiResponse with status code 401 and no content message
        public static ApiResponse getFailedResponse() {
            return ApiResponse.builder ()
                    .message (ResponseMessage.NO_CONTENT)
                    .success (false)
                    .statusCode (401)
                    .build ();
        }

        // Returns a create ApiResponse with status code 200 and create message
        public static ApiResponse getCreatedResponse() {
            return ApiResponse.builder ()
                    .statusCode (200)
                    .success (true)
                    .message (ResponseMessage.CREATED)
                    .build ();
        }

        // Returns a confirm ApiResponse with status code 200 and confirm message
        public static ApiResponse getConfirmResponse() {
            return ApiResponse.builder ()
                    .message (ResponseMessage.CONFIRMED)
                    .success (true)
                    .statusCode (200).build ();
        }
    }




