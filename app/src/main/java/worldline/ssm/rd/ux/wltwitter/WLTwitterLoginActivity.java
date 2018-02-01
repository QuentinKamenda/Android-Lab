package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.R;

/**
 * Created by Remi on 18/01/2018.
 */

public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {

    static final String LOGIN = "worldline.ssm.rd.ux.wltwitter.LOGIN";
    static final String PASSWORD = "worldline.ssm.rd.ux.wltwitter.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.loginButton).setOnClickListener(this);

        SharedPreferences prefs = this.getSharedPreferences("preferences", MODE_PRIVATE);

        if (prefs.contains(LOGIN) && prefs.contains(PASSWORD))
        {
            Intent intent = new Intent(this, WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString(LOGIN, prefs.getString(LOGIN, "Default"));
            intent.putExtras(extras);

            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        EditText loginText = (EditText) findViewById(R.id.loginEditText);
        EditText passwordText = (EditText) findViewById(R.id.passwordEditText);

        if (TextUtils.isEmpty(loginText.getText()) || TextUtils.isEmpty(passwordText.getText()))
        {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
        else
        {
            SharedPreferences sharedPreferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
            sharedPreferences.edit().putString("LOGIN", loginText.getText().toString()).commit();
            sharedPreferences.edit().putString("PASSWORD", passwordText.getText().toString()).apply();

            String testLogin = sharedPreferences.getString("LOGIN", "MrAluron");
            Log.d("LoginPref", testLogin);


            Intent intent = new Intent(this, WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString(LOGIN, loginText.getText().toString());
            intent.putExtras(extras);

            startActivity(intent);
        }
    }
}
