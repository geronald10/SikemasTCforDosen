package absensi.anif.its.ac.id.sikemastcfordosen.model;

public class RuangKelas {

    private String idRuangan;
    private String namaRuangan;

    public RuangKelas(String idRuangan, String namaRuangan) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

}
