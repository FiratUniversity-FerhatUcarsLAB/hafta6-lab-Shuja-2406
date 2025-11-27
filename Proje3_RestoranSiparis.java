import java.util.Scanner;

public class RestoranSistemi {

    // 1. Ana yemek fiyati
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Manti
            default: return 0;
        }
    }

    // 2. Baslangıc fiyati
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // corba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Boregi
            default: return 0;
        }
    }

    // 3. Icecek fiyati
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Taze Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4. Tatli fiyati
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Kunefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sutlac
            default: return 0;
        }
    }

    // 5. Combo menu kontrolu
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6. Happy hour (14:00 – 17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7. Indirim hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {

        double indirimOrani = 0;

        // Combo ise %15 indirim
        if (combo) {
            indirimOrani += 0.15;
        }

        // 200 TL uzeri ekstra %10 indirim
        if (tutar > 200) {
            indirimOrani += 0.10;
        }

        // Happy Hour → iceceklere %20 (GENEL TUTARA %20 DEGIL!)
        // Bu happy hour indirimi final tutara yansiyan icecek fiyatinda hesaplanmali,
        // burada yalnizca ek indirim uygulanmasi icin disaridan icecek indirimi eklenir.

        // Ogrenci hafta ici → %10 ekstra indirim
        if (ogrenci) {
            indirimOrani += 0.10;
        }

        return tutar * indirimOrani;
    }

    // 8. Bahsis onerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Saat (0-23): ");
        int saat = input.nextInt();

        System.out.println("Ogrenci misiniz? (1=Evet, 2=Hayir): ");
        boolean ogrenci = (input.nextInt() == 1);

        System.out.println("\n--- MENU SECIMLERI ---");

        // Ana yemek
        System.out.println("Ana Yemek Sec (1=Izgara Tavuk, 2=Adana, 3=Levrek, 4=Manti, 0=Yok): ");
        int anaSecim = input.nextInt();
        boolean anaVar = anaSecim != 0;

        // Başlangıç
        System.out.println("Baslangic Sec (1=Corba, 2=Humus, 3=Sigara Boregi, 0=Yok): ");
        int baslangicSecim = input.nextInt();
        boolean baslangicVar = baslangicSecim != 0;

        // İçecek
        System.out.println("Icecek Sec (1=Kola, 2=Ayran, 3=Meyve Suyu, 4=Limonata, 0=Yok): ");
        int icecekSecim = input.nextInt();
        boolean icecekVar = icecekSecim != 0;

        // Tatlı
        System.out.println("Tatli Sec (1=Kunefe, 2=Baklava, 3=Sutlac, 0=Yok): ");
        int tatliSecim = input.nextInt();
        boolean tatliVar = tatliSecim != 0;

        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        // Happy Hour icecek indirimi
        if (icecekVar && isHappyHour(saat)) {
            icecekFiyat *= 0.80;  // %20 indirim
        }

        // Toplam ham tutar
        double toplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Combo kontrolü
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        // Indirim hesaplama
        double indirim = calculateDiscount(toplam, combo, ogrenci, saat);

        // Net tutar
        double odenecek = toplam - indirim;

        // Bahsis onerisi
        double bahsis = calculateServiceTip(odenecek);

        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.println("Ana Yemek: " + anaFiyat + " TL");
        System.out.println("Baslangic: " + baslangicFiyat + " TL");
        System.out.println("Icecek: " + icecekFiyat + " TL");
        System.out.println("Tatli: " + tatliFiyat + " TL");
        System.out.println("---------------------------");
        System.out.println("Toplam (indirimsiz): " + toplam + " TL");
        System.out.println("Uygulanan Indirim : -" + indirim + " TL");
        System.out.println("Odenecek Tutar    : " + odenecek + " TL");
        System.out.println("Bahsis Onerisi (%10): " + bahsis + " TL");

        System.out.println(combo ? "Combo Menu: EVET" : "Combo Menu: HAYIR");
        System.out.println(isHappyHour(saat) ? "Happy Hour UYGULANDI" : "Happy Hour YOK");

        input.close();
    }
}





Ogrenci misiniz? (1=Evet, 2=Hayir):
1

--- MENU SECIMLERI ---
Ana Yemek Sec (1=Izgara Tavuk, 2=Adana, 3=Levrek, 4=Manti, 0=Yok):
2
Baslangic Sec (1=Corba, 2=Humus, 3=Sigara Boregi, 0=Yok):
1
Icecek Sec (1=Kola, 2=Ayran, 3=Meyve Suyu, 4=Limonata, 0=Yok):
3
Tatli Sec (1=Kunefe, 2=Baklava, 3=Sutlac, 0=Yok):
2

=== SIPARIS OZETI ===
Ana Yemek: 120.0 TL
Baslangic: 25.0 TL
Icecek: 28.0 TL
Tatli: 55.0 TL
---------------------------
Toplam (indirimsiz): 228.0 TL
Uygulanan Indirim : -79.8 TL
Odenecek Tutar    : 148.2 TL
Bahsis Onerisi (%10): 14.82 TL
Combo Menu: EVET
Happy Hour UYGULANDI
PS D:\JAVA\Week 6\RestoranSistemi>
