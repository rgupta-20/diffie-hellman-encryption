public class CodeBreaker implements Evesdropper {
    
    public long publicMod;
    public long publicBase;
    
    public CodeBreaker(long publicBase, long publicMod) {
        this.publicMod = publicMod;
        this.publicBase = publicBase;        
    }
    
    public long modularExponentiation(long privateKey) {
        long[] modValues = new long[100];
        modValues[0] = publicBase % publicMod;
        for (int i = 1; i < modValues.length; i++) {
            modValues[i] = (modValues[i - 1] * modValues [i - 1]) % publicMod;
        }       
        
        long[] powersOfTwo = new long[100];
        powersOfTwo[0] = 1; 
        int back = 0;
        for (int i = 1; i < powersOfTwo.length; i++) {
            if (privateKey >= powersOfTwo[i - 1] * 2) {
                back = i;
                powersOfTwo[i] = powersOfTwo[i - 1] * 2;
            }
            else {
                break;
            }
        }            
        
        long total = 1;
        for (int i = back; i >= 0; i--) {
            if (powersOfTwo[i] <= privateKey) {
                total = (total * modValues[i]) % publicMod;
                privateKey -= powersOfTwo[i];
            }
        }
        return total;
    }
    
    public void findAndPrintPrivateKey(long publicKey) {
        for (int i = 2; i < publicMod; i++) {
            if (modularExponentiation(i) == publicKey) {
                System.out.println(i);
            }
        }
    }
}