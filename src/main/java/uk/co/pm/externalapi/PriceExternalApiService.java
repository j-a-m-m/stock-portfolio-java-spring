package uk.co.pm.externalapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.pm.model.Equity;
import uk.co.pm.model.Price;

import java.io.IOException;
import java.util.List;

/**
 * Created by nellieoliver on 11/03/2018.
 */
public class PriceExternalApiService {
    //This is used to communicate with the extenal API (http://square.github.io/okhttp/)
    private OkHttpClient client;
    //This is used for converting back & forth between JSON and java objects (https://github.com/google/gson)
    private Gson gson;
    private String baseUrl;

    private TypeToken<List<Price>> priceListTypeToken = new TypeToken<List<Price>>() {
    };

    public PriceExternalApiService(String baseUrl) {
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.baseUrl = baseUrl;
    }

    public List<Price> getPrices() throws IOException {
        String url = baseUrl + "/prices";

        //Create a okHttp "request"
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Use the okHttp client to make a call, using our request object, returning a response
        Response response = client.newCall(request).execute();
        //Extract the response body as a string
        String responseString = response.body().string();
        List<Price> prices = gson.fromJson(responseString, priceListTypeToken.getType());

        return prices;
    }

    public List<Price> getPrices_Quarter(String quarter) throws IOException {
        String url = baseUrl + "/prices/" + quarter;

        //Create a okHttp "request"
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Use the okHttp client to make a call, using our request object, returning a response
        Response response = client.newCall(request).execute();
        //Extract the response body as a string
        String responseString = response.body().string();
        List<Price> price_quarter = gson.fromJson(responseString, priceListTypeToken.getType());

        return price_quarter;
    }
}




