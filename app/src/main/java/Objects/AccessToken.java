package Objects;

public class AccessToken {
    private static String accessToken = null;
    public static void setAccessToken(String token){
        accessToken = token;
    }
    public static String getAccessToken(){
        return accessToken;
    }
    public static void clearAccessToken(){accessToken = null;}
}
