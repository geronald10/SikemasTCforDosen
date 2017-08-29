package absensi.anif.its.ac.id.sikemastcfordosen.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import absensi.anif.its.ac.id.sikemastcfordosen.R;
import absensi.anif.its.ac.id.sikemastcfordosen.activity.dosen.HalamanUtamaDosen;
import absensi.anif.its.ac.id.sikemastcfordosen.data.SikemasContract;
import absensi.anif.its.ac.id.sikemastcfordosen.data.SikemasSessionManager;
import absensi.anif.its.ac.id.sikemastcfordosen.utilities.NetworkUtils;
import absensi.anif.its.ac.id.sikemastcfordosen.utilities.VolleySingleton;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Context mContext;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;
    private SweetAlertDialog pDialog;
    private SikemasSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;

        // Set up the login form.
        edtEmail = (EditText) findViewById(R.id.edt_email_login);
        edtPassword = (EditText) findViewById(R.id.edt_password_login);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);

        // Session manager
        session = new SikemasSessionManager(mContext);

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            Intent intent = null;
            String loggedUserId = session.getUserDetails().get(SikemasSessionManager.KEY_USER_ROLE);
            switch (loggedUserId) {
                case "1":
                    intent = new Intent(LoginActivity.this, HalamanUtamaDosen.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
            finish();
        }

        btnSignIn.setOnClickListener(operation);
    }

    View.OnClickListener operation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.btn_sign_in:
                    String email = edtEmail.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();

                    if (!email.isEmpty() && !password.isEmpty()) {
                        checkLogin(email, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void checkLogin(final String email, final String password) {
        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                NetworkUtils.LOGIN_SIKEMAS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    // Check for error node in json
                    if (code.equals("1")) {
                        // user successfully logged in. Create Login session
                        JSONObject user = jsonObject.getJSONObject("user");
                        String userId = user.getString(SikemasContract.UserEntry.KEY_USER_ID);
                        String name = user.getString(SikemasContract.UserEntry.KEY_USER_NAME);
                        String email = user.getString(SikemasContract.UserEntry.KEY_USER_EMAIL);
                        String role = user.getString(SikemasContract.UserEntry.KEY_USER_ROLE);
                        if (role.equals("1")) {
                            String userCode = jsonObject.getString(SikemasContract.UserEntry.KEY_KODE_DOSEN);
                            session.createLoginSession(userId, name, email, role, userCode);
                        } else {
                            showErrorNotAuthorized();
                        }
                        checkUserRole(role);
                        showSuccessResult();
                        finish();
                    } else {
                        // Tidak ditemukan pengguna
                        showErrorNotFound();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    showErrorResult();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                showErrorResult();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_email", email);
                params.put("user_password", password);

                return params;
            }
        };
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void showLoading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Memproses..");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void showSuccessResult() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    private void showErrorResult() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Gagal!")
                    .setContentText("Terjadi kesalahan server")
                    .show();
        }
    }

    private void showErrorNotFound() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Tidak ditemukan!")
                    .setContentText("Data pengguna tidak ditemukan")
                    .show();
        }
    }

    private void showErrorNotAuthorized() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Tidak diijinkan!")
                    .setContentText("Akun pengguna tidak diijinkan masuk")
                    .show();
        }
    }

    private void checkUserRole(String userRole) {
        switch (userRole) {
            case "1":
                Intent intentDosen = new Intent(LoginActivity.this, HalamanUtamaDosen.class);
                startActivity(intentDosen);
                break;
            default:
                break;
        }
    }
}
