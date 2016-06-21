package vuongluis.veryeasy.utils;


import android.util.Log;

import vuongluis.veryeasy.bean.User;
import vuongluis.veryeasy.dao.MyDatabaseHelper;

/**
 * Created by vuongluis on 5/14/2016.
 */
@SuppressWarnings("all")
public class Patterns {
    public static final int LENGHTPASS = 6;

    /**
     * Method definition to catch validate login and signed up
     */
    public static boolean checkExistUser(MyDatabaseHelper db,String username){
        boolean check = false;
        int count = 0;
        for (User ignoredx : db.getListUser()) {
            if(ignoredx.getUsername().equals(username)){
                count ++;
            }
        }
        if(count > 0){
            check = true;
        }
        Log.i("TAG ---------------------------",String.valueOf(count));
        return check;
    }
}

