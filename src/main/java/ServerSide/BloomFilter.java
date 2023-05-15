package ServerSide;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {

    int size; // number of bits
    BitSet bitSet;
    MessageDigest[] messageDigests;

    public BloomFilter(int size , String...k){
        this.size = size;
        bitSet = new BitSet(size);
        this.messageDigests = new MessageDigest[k.length];
        for(int i=0 ; i<k.length ; i++)
        {
            try {
                this.messageDigests[i] = MessageDigest.getInstance(k[i]);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("The hash name is not valid");
            }
        }
    }

    public int turnWordBit(String word , MessageDigest hashFunk)
    {
        BigInteger bigInt = null;
        byte[] bytes;
        bytes = hashFunk.digest(word.getBytes());
        bigInt = new BigInteger(bytes);
        return (Math.abs(bigInt.intValue()%this.size));
    }


    public void add(String word)
    {
        for(int i =0 ; i <messageDigests.length ; i++)
        {
            bitSet.set(turnWordBit(word,messageDigests[i]));
        }
    }

    public boolean contains(String word)
    {
        for(int i=0 ; i<messageDigests.length ; i++)
        {
            if(!(bitSet.get(turnWordBit(word,messageDigests[i]))))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        for(int i=0 ; i< bitSet.length() ; i++)
        {
            string.append(bitSet.get(i)?"1":"0");
        }

        return string.toString();
    }
}

