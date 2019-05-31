
/**
 * This is the evesdropper interface
 * it only has one method that takes a public key as input and then
 * prints out (and returns) the private key that was used to generate that public key
 */
public interface Evesdropper
{
    public void findAndPrintPrivateKey(long publicKey);
}
