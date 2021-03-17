import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
public class Main {
  private static String TRANSLATE_URL = "https://script.google.com/macros/s/AKfycbwm2qYLCLWuu9RpExpzXtuXqEET1rlwQKNkbZ-TGhSUnyLZrS9HQKF8/exec";
  public static void main1(String[] args) {
    String output = "callback({\"sourceText\":\"Thank you for your request. We will reach out to you soon.\",\"translatedText\":\"هل "
        + "ساعد هذا الجواب؟\"})";
    System.out.println(output);
    System.out.println(output.substring(102, output.length() - 3));
  }
  public static void main(String[] args) throws Exception {
    String text = "This bot is disabled.Please reach out to the bot administrator.";
    //Translated text: Hallo Welt!
    List<String> langs = Arrays
        .asList("ar", "bn", "bg", "my", "zh", "ca", "hr", "cs", "da", "nl", "et", "tl", "fi", "fr",
            "de", "el", "gu", "he", "hi", "hu", "id", "it", "jv", "ja", "kk", "kn", "ko", "ms",
            "ml", "mr", "mn", "ne", "no", "fa", "pl", "pt", "pa", "ps", "ro", "ru", "sk", "so",
            "es", "sv", "ta", "te", "th", "tr", "uk", "ur", "vi");
    for(String lang : langs) {
      String output = translate("en", lang, text);
      System.out.println(lang + " " + output);
      //System.out.println("\""+lang + "\"" + " : " + "\"" +  output.substring(102, output.length() - 3) + "\"");
    }
  }
  private static String translate(String langFrom, String langTo, String text) throws IOException {
    // INSERT YOU URL HERE
    String urlStr = TRANSLATE_URL +
        "?q=" + URLEncoder.encode(text, "UTF-8") +
        "&target=" + langTo +
        "&source=" + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
}