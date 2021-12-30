import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class RSA{
       private BigInteger p;
       private BigInteger q;
       private BigInteger n;
       private BigInteger phi;
       private BigInteger e;
       private BigInteger d;
       private int bitlength=1024;
       private Random r;
       public RSA(){
                   r= new Random();
                   p= BigInteger.probablePrime(bitlength,r);
                   q= BigInteger.probablePrime(bitlength,r);
                   n= p.multiply(q);
                   phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                   e= BigInteger.probablePrime(bitlength/2,r);
                   while((phi.gcd(e).compareTo(BigInteger.ONE))>0 && e.compareTo(phi)<0){
                      e.add(BigInteger.ONE);
                   }
                   d= e.modInverse(phi);
                  }
                  public RSA(BigInteger e,BigInteger n,BigInteger d)
                  {
                    this.e= e;
                    this.n= n;
                    this.d= d;
                  }
                  public static void main(String args[])throws IOException{
                       RSA rsa= new RSA();
                       DataInputStream in = new DataInputStream(System.in);
                       String teststring;
                       System.out.println("Enter the plain text:");
                       teststring= in.readLine();
                       System.out.println("Encrypting String:"+teststring);
                       System.out.println("String in Bytes:"+bytesToString(teststring.getBytes()));
                       byte[] encrypted= rsa.encrypt(teststring.getBytes());
                       byte[] decrypted= rsa.decrypt(encrypted);
                       System.out.println("Decrypting Bytes:"+bytesToString(decrypted));
                       System.out.println("Decrypted String:"+new String(decrypted));
                  }
                  private static String bytesToString(byte[] encrypted){
                       String test=" ";
                       for(byte b:encrypted)
                       {
                         test+=Byte.toString(b);
                       }
                       return test;
                  }
                  public byte[] encrypt(byte[] message){
                       return(new BigInteger(message)).modPow(e,n).toByteArray();
                  }
                  public byte[] decrypt(byte[] message){
                       return(new BigInteger(message)).modPow(d,n).toByteArray();
                  }
                  }
             
                    
                 
