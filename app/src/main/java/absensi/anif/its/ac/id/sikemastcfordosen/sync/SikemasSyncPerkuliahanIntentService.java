package absensi.anif.its.ac.id.sikemastcfordosen.sync;

import android.app.IntentService;
import android.content.Intent;

public class SikemasSyncPerkuliahanIntentService extends IntentService {

    public SikemasSyncPerkuliahanIntentService() {
        super("SikemasSyncPerkuliahanIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SikemasSyncTask.syncPerkuliahan(this);
    }
}
