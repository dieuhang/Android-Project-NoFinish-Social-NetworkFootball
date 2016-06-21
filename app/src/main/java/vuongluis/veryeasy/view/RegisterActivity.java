package vuongluis.veryeasy.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vuongluis.veryeasy.R;
import vuongluis.veryeasy.bean.User;
import vuongluis.veryeasy.dao.MyDatabaseHelper;
import vuongluis.veryeasy.utils.MyAlertDialog;
import vuongluis.veryeasy.utils.Patterns;

@SuppressWarnings("all")
public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputUser;
    private EditText inputPassword;
    private EditText inputBirthDay;
    private EditText inputAddress;
    private EditText inputConfirmPassword;

    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button)this.findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button)this.findViewById(R.id.btnLinkToLogin);
        inputFullName = (EditText)this.findViewById(R.id.fullname);
        inputBirthDay = (EditText)this.findViewById(R.id.birthday);
        inputAddress = (EditText)this.findViewById(R.id.address);
        inputUser = (EditText)this.findViewById(R.id.user);
        inputPassword = (EditText)this.findViewById(R.id.password);
        inputConfirmPassword = (EditText)this.findViewById(R.id.confirmpassword);

        db = new MyDatabaseHelper(getApplicationContext());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = inputFullName.getText().toString().trim();
                String birthday = inputBirthDay.getText().toString().trim();
                String address = inputAddress.getText().toString().trim();
                String username = inputUser.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String confirmPass = inputConfirmPassword.getText().toString().trim();
                if(checkBlank(fullname,birthday,username,password,confirmPass)){
                    if(checkPass(password,confirmPass)){
                        if(validatUser(username)){
                            db.addUser(new User(username,password,fullname,birthday,address));
                            /**
                             * Cần hộp thoại xác nhận thông tin đăng ký thành công tài khoản
                             */
                            new MyAlertDialog(RegisterActivity.this).showMessage();
                        }else{
                            Toast.makeText(getApplicationContext(),"Username is exist in System!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
//                Log.i("TAG",fullname);
//                Log.i("TAG",birthday);
//                Log.i("TAG",address);
//                Log.i("TAG",username);
//                Log.i("TAG",password);
            }
        });

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    public boolean checkBlank(String fullname,String birthday,String username,String password,String confirmPass){
        boolean check = true;
        if(fullname.isEmpty()){
            inputFullName.setError("Do not Blank!");
            check = false;
        }
        if(birthday.isEmpty()){
            inputBirthDay.setError("Do not Blank!");
            check = false;
        }
        if(username.isEmpty()){
            inputUser.setError("Do not Blank!");
            check = false;
        }
        if(password.isEmpty()){
            inputPassword.setError("Do not Blank!");
            check = false;
        }
        if(confirmPass.isEmpty()){
            inputConfirmPassword.setError("Do not Blank!");
            check = false;
        }
        return check;
    }
    public boolean checkPass(String pass1,String pass2){
        boolean check = false;
        if(pass1.equals(pass2)){
            check = true;
        }else{
            inputPassword.setError("Password do not match!");
            inputConfirmPassword.setError("Password do not match!");
        }
        return check;
    }
    /**
     * Kiểm tra user này đã có trong cơ sở dữ liệu
     */
    public boolean validatUser(String user){
        boolean check = true;
        if(Patterns.checkExistUser(db,user)){
            inputUser.setError("User is exits in database!");
            check = false;
        }
        return check;
    }
}
