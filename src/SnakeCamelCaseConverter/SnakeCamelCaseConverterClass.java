package SnakeCamelCaseConverter;

public class SnakeCamelCaseConverterClass {
    public static void main(String[] args) {
        // Примеры использования
        System.out.println(convertIdentifier("some_variable"));    // someVariable
        System.out.println(convertIdentifier("nice_variable"));    // niceVariable
        System.out.println(convertIdentifier("tryToConvertMe"));   // try_to_convert_me
        System.out.println(convertIdentifier("unchanged"));        // unchanged
        System.out.println(convertIdentifier("InvalidMethod"));     // Error!
        System.out.println(convertIdentifier("bad_VarName"));       // Error!
    }

    public static String convertIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            return "Error!";
        }

        boolean isSnakeCase = checkSnakeCase(identifier);
        boolean isCamelCase = checkCamelCase(identifier);

        if (isSnakeCase && isCamelCase) {
            // Случай, когда строка не содержит подчеркиваний и вся в lowercase (например, "unchanged")
            return identifier;
        } else if (isSnakeCase) {
            return snakeToCamel(identifier);
        } else if (isCamelCase) {
            return camelToSnake(identifier);
        } else {
            return "Error!";
        }
    }

    private static boolean checkSnakeCase(String str) {
        if (str.isEmpty() || !Character.isLowerCase(str.charAt(0)) || !Character.isLowerCase(str.charAt(str.length() - 1))) {
            return false;
        }

        boolean underscoreFound = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                if (underscoreFound || i == 0 || i == str.length() - 1) {
                    // Двойное подчеркивание или в начале/конце
                    return false;
                }
                underscoreFound = true;
            } else if (!Character.isLowerCase(c)) {
                // Не строчная буква и не подчеркивание
                return false;
            } else {
                underscoreFound = false;
            }
        }

        return true;
    }

    private static boolean checkCamelCase(String str) {
        if (str.isEmpty() || !Character.isLowerCase(str.charAt(0))) {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    private static String snakeToCamel(String str) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    private static String camelToSnake(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append('_').append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
