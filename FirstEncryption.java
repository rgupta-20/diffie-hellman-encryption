public class FirstEncryption implements Communicator {
    
    private long secretKey;
    public long publicBase;
    public long publicKey;
    private long sharedKey;
    
    public FirstEncryption(long publicBase, long secretKey) {
        this.publicBase = publicBase;
        this.secretKey = secretKey;
        publicKey = publicBase * secretKey;
    }
    
    public void createSharedKey(long otherPublicKey) {
        sharedKey = otherPublicKey * secretKey;
    }
    
    public void printSecretKey() {
    }
    
    public String encryptMessage(String s) {
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            newString += (char) (s.charAt(i) + secretKey);
        }
        return newString;
    }
    
    public String decryptMessage(String s) {
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            newString += (char) (s.charAt(i) - secretKey);
        }
        return newString;        
    }
    
    
}