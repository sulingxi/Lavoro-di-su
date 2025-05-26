public class CaesarCipher {


  public static String metodocodifica(String text, int shift) {
    StringBuilder result = new StringBuilder();

    for (char c : text.toCharArray()) {
      if (Character.isLetter(c)) {
        char base = Character.isUpperCase(c) ? 'A' : 'a';
        char calcolare = (char) ((c - base + shift) % 26 + base);
        result.append(calcolare);
      } else {
        result.append(c);
      }
    }

    return result.toString();
  }


  public static String decodifica(String text, int shift) {
    return metodocodifica(text, 26 - shift);
  }
}
