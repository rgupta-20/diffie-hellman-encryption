public class DHBetterEncryption implements Communicator { 
        
    private long privateKey;
    public long publicBase;
    public long publicMod;
    private long sharedKey;
    
    public DHBetterEncryption(long publicBase, long publicMod, long privateKey) {
        this.publicBase = publicBase;
        this.publicMod = publicMod;
        this.privateKey = privateKey;   
    }   
    
    public long modularExponentiation(long otherPublicKey) {
        long[] modValues = new long[100];
        modValues[0] = otherPublicKey % publicMod;
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
    
    public void createSharedKey(long otherPublicKey){
        sharedKey = modularExponentiation(otherPublicKey);
        System.out.println("shared key is " + sharedKey);
    }
    
    public void printSecretKey(){
        System.out.println(privateKey);
    }
    
    public String encryptMessage(String s){
        String newString = "";
        long chunkValue = 7;
        long chunk1 = sharedKey % chunkValue;
        long chunk2 = (sharedKey / chunkValue) % chunkValue;
        long chunk3 = (sharedKey / chunkValue / chunkValue) % chunkValue; 
        
        for (int i = 0; i < s.length(); i++) {
            if (i % 3 == 0) {
                newString += (char) (s.charAt(i) + chunk1);
            }
            if (i % 3 == 1) {
                newString += (char) (s.charAt(i) + chunk2);
            }            
            if (i % 3 == 2) {
                newString += (char) (s.charAt(i) + chunk3);
            }            
        }
        return newString;
    }
    
    public String decryptMessage(String s){
        String newString = "";
        long chunkValue = 7;
        long chunk1 = sharedKey % chunkValue;
        long chunk2 = (sharedKey / chunkValue) % chunkValue;
        long chunk3 = (sharedKey / chunkValue / chunkValue) % chunkValue; 
        
        for (int i = 0; i < s.length(); i++) {
            if (i % 3 == 0) {
                newString += (char) (s.charAt(i) - chunk1);
            }
            if (i % 3 == 1) {
                newString += (char) (s.charAt(i) - chunk2);
            }            
            if (i % 3 == 2) {
                newString += (char) (s.charAt(i) - chunk3);
            }            
        }
        return newString;
    }
}