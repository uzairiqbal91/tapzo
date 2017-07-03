package com.example.mohsinhussain.allinoneapp;

/**
 * Created by Hp on 6/19/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.R.attr.name;
import static android.R.attr.translateY;

/**
 * Created by Hp on 5/13/2017.
 */

public class DAL  {

    private static String DB_NAME = "Brand Records";


    private FirebaseDatabase firebase;
    private static DatabaseReference database;
    private static DatabaseReference table;
    private Context context;
    private ProgressDialog mProgressDialog;
    public static String category;

    //List<Profile> searchResults;
    private boolean success = false;
    Activity activity;
    ArrayList<String> brandName = new ArrayList<String>();
    ArrayList<String> brandImgId = new ArrayList<String>();
    ArrayList<String> brandCategory = new ArrayList<String>();
    ArrayList<String> brandUrl = new ArrayList<String>();
    public static ArrayList<String> getBrandName;
    public static ArrayList<String> getBrandUrl;
    public static ArrayList<String> getImageUrl;
    public static ArrayList<Bitmap> bitmaps;


    private StorageReference mStorageRef;

    ArrayList<String> getBrandCategory = new ArrayList<String>();
    ArrayList<String> getBrandName1=null;
    String[] copy;

    Scanner scanner;
    public static int  counter = 0;
    private int array_position;

//    private StorageReference mStorageRef;

    //public static String[] getBrandCategory=new  String[10];


    public DAL(Activity activity, Context context) {
        this.activity = activity;
        firebase = FirebaseDatabase.getInstance();
        firebase.setPersistenceEnabled(true);
        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);
//        table.keepSynced(true);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        this.context = context;
    }

    public DAL()
    {

    }

