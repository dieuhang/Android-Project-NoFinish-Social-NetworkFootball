package vuongluis.veryeasy.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by vuongluis on 5/14/2016.
 */
@SuppressWarnings("all")
public class MyAlertDialog {
    private Activity activity;
    public MyAlertDialog(Activity activity){
        this.activity = activity;
    }
    public void showMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle("Message");
        alertDialogBuilder
                .setMessage("Congratulation you register account success!")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        activity.finish();
                    }
                });
        AlertDialog alertDialog =  alertDialogBuilder.create();
        alertDialog.show();
    }
}
