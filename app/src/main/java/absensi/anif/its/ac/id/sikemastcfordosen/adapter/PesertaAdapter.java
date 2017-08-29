package absensi.anif.its.ac.id.sikemastcfordosen.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import absensi.anif.its.ac.id.sikemastcfordosen.R;
import absensi.anif.its.ac.id.sikemastcfordosen.activity.dosen.PesertaKelasFragment;

public class PesertaAdapter extends RecyclerView.Adapter<PesertaAdapter.PesertaAdapterViewHolder> {

    private final Context mContext;
    private Cursor mCursor;

    public PesertaAdapter(Context context) {
        mContext = context;
    }

    @Override
    public PesertaAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_peserta, viewGroup, false);

        return new PesertaAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PesertaAdapterViewHolder pesertaAdapterViewHolder, int position) {
        mCursor.moveToPosition(position);

        String idPeserta = mCursor.getString(PesertaKelasFragment.INDEX_ID_PESERTA);
        String nrpPeserta = mCursor.getString(PesertaKelasFragment.INDEX_NRP_PESERTA);
        String namaPeserta = mCursor.getString(PesertaKelasFragment.INDEX_NAMA_PESERTA);
        String emailPeserta = mCursor.getString(PesertaKelasFragment.INDEX_EMAIL_PESERTA);
        String countHadir = mCursor.getString(PesertaKelasFragment.INDEX_COUNT_HADIR);
        String countIjin = mCursor.getString(PesertaKelasFragment.INDEX_COUNT_IJIN);
        String countSakit = mCursor.getString(PesertaKelasFragment.INDEX_COUNT_SAKIT);
        String countAbsen = mCursor.getString(PesertaKelasFragment.INDEX_COUNT_ABSEN);

        pesertaAdapterViewHolder.tvNumber.setText("#" + (position+1));
        pesertaAdapterViewHolder.tvNrpPeserta.setText(nrpPeserta);
        pesertaAdapterViewHolder.tvNamaPeserta.setText(namaPeserta);
        pesertaAdapterViewHolder.tvHadirCount.setText(countHadir);
        pesertaAdapterViewHolder.tvIjinCount.setText(countIjin);
        pesertaAdapterViewHolder.tvSakitCount.setText(countSakit);
        pesertaAdapterViewHolder.tvAbsenCount.setText(countAbsen);
    }

    @Override
    public int getItemCount() {
        if (null == mCursor)
            return 0;
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    class PesertaAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumber;
        private TextView tvNrpPeserta;
        private TextView tvNamaPeserta;
        private TextView tvHadirCount;
        private TextView tvIjinCount;
        private TextView tvSakitCount;
        private TextView tvAbsenCount;

        private PesertaAdapterViewHolder(View itemView) {
            super(itemView);

            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvNrpPeserta = (TextView) itemView.findViewById(R.id.tv_nrp_peserta);
            tvNamaPeserta = (TextView) itemView.findViewById(R.id.tv_nama_peserta);
            tvHadirCount = (TextView) itemView.findViewById(R.id.tv_hadir_count);
            tvIjinCount = (TextView) itemView.findViewById(R.id.tv_ijin_count);
            tvSakitCount = (TextView) itemView.findViewById(R.id.tv_sakit_count);
            tvAbsenCount = (TextView) itemView.findViewById(R.id.tv_absen_count);
        }
    }
}
