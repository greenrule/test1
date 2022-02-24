package guru.qa;

public class FirstClass {
    public static void main(String[] args) {
        byte aByte = 127; // 8 bit -128 ... 127

        short aShort = 100 ; // 16 bit -32768 ... 32767

        // самый используемый
        int aInt = 1_100_000; // 32 bit -2 ^ 31 ... (2 ^ 31) -1
        long aLong = 100L; // 64 bit -2 ^ 63 ... (2 ^ 63) -1

        float aFloat = 0.1F; // 32 bit
        // самый используемый (если просто хранить, но не использовать арифметику)
        double aDouble = 512.63D; // 64 bit

        char aChar = 'c';
        boolean aBoolean = false;
        // / -- целочисленное деление
        // % -- остаток от деления
        // инкремент ++
        // декремент --
        System.out.println(aInt + aByte);
        System.out.println(aInt - aByte);
        System.out.println(aInt * aShort);
        System.out.println(aLong + aShort);
        System.out.println(aFloat * 2);
        System.out.println(aDouble + aShort);
        System.out.println(aByte / 4);
        System.out.println(aByte % 4);
        System.out.println(aByte++);
        System.out.println(aByte--);

        // && (&)
        // || (|)
        // !
      System.out.println((5 <= 10) && (6>5) || !(8<10));



    }
}
