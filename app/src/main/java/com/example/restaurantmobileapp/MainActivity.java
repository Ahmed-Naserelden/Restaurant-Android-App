package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.annotation.Documented;
import java.security.ProtectionDomain;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void save(){
//        FirebaseDatabase fir = FirebaseDatabase.getInstance();
//        DatabaseReference ref = fir.getReference();
//
 //       DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Restaurant");

//        DocumentReference mdoc = FirebaseFirestore.getInstance().document("Restaurant/Users");
        Product p1 = new Product("fish", "sea food", "salt", 18.9f);
        Product p2 = new Product("milk", "drinks", "safe", 2.9f);
        Product p3 = new Product("rise", "food", "spice", 5.9f);
        Product p4 = new Product("water", "drinks", "good", 1.9f);

        Order order1 = new Order();
        order1.addProduct(p1);order1.addProduct(p2);order1.addProduct(p3);order1.addProduct(p4);

        Order order2 = new Order();
        order2.addProduct(p1);order2.addProduct(p2);order2.addProduct(p3);order2.addProduct(p4);
        order2.addProduct(p2);

        Cart cart = new Cart();
        cart.addOrder(order1);
        cart.addOrder(order2);

        FavouriteProducts favouriteProducts = new FavouriteProducts();
        favouriteProducts.addFavouriteProduct(p1);
        favouriteProducts.addFavouriteProduct(p2);

        String NAME = "Naser eldin Mohamed Amer";
        String EMAIL = "Naser@gmail.com";
        String phone = "01003129806";

        User user = new User(NAME, EMAIL, phone, cart, favouriteProducts);
        user.setPassword("veryStrong");

//        order1.setOrderStatus("Finished");


        DBModule db = new DBModule();
//        db.addUser(user, this);
        db.changeStateOrder(order1, user, "cancled",MainActivity.this);
        db.changeStateOrder(order2, user, "finished",MainActivity.this);

//        dbref.child("Users").child(phone).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//
//                    }
//                }).addOnCanceledListener(new OnCanceledListener() {
//            @Override
//            public void onCanceled() {
//                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}