package com.example.hiren.scanbuy2;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Hiren on 10/25/2015.
 */
public class ValidateForm {

    private static final String PHONE_REGEX = "^-?\\d*(\\.\\d+)?$";

    public boolean isEmpty (String values)
    {
        if ((values.trim()).equals("") || values.equals(null)) {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean isNumber(String values) {
        String str = values.trim();
       // int num = Integer.parseInt(str);
        if(Pattern.matches(PHONE_REGEX,str))
        {
            return true;
        }
        else
            return false;
    }


}
