package snapje.canetop.Utils;

import java.util.UUID;

public class Check {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  public static boolean isInteger(String s) {
      try {
          Integer.parseInt(s);
          return true;
      } catch (NumberFormatException ex) {
          return false;
      }
  }

    public static boolean isUUID(String name) {
        try {
            UUID.fromString(name);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