//    public boolean addProfile() {
//
//        String[] tokens = new String[1000];
//        InputStream is = context.getResources().openRawResource(R.raw.shoppingrecords);
//
//
//        scanner = new Scanner(is);
//        int k = 0;
//        while (scanner.hasNext()) {
//            tokens = scanner.nextLine().split(";");
//            brandName.add(tokens[0]);
//            brandImgId.add(tokens[1]);
//            brandCategory.add(tokens[2]);
//            brandUrl.add(tokens[3]);
//
//            k++;
//        }
//
////i am fetching name and cgpa from file and save the data to firebase
//        InputStream stream = null;
//        Bitmap bitmap = null;
//        for (int i = 0; i < brandName.size(); i++) {
//
//
//            final DatabaseReference record = table.child(String.valueOf(i));
//            record.child("Name").setValue(brandName.get(i), new DatabaseReference.CompletionListener() {
//
//
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            record.child("Imgid").setValue(brandImgId.get(i), new DatabaseReference.CompletionListener() {
//
//
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            record.child("Category").setValue(brandCategory.get(i), new DatabaseReference.CompletionListener() {
//
//
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            record.child("Url").setValue(brandUrl.get(i), new DatabaseReference.CompletionListener() {
//
//
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
//                }
//            });
//
////
////            File sd = Environment.getExternalStorageDirectory();
////            File image = new File("C:/Summer Internship/Allinooneapp/app/src/main/res/drawable/brands/0.jpg");
////
////
//
////            try {
////
////                URL url = new URL(brandImgId.get(i));
////                InputStream in = url.openConnection().getInputStream();
////                BufferedInputStream bis = new BufferedInputStream(in,1024*8);
////                ByteArrayOutputStream out = new ByteArrayOutputStream();
////
////                int len=0;
////                byte[] buffer = new byte[1024];
////                while((len = bis.read(buffer)) != -1){
////                    out.write(buffer, 0, len);
////                }
////                out.close();
////                bis.close();
////
////                byte[] data = out.toByteArray();
////                 bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
////
////            }
////            catch (IOException e) {
////                e.printStackTrace();
////            }
//
////            try {
////                stream = new FileInputStream(new File(brandImgId.get(i).toString()));
////                StorageReference storageReference = mStorageRef.child(String.valueOf(i)+ ".jpg");
////                UploadTask uploadTask = storageReference.putStream(stream);
////                uploadTask.addOnFailureListener(new OnFailureListener() {
////                    @Override
////                    public void onFailure(@NonNull Exception e) {
////                        Toast.makeText(context,"Failiure",Toast.LENGTH_SHORT).show();
////
////
////
////                    }
////                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////                    @Override
////                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//////
//////                    DatabaseReference record=table.child("pic01");
//////                    record.setValue(downloadUrl.getLastPathSegment());
//////
//////
////                    Toast.makeText(context,"Saved into Database",Toast.LENGTH_SHORT).show();
////
////                    }
////                });
////
////
////            } catch (FileNotFoundException e) {
////                e.printStackTrace();
////            }
//
////            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                    Uri.fromFile(new File("C:/Summer Internship/Allinooneapp/app/src/main/res/drawable/brands/0.jpg"));
////                    Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();
////                    mProgressDialog.dismiss();
////
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////                    Toast.makeText(context,"upload failed",Toast.LENGTH_SHORT).show();
////                    mProgressDialog.dismiss();
////
////                }
////            });
//
//        }
//
//
//        //        Uri uri = Uri.fromFile(new File(pathArray.get(array_position)));
////        StorageReference storageReference = mStorageRef.child("images/users/" + userID + "/" + name + ".jpg");
////        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////            @Override
////            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                // Get a URL to the uploaded content
////                Uri downloadUrl = taskSnapshot.getDownloadUrl();
////                toastMessage("Upload Success");
////                mProgressDialog.dismiss();
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                toastMessage("Upload Failed");
////                mProgressDialog.dismiss();
////            }
////        })
////        ;
//
//        return success;
//    }
//
//    public void addImages()
//    {
//        InputStream stream = null;
//        Bitmap bitmap = null;
//        StorageReference storageReference=null;
//        Uri uri=null;
//        for (int i = 0; i < brandName.size(); i++) {
//            int resId= context.getResources().getIdentifier(brandImgId.get(i),null,context.getPackageName());
//             uri = Uri.parse("android.resource://com.example.mohsinhussain.allinoneapp/" + resId);
//         storageReference = mStorageRef.child(String.valueOf(i) + ".jpg");
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // Get a URL to the uploaded content
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                Toast.makeText(context,"upload succesess",Toast.LENGTH_SHORT).show();
////                mProgressDialog.dismiss();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context,"upload failed",Toast.LENGTH_SHORT).show();
//                mProgressDialog.dismiss();
//            }
//        })
//        ;
//
//
//        }
//    }

    public static void searchProfile(String category) {



        getBrandName=new ArrayList<String>();
        getBrandUrl=new ArrayList<String>();
        getImageUrl=new ArrayList<String>();
        table = database.child(category);
        table.keepSynced(true);

        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {


                    Log.i("DAL::deleteProfile", record.child("name").getValue(String.class) + " "  );
                    //Log.i("DAL::deleteProfile", record.child("Roll").getValue(String.class) + " "  );
                    getBrandName.add( String.valueOf(record.child("Name").getValue(String.class)));
                    getBrandUrl.add( String.valueOf(record.child("Url").getValue(String.class)));
                    getImageUrl.add( String.valueOf(record.child("Imgid").getValue(String.class)));
//getImageUrl.add("https://firebasestorage.googleapis.com/v0/b/all-in-one-app-panoplytek.appspot.com/o/0.jpg?alt=media&token=058741a8-140b-4eab-9443-edd35c2f2f0c");
//                    getImageUrl.add(String.valueOf(record.child("Imgid").getValue(String.class)));
//                   URL url = new URL(getImageUrl.get);
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    imageView.setImageBitmap(bmp);




                    //getBrandCategory[counter]= String.valueOf( record.child("Category").getValue(String.class));
                    //Toast.makeText(context,getBrandName.get(counter),Toast.LENGTH_SHORT).show();
                    // Log.i("DAL::deleteProfile", getitemname[counter] + " "  );
                    //Log.i("DAL::deleteProfile", getcgpa[counter] + " "  );
                    counter++;


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        for(int k=0 ; k<getBrandName.size() ;k++)
//        {
//
//            URL url = null;
//            try {
//
//                url = new URL(getImageUrl.get(k));
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                bitmaps.add(bmp);
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

    }

    public ArrayList<String> getBrandName() {


        return getBrandName;

    }





    public void printData() {
        if (getBrandName.isEmpty()) {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();

        } else {
            for (int i = 0; i < getBrandName.size(); i++) {
                Toast.makeText(context, getImageUrl.get(i), Toast.LENGTH_SHORT).show();
            }


        }

    }
}
