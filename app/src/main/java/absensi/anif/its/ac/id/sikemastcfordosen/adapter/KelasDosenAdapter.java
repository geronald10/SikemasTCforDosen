package absensi.anif.its.ac.id.sikemastcfordosen.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import absensi.anif.its.ac.id.sikemastcfordosen.R;
import absensi.anif.its.ac.id.sikemastcfordosen.activity.dosen.ListKelasActivity;
import absensi.anif.its.ac.id.sikemastcfordosen.utilities.SikemasDateUtils;

public class KelasDosenAdapter extends RecyclerView.Adapter<KelasDosenAdapter.KelasDosenAdapterViewHolder>{

    private final Context mContext;
    private Cursor mCursor;

    // handle clicks on items
    final private KelasDosenAdapterOnClickHandler mClickHandler;

    // interface that receives onClick message
    public interface KelasDosenAdapterOnClickHandler {
        void onClick(String idKelas, String infoKelas);
    }

    public KelasDosenAdapter(@NonNull Context context, KelasDosenAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    @Override
    public KelasDosenAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item_jadwal_dosen, viewGroup, false);
        view.setFocusable(true);

        return new KelasDosenAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KelasDosenAdapterViewHolder kelasDosenAdapterViewHolder, int position) {
        mCursor.moveToPosition(position);

        String kelasMk = mCursor.getString(ListKelasActivity.INDEX_KODE_KELAS);
        String namaMk = mCursor.getString(ListKelasActivity.INDEX_KELAS_NAMA_MK);
        String kodeMk = mCursor.getString(ListKelasActivity.INDEX_KELAS_KODE_MK);
        String ruangMK = mCursor.getString(ListKelasActivity.INDEX_KELAS_NAMA_RUANGAN);
        String hari = mCursor.getString(ListKelasActivity.INDEX_KELAS_HARI);
        String waktuMulai = SikemasDateUtils.formatTime(mCursor.getString(ListKelasActivity.INDEX_KELAS_MULAI));
        String waktuSelesai= SikemasDateUtils.formatTime(mCursor.getString(ListKelasActivity.INDEX_KELAS_SELESAI));

        kelasDosenAdapterViewHolder.tvKodeMK.setText(kodeMk);
        kelasDosenAdapterViewHolder.tvNamaMK.setText(namaMk);
        kelasDosenAdapterViewHolder.tvKelasMK.setText(kelasMk);
        kelasDosenAdapterViewHolder.tvRuangMK.setText(ruangMK);
        kelasDosenAdapterViewHolder.tvHariMK.setText(hari);
        kelasDosenAdapterViewHolder.tvWaktuMulaiMK.setText(waktuMulai);
        kelasDosenAdapterViewHolder.tvWaktuSelesaiMK.setText(waktuSelesai);
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

    class KelasDosenAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvKodeMK;
        private TextView tvNamaMK;
        private TextView tvKelasMK;
        private TextView tvHariMK;
        private TextView tvWaktuMulaiMK;
        private TextView tvWaktuSelesaiMK;
        private TextView tvRuangMK;

        public KelasDosenAdapterViewHolder(View itemView) {
            super(itemView);

            tvKodeMK = (TextView) itemView.findViewById(R.id.tv_kode_mata_kuliah);
            tvNamaMK = (TextView) itemView.findViewById(R.id.tv_nama_mata_kuliah);
            tvKelasMK = (TextView) itemView.findViewById(R.id.tv_kelas);
            tvHariMK = (TextView) itemView.findViewById(R.id.tv_hari);
            tvWaktuMulaiMK = (TextView) itemView.findViewById(R.id.tv_waktu_mulai);
            tvWaktuSelesaiMK = (TextView) itemView.findViewById(R.id.tv_waktu_selesai);
            tvRuangMK = (TextView) itemView.findViewById(R.id.tv_ruang);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);
            String idKelas = mCursor.getString(ListKelasActivity.INDEX_ID_KELAS);
            String infoKelas = mCursor.getString(ListKelasActivity.INDEX_KELAS_NAMA_MK) + " kelas " +
                    mCursor.getString(ListKelasActivity.INDEX_KODE_KELAS);
            mClickHandler.onClick(idKelas, infoKelas);
        }
    }
}
