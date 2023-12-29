package com.example.buoi6_demo_api_20dthb4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.libs.APIClient;
import com.example.libs.APIInterface;
import com.example.libs.Model.GetProductCallBackModel;
import com.example.libs.Model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    ListView productlistview;
    List<String> dataString;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productlistview = findViewById(R.id.productlistview);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<GetProductCallBackModel> call = apiInterface.doGetProductList();
        call.enqueue(new Callback<GetProductCallBackModel>() {
            @Override
            public void onResponse(Call<GetProductCallBackModel> call, Response<GetProductCallBackModel> response) {
                List<Product> productList = response.body().getData();
                dataString = new ArrayList<>();

                for (int i = 0; i < productList.size(); i++) {
                    Product product = productList.get(i);
                    String displayString = "ID: " + product.getID() +
                            "\nName: " + product.getName() +
                            "\nPrice: " + product.getPrice();
                    dataString.add(displayString);
                }

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataString);
                productlistview.setAdapter(adapter);

                productlistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int UUID, long id) {
                        String productID = productList.get(UUID).getID().toString();
                        showDeleteConfirmationDialog(productID);
                        return true;
                    }
                });

                productlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int UUID, long id) {
                        Product selectedProduct = productList.get(UUID);
                        showProductDetail(selectedProduct);
                    }
                });
            }

            @Override
            public void onFailure(Call<GetProductCallBackModel> call, Throwable t) {
                Log.v("log:", t.getMessage());
            }
        });
    }

    private void showDeleteConfirmationDialog(final String UUID) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct(UUID);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void deleteProduct(String UUID) {
        Call<GetProductCallBackModel> call = apiInterface.deleteProduct(UUID);
        call.enqueue(new Callback<GetProductCallBackModel>() {
            @Override
            public void onResponse(Call<GetProductCallBackModel> call, Response<GetProductCallBackModel> response) {
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<GetProductCallBackModel> call, Throwable t) {
                Log.v("log:", t.getMessage());
            }
        });
    }

    private void showProductDetail(Product product) {
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("PRODUCT_ID", product.getID().toString());
        intent.putExtra("PRODUCT_NAME", product.getName());
        intent.putExtra("PRODUCT_PRICE", product.getPrice());
        String groupName = product.getGroupName();
        intent.putExtra("PRODUCT_GROUPNAME", product.getGroupName());
        startActivity(intent);
    }


    public void clickCreate(View view) {
        Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
        startActivity(intent);
    }
}
