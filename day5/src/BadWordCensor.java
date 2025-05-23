public class BadWordCensor {
    public static void main(String[] args) {
        String text = "This is a damn bad example with some stupid words.";
        String[] badWords = {"damn", "stupid", "bad"};

        String result = text;
        for (String word : badWords) {
            result = result.replaceAll("(?i)\\b" + word + "\\b", "****");
        }

        System.out.println("Original: " + text);
        System.out.println("Censored: " + result);
    }
}