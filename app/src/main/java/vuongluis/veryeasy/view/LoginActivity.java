package vuongluis.veryeasy.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vuongluis.veryeasy.R;
import vuongluis.veryeasy.bean.User;
import vuongluis.veryeasy.dao.MyDatabaseHelper;
import vuongluis.veryeasy.utils.Patterns;

public class LoginActivity extends Activity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private TextView txtTitle;
    private EditText inputUser;
    private EditText inputPass;
    private Button btnLogin;
    private Button btnLinkToRegister;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtTitle = (TextView)this.findViewById(R.id.txtTitle);
        inputUser = (EditText)this.findViewById(R.id.user);
        inputPass = (EditText)this.findViewById(R.id.password);
        btnLogin = (Button)this.findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button)this.findViewById(R.id.btnLinkToRegister);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"EdwardianScriptITC.ttf");
        txtTitle.setTypeface(myTypeface);

        db = new MyDatabaseHelper(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = inputUser.getText().toString().trim();
                String pass = inputPass.getText().toString().trim();
                if(checkLogin(user,pass)){
                    if(checkdbLogin(user,pass)){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "No Exist Database. Siged In!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Login Fail!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
    }
    public boolean validateLogin(String user,String pass){
        boolean validate = true;
        if(user.isEmpty() || pass.isEmpty()) {
            inputUser.setError("Please input data and not to blanks!");
            inputPass.setError("Please input data and not to blanks!");
            validate = false;
        }
        if(pass.length() < Patterns.LENGHTPASS){
            inputPass.setError("Length of password greater than 6!");
            validate = false;
        }
        return validate;
    }
    public boolean checkLogin(String user,String pass){
        boolean check = true;
        if(validateLogin(user, pass)){
            btnLogin.setEnabled(true);
        }else{
            check = false;
        }
        return check;
    }
    /**
     * Check account exits int database
     */
    public boolean checkdbLogin(String user,String pass){
        boolean check = false;
        int count = 0;
        for (User ignoredx : db.getListUser()) {
            if(ignoredx.getUsername().equals(user) && ignoredx.getPassword().equals(pass)){
                count ++;
            }
        }
        if(count > 0){
            check = true;
        }
        return check;
    }
}
