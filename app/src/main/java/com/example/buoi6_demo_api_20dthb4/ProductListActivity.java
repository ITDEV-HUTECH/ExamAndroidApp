package com.example.buoi6_demo_api_20dthb4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libs.APIClient;
import com.example.libs.APIInterface;
import com.example.libs.Model.GetProductCallBackModel;
import com.example.libs.Model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {
    APIInterface apiInterface;
    EditText txtName, txtPrice, txtGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtGroupName = findViewById(R.id.txtGroupName);
    }

    public void clickCreateProduct(View view) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Product product = new Product();
        product.setName(txtName.getText() + "");
        product.setPrice(Double.valueOf(txtPrice.getText() + ""));
        product.setGroupName(txtGroupName.getText() + "");
        Call<GetProductCallBackModel> call = apiInterface.insertProduct(product);
        call.enqueue(new Callback<GetProductCallBackModel>() {
            @Override
            public void onResponse(Call<GetProductCallBackModel> call, Response<GetProductCallBackModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<GetProductCallBackModel> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Có lỗi xãy ra", Toast.LENGTH_LONG).show();
            }
        });
    }
}