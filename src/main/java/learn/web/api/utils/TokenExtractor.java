package learn.web.api.utils;

public class TokenExtractor {
    public static String extractToken(String headerValue) {
        // Check if the header starts with "Bearer "
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            // Remove "Bearer " prefix
            return headerValue.substring("Bearer ".length()).trim();
        } else {
            // Handle the case where the header doesn't start with "Bearer "
            System.err.println("Invalid Bearer token format");
            return null; // or throw an exception or handle it according to your requirements
        }
    }
}
