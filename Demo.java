import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static org.testng.Assert.*;


public class Demo {
    String payload = "{\n" +
            "    \"username\": \"upskills_admin\",\n" +
            "  \"password\": \"Talent4$$\"\n" +
            "}";
    String payload1 = "{\n" +
            "\"model\": \"Lenovo Ideapad Laptop\",\n" +
            " \"quantity\": \"1000\",\n" +
            " \"price\": \"44000.00\",\n" +
            " \"product_description\": [\n" +
            "    {\n" +
            "      \"name\": \"Lenovo IdeaPad S100\", \n" +
            "      \"meta_title\": \"Lenovo IdeaPad S100\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    String Authorization = "Authorization";
    String basic = "Basic dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9jbGllbnQ6dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9zZWNyZXQ=";

    // TO GENERATE ACCESS TOKEN
    @Test(priority = 1)
    public String Token() {
        Response responsePost = RestAssured.given().
                when().
                header("content-type", "application/json").
                header(Authorization, basic).post("http://rest-api.upskills.In/api/rest_admin/oauth2/token/client_credentials");
        String Variable = responsePost.getBody().asString();
        Pattern token_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher token_m = token_p.matcher(Variable);
        List<String> token_l = new ArrayList<String>();
        while (token_m.find()) {
            token_l.add(token_m.group(1));
        }
        String result = token_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        System.out.println(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        String access_token = jsonObject.get("access_token").getAsString();

        //Assertion
        System.out.println("StatusCode:" + responsePost.getStatusCode());
        int statusCode = responsePost.getStatusCode();
        assertEquals(statusCode, 200);
        return access_token;

    }

    //TO LOGIN USING GIVEN DETAILS
    @Test(priority = 2)
    public void Login() {
        String access_token = Token();

        Response LoginResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload).when().post("http://rest-api.upskills.in/api/rest_admin/login");
        String Login = LoginResponse.getBody().asString();
        Pattern login_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher login_m = login_p.matcher(Login);
        List<String> login_l = new ArrayList<String>();
        while (login_m.find()) {
            login_l.add(login_m.group(1));
        }
        String Logins = login_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject LoginsObject = new JsonParser().parse(Logins).getAsJsonObject();
        System.out.println(LoginsObject);

    }

    //TO GET USER DETAILS
    @Test(priority = 3)
    public void UserDetails() {
        String access_token = Token();

        Response UserResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload).when().get("http://rest-api.upskills.In/api/rest_admin/user");
        String User = UserResponse.getBody().asString();
        Pattern User_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher User_m = User_p.matcher(User);
        List<String> User_l = new ArrayList<String>();
        while (User_m.find()) {
            User_l.add(User_m.group(1));
        }
        String Userjson = User_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject UserjsonObject = new JsonParser().parse(Userjson).getAsJsonObject();
        System.out.println(UserjsonObject);
    }

    //TO DISPLAY PRODUCTS
    @Test(priority = 4)
    public void Products() {
        String access_token = Token();

        Response ProductResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload1).body(payload1).when().post("http://rest-api.upskills.In/api/rest_admin/products");
        String Product = ProductResponse.getBody().asString();
        Pattern Product_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher Product_m = Product_p.matcher(Product);
        List<String> Product_l = new ArrayList<String>();
        while (Product_m.find()) {
            Product_l.add(Product_m.group(1));
        }
        String Productjson = Product_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject ProductjsonObject = new JsonParser().parse(Productjson).getAsJsonObject();
        System.out.println(ProductjsonObject);
    }

    //TO SELECT A PRODUCT
    @Test(priority = 5)
    public void SelectProduct() {
        String access_token = Token();

        Response SelectProductResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload1).body(payload1).when().get("http://rest-api.upskills.In/api/rest_admin/products/60");
        String SelectProduct = SelectProductResponse.getBody().asString();
        Pattern SelectProduct_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher SelectProduct_m = SelectProduct_p.matcher(SelectProduct);
        List<String> SelectProduct_l = new ArrayList<String>();
        while (SelectProduct_m.find()) {
            SelectProduct_l.add(SelectProduct_m.group(1));
        }
        String Productjson = SelectProduct_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject ProductjsonObject = new JsonParser().parse(Productjson).getAsJsonObject();
        System.out.println(ProductjsonObject);
    }
    //TO ADD A PRODUCT
    @Test(priority = 6)
    public void Item() {
        String access_token = Token();

        Response ItemResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload1).body(payload1).when().put("http://rest-api.upskills.In/api/rest_admin/products/999");
        String Item = ItemResponse.getBody().asString();
        Pattern Item_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher Item_m = Item_p.matcher(Item);
        List<String> Item_l = new ArrayList<String>();
        while (Item_m.find()) {
            Item_l.add(Item_m.group(1));
        }
        String Items = Item_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject ItemsObject = new JsonParser().parse(Items).getAsJsonObject();
        System.out.println(ItemsObject);
    }

    //TO DISPLAY LIMITED PRODUCTS
    @Test(priority = 7)
    public void Limit() {
        String access_token = Token();

        Response ItemResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload1).body(payload1).when().get("http://rest-api.upskills.In/api/rest_admin/products/limit/2/page/1");
        String Item = ItemResponse.getBody().asString();
        Pattern Item_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher Item_m = Item_p.matcher(Item);
        List<String> Item_l = new ArrayList<String>();
        while (Item_m.find()) {
            Item_l.add(Item_m.group(1));
        }
        String Items = Item_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject ItemsObject = new JsonParser().parse(Items).getAsJsonObject();
        System.out.println(ItemsObject);
    }

    //TO LOGOUT
    @Test(priority = 8)
    public void Logout() {
        String access_token = Token();

        Response logoutResponse = RestAssured.given().auth().oauth2(access_token).header("Content-Type", "application/json ").body(payload1).body(payload1).when().get("http://rest-api.upskills.In/api/rest_admin/logout");
        String Item = logoutResponse.getBody().asString();
        Pattern Item_p = Pattern.compile("\\{([^{}]*)\\}");
        Matcher Item_m = Item_p.matcher(Item);
        List<String> Item_l = new ArrayList<String>();
        while (Item_m.find()) {
            Item_l.add(Item_m.group(1));
        }
        String Items = Item_l.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "{", "}"));
        JsonObject ItemsObject = new JsonParser().parse(Items).getAsJsonObject();
        System.out.println(ItemsObject);
    }
}


