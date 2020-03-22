package myinterfaces;

public class WordModifier {
    public static String modify(String word) {

        word = word.replaceAll("Ính từ", "Tính từ");
        word = word.replaceAll("Anh từ", "Danh từ");
        word = word.replaceAll("từDanh", "từ Danh");
        word = word.replaceAll("từTính", "từ Tính");
        word = word.replaceAll("từN", "từ N");
        word = word.replaceAll("Nh", "Danh");
        word = word.replaceAll("Hó", "Phó");
        return word;
    }
}
