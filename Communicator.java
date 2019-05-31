
/**
 * This is the communicator interface
 * it contains methods that let it setup a shared key
 * with another communicator, and then use that key to
 * encrypt and decrypt messages
 */
public interface Communicator
{
    
    public void createSharedKey(long otherPublicKey);
    
    public void printSecretKey();
    
    public String encryptMessage(String s);
    
    public String decryptMessage(String s);
    
}
