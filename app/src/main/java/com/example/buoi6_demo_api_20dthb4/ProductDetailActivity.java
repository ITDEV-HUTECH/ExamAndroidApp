package com.example.buoi6_demo_api_20dthb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libs.APIClient;
import com.example.libs.APIInterface;
import com.example.libs.Model.GetProductCallBackModel;
import com.example.libs.Model.Product;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    APIInterface apiInterface;
    String productId, productName, productGroupName;
    double productPrice;
    EditText editProductName, editProductPrice, editProductGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        if (intent != null) {
            productId = intent.getStringExtra("PRODUCT_ID");
            productName = intent.getStringExtra("PRODUCT_NAME");
            productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.0);
            productGroupName = intent.getStringExtra("PRODUCT_GROUPNAME");

            editProductName = findViewById(R.id.txtName);
            editProductPrice = findViewById(R.id.txtPrice);
            editProductGroupName = findViewById(R.id.txtGroupName);

            editProductName.setText(productName);
            editProductPrice.setText(productPrice + "");
            editProductGroupName.setText(productGroupName);
        }

    }

    public void clickSaveProduct(View view) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Product product = new Product();
        product.setID(UUID.fromString(productId));
        product.setName(editProductName.getText() + "");
        product.setPrice(Double.valueOf(editProductPrice.getText() + ""));
        product.setGroupName(editProductGroupName.getText() + "");
        Call<GetProductCallBackModel> call = apiInterface.updateProduct(product);
        call.enqueue(new Callback<GetProductCallBackModel>() {
            @Override
            public void onResponse(Call<GetProductCallBackModel> call, Response<GetProductCallBackModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<GetProductCallBackModel> call, Throwable t) {

            }
        });
    }
}
