package Retrofit;

import java.util.List;

import Objects.LoginToken;
import Objects.LoginUser;
import Objects.ResponseTags;
import Objects.Word;
import Objects.WordPost;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {

    @POST("api/auth/signup")
    Call<LoginToken> createUser(@Query("name") String name, @Query("email") String email, @Query("password") String password, @Query("password_confirmation") String password_confirmation);

    @POST("api/auth/login")
    Call<LoginToken> login(@Body LoginUser user);

    @POST("api/search")
    Call<List<Word>> search(@Query("lex") String word);

    @GET("api/auth/logout")
    Call<LoginToken> logout(@Header("Authorization") String token);

    @POST("api/user_word/edit")
    Call<ResponseTags> mark(@Header("Authorization") String token, @Body WordPost wordPost);

    @GET("api/user_word/show_list")
    Call<ResponseTags> showList(@Header("Authorization") String token);

    @POST("api/user_word/show_list_word")
    Call<List<List<Word>>> getWords(@Header("Authorization") String authHeader, @Body WordPost wordPost);
}
