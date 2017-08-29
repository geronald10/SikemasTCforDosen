package absensi.anif.its.ac.id.sikemastcfordosen.sync;

import android.app.IntentService;
import android.content.Intent;


public class SikemasSyncKelasIntentService extends IntentService {

    public SikemasSyncKelasIntentService() {
        super("SikemasSyncKelasIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SikemasSyncTask.syncKelasDosen(this);
    }
}
