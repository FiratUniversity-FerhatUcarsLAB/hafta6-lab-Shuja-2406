import java.util.Scanner;

public class SinemaBiletiSistemi {

    // 1. Hafta sonu mu?
    public static boolean haftaSonuMu(int gun) {
        return gun == 6 || gun == 7;  // Cumartesi(6) – Pazar(7)
    }

    // 2. Matine mi? (12:00 öncesi)
    public static boolean matineMi(int saat) {
        return saat < 12;
    }

    // 3. Temel fiyat hesaplama
    public static double temelFiyatHesapla(int gun, int saat) {
        boolean weekend = haftaSonuMu(gun);
        boolean matinee = matineMi(saat);

        if (!weekend && matinee) return 45;    // Hafta içi matine
        if (!weekend && !matinee) return 65;   // Hafta içi normal
        if (weekend && matinee) return 55;     // Hafta sonu matine
        return 85;                              // Hafta sonu normal
    }

    // 4. İndirim hesaplama (indirim oranı döner)
    public static double indirimHesapla(int yas, int meslek, int gun) {

        // Yas indirimleri (öncelikli)
        if (yas >= 65) return 0.30; // %30
        if (yas < 12) return 0.25;  // %25

        // Meslek indirimleri
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) return 0.20; // %20 (Pzt–Perş)
                else return 0.15;                      // %15 (Cuma–Pazar)

            case 2: // Öğretmen
                if (gun == 3) return 0.35;             // Çarşamba %35
                break;

            default:
                return 0.0;
        }

        return 0.0;
    }

    // 5. Film türü ekstra ücret hesaplama
    public static double formatEkstra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6. Final fiyat hesaplama
    public static double finalFiyatHesapla(double temel, double indirim, double ekstra) {
        double indirimliFiyat = temel - (temel * indirim);
        return indirimliFiyat + ekstra;
    }

    // 7. Bilet bilgisi oluşturma
    public static void biletBilgisiYaz(
            int gun, int saat, int yas, int meslek, int filmTuru,
            double temel, double indirim, double ekstra, double finalFiyat) {

        System.out.println("\n=== SINEMA BILETI ===");
        System.out.println("Gün: " + gunAdi(gun));
        System.out.println("Saat: " + saat + ":00");
        System.out.println("Yaş: " + yas);
        System.out.println("Meslek: " + meslekAdi(meslek));
        System.out.println("Film Türü: " + filmTuruAdi(filmTuru));
        System.out.println("-------------------------------");
        System.out.println("Temel Fiyat     : " + temel + " TL");
        System.out.println("İndirim Oranı   : %" + (indirim * 100));
        System.out.println("Format Ekstra   : " + ekstra + " TL");
        System.out.println("-------------------------------");
        System.out.println("FINAL FIYAT     : " + finalFiyat + " TL");
    }

    // Yardımcı metod: Gün adı
    public static String gunAdi(int gun) {
        switch (gun) {
            case 1: return "Pazartesi";
            case 2: return "Salı";
            case 3: return "Çarşamba";
            case 4: return "Perşembe";
            case 5: return "Cuma";
            case 6: return "Cumartesi";
            case 7: return "Pazar";
            default: return "Bilinmiyor";
        }
    }

    // Yardımcı metod: Meslek adı
    public static String meslekAdi(int meslek) {
        switch (meslek) {
            case 1: return "Öğrenci";
            case 2: return "Öğretmen";
            default: return "Diğer";
        }
    }

    // Yardımcı metod: Film türü adı
    public static String filmTuruAdi(int tur) {
        switch (tur) {
            case 1: return "2D";
            case 2: return "3D";
            case 3: return "IMAX";
            case 4: return "4DX";
            default: return "Bilinmiyor";
        }
    }

    // MAIN
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Gün seçin (1=Pzt ... 7=Paz): ");
        int gun = input.nextInt();

        System.out.println("Saat (0-23): ");
        int saat = input.nextInt();

        System.out.println("Yaş: ");
        int yas = input.nextInt();

        System.out.println("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = input.nextInt();

        System.out.println("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = input.nextInt();

        // Hesaplamalar
        double temel = temelFiyatHesapla(gun, saat);
        double indirim = indirimHesapla(yas, meslek, gun);
        double ekstra = formatEkstra(filmTuru);
        double finalFiyat = finalFiyatHesapla(temel, indirim, ekstra);

        // Bilet yazdır
        biletBilgisiYaz(gun, saat, yas, meslek, filmTuru, temel, indirim, ekstra, finalFiyat);

        input.close();
    }
}



Gün seçin (1=Pzt ... 7=Paz): 
6
Saat (0-23): 
15
Ya?: 
19
Meslek (1=Ö?renci, 2=Ö?retmen, 3=Di?er): 
1
Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): 
3

=== SINEMA BILETI ===
Gün: Cumartesi
Saat: 15:00
Ya?: 19
Meslek: Ö?renci
Film Türü: IMAX
-------------------------------
Temel Fiyat     : 85.0 TL
?ndirim Oran?   : %15.0
Format Ekstra   : 35.0 TL
-------------------------------
FINAL FIYAT     : 107.25 TL
PS D:\JAVA\Week 6\SinemaBiletiSistemi> 
