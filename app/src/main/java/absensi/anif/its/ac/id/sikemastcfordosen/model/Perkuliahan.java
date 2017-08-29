package absensi.anif.its.ac.id.sikemastcfordosen.model;

public class Perkuliahan {

    private String idPerkuliahan;
    private String kodeMk;
    private String namaMk;
    private String kelasMk;
    private String ruangMk;
    private String pertemuanKe;
    private String tanggal;
    private String waktuMulai;
    private String waktuSelesai;

    public Perkuliahan(String idPerkuliahan, String kodeMk, String namaMk, String kelasMk,
                       String ruangMk, String pertemuanKe, String tanggal, String waktuMulai,
                       String waktuSelesai) {
        this.idPerkuliahan = idPerkuliahan;
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.kelasMk = kelasMk;
        this.ruangMk = ruangMk;
        this.pertemuanKe = pertemuanKe;
        this.tanggal = tanggal;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }

    public String getIdPerkuliahan() {
        return idPerkuliahan;
    }

    public void setIdPerkuliahan(String idPerkuliahan) {
        this.idPerkuliahan = idPerkuliahan;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public String getKelasMk() {
        return kelasMk;
    }

    public void setKelasMk(String kelasMk) {
        this.kelasMk = kelasMk;
    }

    public String getRuangMk() {
        return ruangMk;
    }

    public void setRuangMk(String ruangMk) {
        this.ruangMk = ruangMk;
    }

    public String getPertemuanKe() {
        return pertemuanKe;
    }

    public void setPertemuanKe(String pertemuanKe) {
        this.pertemuanKe = pertemuanKe;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }
}
