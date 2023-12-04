public class Test {
  public static void main(String[] args) {

    String password = "contraseñ";
    System.out.println(encryptPassword(password));	
    
    // 255 % 255 = 0
    // 254 % 255 = 254
    // 253 % 255 = 253
    // 256 % 255 = 1

    // Hola -> aloH -> cn
  }

  // password.charAt(i) -> Devuelve el caracter en la posición i de la cadena password
  // rotateString.indexOf("a") -> Devuelve la posición del caracter password.charAt(i) en la cadena rotateString

  public static String encryptPassword(String password) {
    String encryptedPassword = "";
    String rotateString = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0246813579";

    for (int i = password.length() - 1; i >= 0; i--) {
      int rotatedIndex = (rotateString.indexOf(password.charAt(i)) + password.length()) % rotateString.length();
      encryptedPassword += rotateString.charAt(rotatedIndex);
    }

    return encryptedPassword;
  }
}
