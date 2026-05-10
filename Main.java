class Karyawan {
    private int idKaryawan;
    private String nama;
    private String jabatan;
    private String departemen;
    private String email;
    private String username;
    private String password;

    public Karyawan(int idKaryawan, String nama, String jabatan,
                     String departemen, String email,
                     String username, String password) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jabatan = jabatan;
        this.departemen = departemen;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void login(String inputUsername, String inputPassword) {
        if(username.equals(inputUsername) && password.equals(inputPassword)) {
            System.out.println(nama + " berhasil login.");
        } else {
            System.out.println("Login gagal.");
        }
    }

    public void tampilkanData() {
        System.out.println("ID: " + idKaryawan);
        System.out.println("Nama: " + nama);
        System.out.println("Jabatan: " + jabatan);
        System.out.println("Departemen: " + departemen);
        System.out.println("Email: " + email);
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }
}

class KaryawanTetap extends Karyawan {
    private String status;
    private String tanggalMulai;

    public KaryawanTetap(int id, String nama, String jabatan,
                         String departemen, String email,
                         String username, String password,
                         String status, String tanggalMulai) {
        super(id, nama, jabatan, departemen, email, username, password);
        this.status = status;
        this.tanggalMulai = tanggalMulai;
    }

    public void tampilkanStatus() {
        System.out.println("Status: " + status);
        System.out.println("Tanggal Mulai: " + tanggalMulai);
    }
}

class Shift {
    private int idShift;
    private String namaShift;
    private int jamMasuk;
    private int jamKeluar;

    public Shift(int idShift, String namaShift, int jamMasuk, int jamKeluar) {
        this.idShift = idShift;
        this.namaShift = namaShift;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
    }

    public void tampilkanShift() {
        System.out.println("Shift: " + namaShift);
        System.out.println("Jam masuk: " + jamMasuk);
        System.out.println("Jam keluar: " + jamKeluar);
    }

    public int getIdShift() {
        return idShift;
    }
}

class Absensi {
    private int idAbsensi;
    private int idKaryawan;
    private int idShift;
    private String tanggal;
    private int jamMasuk;
    private int jamKeluar;

    public Absensi(int idAbsensi, int idKaryawan, int idShift, String tanggal) {
        this.idAbsensi = idAbsensi;
        this.idKaryawan = idKaryawan;
        this.idShift = idShift;
        this.tanggal = tanggal;
    }

    public void absenMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
        System.out.println("Absen masuk: " + jamMasuk);
    }

    public void absenKeluar(int jamKeluar) {
        if(jamKeluar < jamMasuk) {
            System.out.println("ERROR: Jam keluar tidak boleh sebelum jam masuk!");
        }
        else if((jamKeluar - jamMasuk) > 12) {
            System.out.println("ERROR: Jam kerja tidak boleh lebih dari 12 jam!");
        }
        else {
            this.jamKeluar = jamKeluar;
            System.out.println("Absen keluar: " + jamKeluar);
            System.out.println("Total jam kerja: " + (jamKeluar - jamMasuk) + " jam");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        KaryawanTetap k1 = new KaryawanTetap(
                1,
                "Sharon",
                "Staff IT",
                "IT Department",
                "sharon@gmail.com",
                "sharon123",
                "pass123",
                "Tetap",
                "2024-01-10"
        );

        k1.login("sharon123", "pass123");
        k1.tampilkanData();
        k1.tampilkanStatus();

        System.out.println("\n=== DATA SHIFT ===");
        Shift s1 = new Shift(101, "Shift Pagi", 8, 17);
        s1.tampilkanShift();

        System.out.println("\n=== ABSENSI VALID ===");
        Absensi a1 = new Absensi(201, k1.getIdKaryawan(), s1.getIdShift(), "2026-05-09");
        a1.absenMasuk(8);
        a1.absenKeluar(17);

        System.out.println("\n=== ABSENSI ILEGAL ===");
        Absensi a2 = new Absensi(202, k1.getIdKaryawan(), s1.getIdShift(), "2026-05-09");
        a2.absenMasuk(8);
        a2.absenKeluar(23);
    }
}
