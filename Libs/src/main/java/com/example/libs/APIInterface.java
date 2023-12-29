package com.example.libs;

import com.example.libs.Model.GetProductCallBackModel;
import com.example.libs.Model.Product;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/Product/get-product-list?GroupName=hpt")
    Call<GetProductCallBackModel> doGetProductList();

    @POST("api/Product/insert-product")
    Call<GetProductCallBackModel> insertProduct(@Body Product product);

    @GET("api/Product/get-product")
    Call<GetProductCallBackModel> getProduct(@Query("id") UUID Id);

    @POST("api/Product/delete-product")
    Call<GetProductCallBackModel> deleteProduct(@Query("id") String Id);

    @POST("api/Product/update-product")
    Call<GetProductCallBackModel> updateProduct(@Body Product product);
}
