package absensi.anif.its.ac.id.sikemastcfordosen.utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class NetworkUtils {

    // Login API
    public static final String LOGIN_SIKEMAS =
            "http://absensi.if.its.ac.id/loginmobile";

    public static final String KIRIM_STATUS_KEHADIRAN_MAHASISWA =
            "http://absensi.if.its.ac.id/mhs/ubahstatuskehadiran";

    // List Kelas Diampu oleh Dosen API
    public static final String LIST_KELAS_DOSEN_SIKEMAS =
            "http://absensi.if.its.ac.id/kelasdiampu";

    // List Jadwal Kelas Hari ini
    public static final String LIST_JADWAL_KELAS_DOSEN_HARI_INI =
            "http://absensi.if.its.ac.id/kelashariini";

    // Change Status kelas
    public static final String CHANGE_STATUS_KELAS =
            "http://absensi.if.its.ac.id/ubahstatuskelas";

    // Detail peserta kelas yang dipilih
    public static final String GET_PESERTA_PERKULIAHAN =
            "http://absensi.if.its.ac.id/peserta_perkuliahan_ini";

    // Penjadwalan ulang kirim tanggal
    public static final String REQUEST_PENJADWALAN_BY_TANGGAL =
            "http://absensi.if.its.ac.id/jadwal_ulang_tanggal";

    // Penjadwalan ulang kirim tanggal, kirim waktu
    public static final String REQUEST_PENJADWALAN_BY_TANGGAL_WAKTU =
            "http://absensi.if.its.ac.id/jadwal_ulang_waktu";

    // Penjadwalan ulang kirim tanggal, kirim waktu
    public static final String CONFIRM_PENJADWALAN_SEMENTARA =
            "http://absensi.if.its.ac.id/konfirm_ganti_jadwal";

    // Penjadwalan sementara request perkuliahan terpilih
    public static final String REQUEST_JADWAL_PERKULIAHAN =
            "http://absensi.if.its.ac.id/req_perkuliahan";

    // Refresh Kehadiran
    public static final String REFRESH_KEHADIRAN =
            "http://absensi.if.its.ac.id/refresh_kehadiran";

    // Kirim berita acara
    public static final String KIRIM_BERITA_ACARA =
            "http://absensi.if.its.ac.id/insertberitaacara";

    private static final String KODE_DOSEN_PARAM = "kode_dosen";

    public static String getResponseListKelasDosen(Context context, final String kodeDosen) {
        int REQUEST_TIMEOUT = 5;
        JSONObject response = null;
        HashMap<String, String> params = new HashMap<>();
        params.put(KODE_DOSEN_PARAM, kodeDosen);

        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                LIST_KELAS_DOSEN_SIKEMAS, new JSONObject(params), requestFuture, requestFuture);
        VolleySingleton.getmInstance(context).addToRequestQueue(jsonRequest);
        try {
            response = requestFuture.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
            Log.d("response kelas", String.valueOf(response));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public static String getResponseListPerkuliahanDosen(Context context, final String kodeDosen) {
        int REQUEST_TIMEOUT = 5;
        JSONObject response = null;
        HashMap<String, String> params = new HashMap<>();
        params.put(KODE_DOSEN_PARAM, kodeDosen);

        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                LIST_JADWAL_KELAS_DOSEN_HARI_INI, new JSONObject(params), requestFuture, requestFuture);
        VolleySingleton.getmInstance(context).addToRequestQueue(jsonRequest);
        try {
            response = requestFuture.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
            Log.d("response perkuliahan", String.valueOf(response));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

}
