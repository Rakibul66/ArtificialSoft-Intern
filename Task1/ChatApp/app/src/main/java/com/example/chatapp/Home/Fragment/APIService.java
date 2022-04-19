package com.example.chatapp.Home.Fragment;

import com.example.chatapp.Notification.MyResponse;
import com.example.chatapp.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAA98HE5A:APA91bFqJUuvhONzRp0Mu_mJgvHAeFuHgf_LE-PuGA0kY7F5enxLcqaAE_5bY0UxP4LS7t5-3tzFz0BV696H4qKZCGKfLZY56P5VIWhxeHc-Q7VZk9GOmZY2oj97LR9HQ8Ub1RentG-Y"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

