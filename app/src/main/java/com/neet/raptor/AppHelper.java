package com.neet.raptor;

import android.text.TextUtils;

public class AppHelper {

   public static boolean isValidEmail( String email ) {
      return !TextUtils.isEmpty( email ) && android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches();
   }
}
