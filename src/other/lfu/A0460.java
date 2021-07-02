package other.lfu;

public class A0460 {
    public static void main(String[] args) {
//        LFUCache lfuCache = new LFUCache(2);
//        lfuCache.put(1, 1);
//        lfuCache.put(2, 2);
//        System.out.println(lfuCache.get(1));
//        lfuCache.put(3, 3);
//        System.out.println(lfuCache.get(2));
//        System.out.println(lfuCache.get(3));
//        lfuCache.put(4, 4);
//        System.out.println(lfuCache.get(1));
//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(3, 1);
        lfuCache.put(2, 1);
        lfuCache.put(2, 2);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(2));

    }
}
