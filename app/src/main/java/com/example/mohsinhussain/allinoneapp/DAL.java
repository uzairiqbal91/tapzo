package com.example.mohsinhussain.allinoneapp;

/**
 * Created by Hp on 6/19/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.R.attr.name;

/**
 * Created by Hp on 5/13/2017.
 */

public class DAL  {

    private static String DB_NAME = "Brand Records";
    private static String ENTITY_NAME_PROFILES = "Records";

    private FirebaseDatabase firebase;
    private DatabaseReference database;
    private static DatabaseReference table;
    private Context context;
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
        table = database.child(ENTITY_NAME_PROFILES);
        table.keepSynced(true);

        this.context = context;
    }

    public DAL()
    {

    }

    public boolean addProfile() {

        String[] tokens = new String[1000];
        InputStream is = context.getResources().openRawResource(R.raw.shoppingrecords);


        scanner = new Scanner(is);
        int k = 0;
        while (scanner.hasNext()) {
            tokens = scanner.nextLine().split(";");
            brandName.add(tokens[0]);
            brandImgId.add(tokens[1]);
            brandCategory.add(tokens[2]);
            brandUrl.add(tokens[3]);

            k++;
        }

//i am fetching name and cgpa from file and save the data to firebase

        for (int i = 0; i < brandName.size(); i++) {


            final DatabaseReference record = table.child(String.valueOf(i));
            record.child("Name").setValue(brandName.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Imgid").setValue(brandImgId.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Category").setValue(brandCategory.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Url").setValue(brandUrl.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });


        }

//        Uri uri = Uri.fromFile(new File(pathArray.get(array_position)));
//        StorageReference storageReference = mStorageRef.child("images/users/" + userID + "/" + name + ".jpg");
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // Get a URL to the uploaded content
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                toastMessage("Upload Success");
//                mProgressDialog.dismiss();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                toastMessage("Upload Failed");
//                mProgressDialog.dismiss();
//            }
//        })
//        ;

        return success;
    }

    public static void searchProfile(String category) {



        getBrandName=new ArrayList<String>();
        getBrandUrl=new ArrayList<String>();

        table.orderByChild("Category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {


                    //Log.i("DAL::deleteProfile", record.child("name").getValue(String.class) + " "  );
                    //Log.i("DAL::deleteProfile", record.child("Roll").getValue(String.class) + " "  );
                    getBrandName.add( String.valueOf(record.child("Name").getValue(String.class)));
                    getBrandUrl.add( String.valueOf(record.child("Url").getValue(String.class)));

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


    }

    public ArrayList<String> getBrandName() {


        return getBrandName;

    }





    public void printData() {
        if (getBrandName.isEmpty()) {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();

        } else {
            for (int i = 0; i < getBrandName.size(); i++) {
                Toast.makeText(context, getBrandName.get(i), Toast.LENGTH_SHORT).show();
            }


        }

    }
}
