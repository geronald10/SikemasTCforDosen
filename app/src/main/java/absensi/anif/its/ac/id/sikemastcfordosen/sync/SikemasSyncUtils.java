package absensi.anif.its.ac.id.sikemastcfordosen.sync;

import android.content.Context;
import android.content.Intent;

public class SikemasSyncUtils {

    public static void startImmediatePerkuliahanSync(Context context) {
        Intent intentToSyncImmediately = new Intent(context, SikemasSyncPerkuliahanIntentService.class);
        context.startService(intentToSyncImmediately);
    }

    public static void startImmediateKelasSync(final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SikemasSyncKelasIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}