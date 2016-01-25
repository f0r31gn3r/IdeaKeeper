package lv.javaguru.java3.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.java3.core.dto.user.UserDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.XML;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Anna on 23.01.2016.
 */
@ManagedBean
@RequestScoped
public class PersonEditBean {

    private String welcomeMessage = "Populated by JSF created bean";

    public UserDTO userDTO;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getPersonById(String link) {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(link);
        HttpResponse response;
        String result = null;
        UserDTO userDTO = null;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                result = convertStreamToString(instream);
                System.out.println("RESPONSE result: " + result);

                org.json.JSONObject xmlJSONObj = XML.toJSONObject(result);
                System.out.println("RESPONSE json: " + xmlJSONObj);
                //userDTO = xmlJSONObj;

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = xmlJSONObj.toString();
                jsonString = jsonString.substring(jsonString.indexOf(":")+1, jsonString.length()-1);
                System.out.println("RESPONSE json string: " + jsonString);

                userDTO = mapper.readValue(jsonString, UserDTO.class);

                instream.close();
            }
            // Headers
            org.apache.http.Header[] headers = response.getAllHeaders();
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return userDTO;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}