package android.cuong.androidmvp_antonioleiva.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.cuong.androidmvp_antonioleiva.R;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Implementing login
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username, password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init view
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.button).setOnClickListener(this);

        // init presenter
        presenter = new LoginPresenterImpl();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        username.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {

    }
}
