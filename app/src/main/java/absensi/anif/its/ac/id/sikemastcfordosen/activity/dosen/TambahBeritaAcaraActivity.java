package absensi.anif.its.ac.id.sikemastcfordosen.activity.dosen;

import android.app.ProgressDialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import absensi.anif.its.ac.id.sikemastcfordosen.R;
import absensi.anif.its.ac.id.sikemastcfordosen.data.SikemasContract.PertemuanEntry;
import absensi.anif.its.ac.id.sikemastcfordosen.utilities.NetworkUtils;
import absensi.anif.its.ac.id.sikemastcfordosen.utilities.VolleySingleton;


public class TambahBeritaAcaraActivity extends AppCompatActivity {

    private final String TAG = TambahBeritaAcaraActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_berita_acara);

        mProgressDialog = new ProgressDialog(this);
        final String idPerkuliahan = getIntent().getStringExtra("id_perkuliahan");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Tambah Berita Acara");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        if (VERSION.SDK_INT >= 21) {
            mToolbar.setElevation(10.0f);
        }
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText edtBeritaAcara = (EditText) findViewById(R.id.edt_berita_acara);
        Button btnKirimBerita = (Button) findViewById(R.id.btn_kirim_berita_acara);
        btnKirimBerita.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimBeritaAcara(String.valueOf(edtBeritaAcara.getText()), idPerkuliahan);
            }
        });
    }

    private void kirimBeritaAcara(final String berita, final String idPerkuliahan) {
        Log.d(TAG, "call kirimBeritaAcara Method");
        Log.d(TAG, "idPerkuliahan " + idPerkuliahan);
        mProgressDialog.setMessage("Mengirimkan Berita Acara ...");
        mProgressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                NetworkUtils.KIRIM_BERITA_ACARA,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "berhasil mengirimkan berita acara",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TambahBeritaAcaraActivity.this.TAG, "Error: " + error.getMessage());
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_perkuliahan", idPerkuliahan);
                params.put(PertemuanEntry.KEY_BERITA_ACARA, berita);
                return params;
            }
        };
        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
