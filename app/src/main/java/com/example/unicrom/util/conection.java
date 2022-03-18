package com.example.unicrom.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class conection {

    private static FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static FirebaseAuth authentication(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
    public static FirebaseUser current(){
        FirebaseAuth user = conection.authentication();
        return user.getCurrentUser();
    }



}
