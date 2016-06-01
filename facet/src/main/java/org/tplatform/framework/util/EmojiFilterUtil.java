package org.tplatform.framework.util;

/**
 * Created by Tianyi on 2015/1/29.
 */
public class EmojiFilterUtil {
  //工具类禁止实例化
  private EmojiFilterUtil() {
  }

//  private static final Pattern emoji = Pattern.compile("[\\\\ud83c\\\\udc00-\\\\ud83c\\\\udfff]|[\\\\ud83d\\\\udc00-\\\\ud83d\\\\udfff]|[\\\\u2600-\\\\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

//  @Override
//  public static CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//    Matcher emojiMatcher = emoji.matcher(source);
//    if (emojiMatcher.find()) {
//      return "";
//    }
//
//    return null;
//  }

  /**
   * 检测是否有emoji字符
   *
   * @param source
   * @return 一旦含有就抛出
   */
  public static boolean containsEmoji(String source) {
    if (StringUtil.isEmpty(source)) {
      return false;
    }

    int len = source.length();

    for (int i = 0; i < len; i++) {
      char codePoint = source.charAt(i);

      if (isEmojiCharacter(codePoint)) {
        //do nothing，判断到了这里表明，确认有表情字符
        return true;
      }
    }

    return false;
  }

  private static boolean isEmojiCharacter(char codePoint) {
    return (codePoint == 0x0) ||
        (codePoint == 0x9) ||
        (codePoint == 0xA) ||
        (codePoint == 0xD) ||
        ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
        ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
        ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
  }

  /**
   * 过滤emoji 或者 其他非文字类型的字符
   *
   * @param source
   * @return
   */
  public static String filterEmoji(String source) {

    if (!containsEmoji(source)) {
      return source;//如果不包含，直接返回
    }
    //到这里铁定包含
    StringBuilder buf = null;

    int len = source.length();

    for (int i = 0; i < len; i++) {
      char codePoint = source.charAt(i);

      if (isEmojiCharacter(codePoint)) {
        if (buf == null) {
          buf = new StringBuilder(source.length());
        }

        buf.append(codePoint);
      } else {
      }
    }

    if (buf == null) {
      return source;//如果没有找到 emoji表情，则返回源字符串
    } else {
      if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
        buf = null;
        return source;
      } else {
        return buf.toString();
      }
    }

  }
}
