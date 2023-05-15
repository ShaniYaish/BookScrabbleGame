package ServerSide;

public interface CacheReplacementPolicy {
    void add(String word);
    String remove();
}