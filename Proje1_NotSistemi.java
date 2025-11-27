import java.util.Scanner;

public class StudentReport {

    // --- REQUIRED METHODS ---

    // 1. Average Calculation
    public static double calculateAverage(double midterm, double fin, double homework) {
        return (midterm * 0.30) + (fin * 0.40) + (homework * 0.30);
    }

    // 2. Passing Grade
    public static boolean isPassingGrade(double average) {
        return average >= 50;
    }

    // 3. Letter Grade
    public static String getLetterGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    // 4. Honor List
    public static boolean isHonorList(double average, double midterm, double fin, double homework) {
        return average >= 85 && midterm >= 70 && fin >= 70 && homework >= 70;
    }

    // 5. Retake Right (Bütünleme)
    public static boolean hasRetakeRight(double average) {
        return average >= 40 && average < 50;
    }


    // --- MAIN PROGRAM ---
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Vize: ");
        double midterm = input.nextDouble();

        System.out.print("Final: ");
        double fin = input.nextDouble();

        System.out.print("Odev: ");
        double homework = input.nextDouble();

        double average = calculateAverage(midterm, fin, homework);
        String letter = getLetterGrade(average);
        boolean passed = isPassingGrade(average);
        boolean honor = isHonorList(average, midterm, fin, homework);
        boolean retake = hasRetakeRight(average);

        // --- REPORT OUTPUT ---
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu  : " + midterm);
        System.out.println("Final Notu : " + fin);
        System.out.println("Odev Notu  : " + homework);
        System.out.println("------------------------------");
        System.out.printf("Ortalama : %.1f\n", average);
        System.out.println("Harf Notu : " + letter);
        System.out.println("Durum : " + (passed ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (honor ? "EVET" : "HAYIR"));

        if (retake) {
            System.out.println("Butunleme Hakkı : VAR");
        } else {
            System.out.println("Butunleme Hakkı : YOK");
        }

        input.close();
    }
}



Vize: 55
Final: 60
Odev: 85

=== OGRENCI NOT RAPORU ===
Vize Notu  : 55.0
Final Notu : 60.0
Odev Notu  : 85.0
------------------------------
Ortalama : 66.0
Harf Notu : D
Durum : GECTI
Onur Listesi : HAYIR
Butunleme Hakk? : YOK
PS D:\JAVA\Week 6\NotSistemi>
