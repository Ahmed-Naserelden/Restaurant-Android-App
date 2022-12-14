package com.example.restaurantmobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DBModule {
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference dbRef;

    public DBModule() {
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Restaurant");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }
    //****************************************************\


    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public DatabaseReference getDbRef() {
        return dbRef;
    }

    // ****************************************************\
    // this product will be send to firebase
    public void addProduct(@NonNull Product product, Context context) {
        dbRef.child("Products").child(product.getType()).child(product.getId()).setValue(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add product", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure  add product", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled  add product", Toast.LENGTH_SHORT).show();

            }
        });
    }
    // this user will be send to firebase
    public void addUser(@NonNull User user, Context context) {
        dbRef.child("Users").child(mAuth.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add user", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure add user", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled add user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // this order will be send to firebase
    public void addOrder(@NonNull Order order, @NonNull User user, Context context) {
        user.addOrder(order);
        dbRef.child("Users").child(user.getPhone()).child("cart").setValue(user.getCart()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success add order", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure add order", Toast.LENGTH_SHORT).show();

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled add order", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // this changes will be send to firebase
    public void changeStateOrder(@NonNull Order order, @NonNull User user, String status, Context context) {
        user.getCart().getOrders().get(order.getOrderID()).setOrderStatus(status);
        dbRef.child("Users").child(user.getPhone()).child("cart").setValue(user.getCart()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success change StateOrder", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure changeS tateOrder", Toast.LENGTH_SHORT).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled change StateOrder", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addFavoriteProduct(@NonNull Product product, @NonNull User user, Context context) {
        user.addFavouriteProduct(product);
        dbRef.child("Users").child(mAuth.getUid()).child("favouriteProducts").setValue(user.getFavouriteProducts()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success to add FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure to add FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled to add FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RemoveFavoriteProduct(@NonNull Product product, @NonNull User user, Context context) {
        user.removeFavouriteProduct(product);
        dbRef.child("Users").child(user.getPhone()).child("favouriteProducts").setValue(user.getFavouriteProducts()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Success to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failure to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(context, "Canceled to Remove FavoriteProduct", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadPicture(String path, String name, Uri imageUri, Context context) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("Uploading Image ...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        storageReference.child(path + name).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Snackbar.make(findViewById(android.R.id.content), "image Uploaded", Snackbar.LENGTH_LONG).show();

                Toast.makeText(context, "image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                pd.dismiss();
                Toast.makeText(context, "Upload Canceled", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(context, "Upload Failure", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage : " + (int) progressPercent + "%");
                if ((int) progressPercent == 100) {
                    pd.dismiss();
                }
            }
        });
    }

    public void displayPicture(String path, String name, ImageView imageView, Context context) {

        try {
            final File locFile = File.createTempFile("app_ui", "jpg");
            storageReference.child(path + name).getFile(locFile).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                       // Toast.makeText(context, "Success download Image", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(locFile.getAbsolutePath());
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (IOException e) {
            Toast.makeText(context, "Catch error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void readProduct(Product product, Context context) {

    }

    public void mReadDataOnce(String child, @NonNull final OnGetDataListener listener) {
        listener.onStart();
        dbRef.child(child).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });
    }

    public boolean getCurrentUser() {
        return false;
    }

     synchronized public Data getProductData(String path, Context context) {
        Data data = new Data();
        dbRef.child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot i : snapshot.getChildren()) {
                    List<Product> li = (List<Product>) new ArrayList<Product>();
                    // TODO: handle the post
                    for (DataSnapshot j : i.getChildren()) {
                        // TODO: handle the post
                        Product product = j.getValue(Product.class);
                        li.add(product);
                    }
                    data.addProductList(i.getKey(), li);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Faliure", Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public void getUserCart(String path, Context context){
        dbRef.child("Users/"+mAuth.getUid()+"/cart").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Cart cart = snapshot.getValue(Cart.class);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       }
        );
    }


    public void addProductToOrder(Product product, User user,Context context){

        user.addProductToOrder(product);
        dbRef.child("Users/"+mAuth.getUid()+"/order").setValue(user.getOrder()).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  if(task.isSuccessful()){
//                      Toast.makeText(context, "add Product", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(context, "Not add Product", Toast.LENGTH_SHORT).show();
                  }
              }
          }
        );
    }

}
