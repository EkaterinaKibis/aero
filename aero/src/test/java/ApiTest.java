import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import com.jayway.restassured.http.ContentType;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ApiTest {

    // Master Data API Tests
    // Get MasterData Chennel, json fields exchange check
    @Test
    public void GetMasterDataChannel() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 0);
        firstElement.put("name", "");
        firstElement.put("lastupdate", "2019-11-28T09:26:56.316496Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 1);
        secondElement.put("name", "AFTN");
        secondElement.put("lastupdate", "2020-02-22T10:17:24.300842Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 2);
        thirdElement.put("name", "SITATEX");
        thirdElement.put("lastupdate", "2020-02-27T07:09:02.924871Z");
        JSONObject forthElement = new JSONObject();
        forthElement.put("id", 3);
        forthElement.put("name", "E-MAIL");
        forthElement.put("lastupdate", "2020-02-27T07:09:07.685768Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        request.put(forthElement);

        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/channel").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Chennel, status 200
    @Test
    public void GetMasterDataChannelStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/channel").
                then().
                assertThat().
                statusCode(200);
    }

    // Get MasterData Urgency
    @Test
    public void GetMasterDataUrgency() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 0);
        firstElement.put("name", "");
        firstElement.put("lastupdate", "2019-11-28T09:26:56.316496Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 1);
        secondElement.put("name", "QN");
        secondElement.put("lastupdate", "2020-02-22T10:17:30.757662Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 2);
        thirdElement.put("name", "FF");
        thirdElement.put("lastupdate", "2020-02-27T08:19:47.479724Z");
        JSONObject forthElement = new JSONObject();
        forthElement.put("id", 3);
        forthElement.put("name", "QU");
        forthElement.put("lastupdate", "2020-02-27T08:27:42.19552Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        request.put(forthElement);

        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/urgency").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Urgency, status 200
    @Test
    public void GetMasterDataUrgencyStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/urgency").
                then().
                assertThat().
                statusCode(200);
    }

    // Get MasterData Type
    @Test
    public void GetMasterDataType() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 0);
        firstElement.put("name", "");
        firstElement.put("lastupdate", "2019-11-28T09:26:56.316496Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 1);
        secondElement.put("name", "MVT");
        secondElement.put("lastupdate", "2020-02-22T10:17:14.996487Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 2);
        thirdElement.put("name", "FPL");
        thirdElement.put("lastupdate", "2020-02-27T07:08:09.734289Z");
        JSONObject forthElement = new JSONObject();
        forthElement.put("id", 3);
        forthElement.put("name", "PSM");
        forthElement.put("lastupdate", "2020-02-27T08:32:49.97047Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        request.put(forthElement);

        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/type").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Type, Status 200
    @Test
    public void GetMasterDataTypeStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/type").
                then().
                assertThat().
                statusCode(200);
    }

    // Get MasterData Filter_types
    @Test
    public void GetMasterDataFilter_types() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 1);
        firstElement.put("name", "match all of following");
        firstElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 2);
        secondElement.put("name", "match any of following");
        secondElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        request.put(firstElement);
        request.put(secondElement);
        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/filter-type").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Filter_Type, Status 200
    @Test
    public void GetMasterDataFilterTypeStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/filter-type").
                then().
                assertThat().
                statusCode(200);
    }

    // Get MasterData Operations
    @Test
    public void GetMasterDataOperations() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 1);
        firstElement.put("name", "contains");
        firstElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 2);
        secondElement.put("name", "doesn't contain");
        secondElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 3);
        thirdElement.put("name", "is");
        thirdElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject forthElement = new JSONObject();
        forthElement.put("id", 4);
        forthElement.put("name", "isn't");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject fivesElement = new JSONObject();
        forthElement.put("id", 5);
        forthElement.put("name", "begins with");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject sixElement = new JSONObject();
        forthElement.put("id", 6);
        forthElement.put("name", "ends with");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject sevanElement = new JSONObject();
        forthElement.put("id", 7);
        forthElement.put("name", "is before");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject eightElement = new JSONObject();
        forthElement.put("id", 8);
        forthElement.put("name", "is after");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject nineElement = new JSONObject();
        forthElement.put("id", 9);
        forthElement.put("name", "is higher than");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject tenElement = new JSONObject();
        forthElement.put("id", 10);
        forthElement.put("name", "is lower than");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject elevenElement = new JSONObject();
        forthElement.put("id", 11);
        forthElement.put("name", "is greater than");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject twelveElement = new JSONObject();
        forthElement.put("id", 12);
        forthElement.put("name", "is greater than");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject thirteenElement = new JSONObject();
        forthElement.put("id", 13);
        forthElement.put("name", "is in address book");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject fourteenElement = new JSONObject();
        forthElement.put("id", 14);
        forthElement.put("name", "isn't in address book");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject fifteenElement = new JSONObject();
        forthElement.put("id", 15);
        forthElement.put("name", "is empty");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject sixteenElement = new JSONObject();
        forthElement.put("id", 16);
        forthElement.put("name", "isn't empty");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        request.put(forthElement);
        request.put(fivesElement);
        request.put(sixElement);
        request.put(sevanElement);
        request.put(eightElement);
        request.put(nineElement);
        request.put(tenElement);
        request.put(elevenElement);
        request.put(twelveElement);
        request.put(thirteenElement);
        request.put(fourteenElement);
        request.put(fifteenElement);
        request.put(sixteenElement);

        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/operation").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Operations, Status 200
    @Test
    public void GetMasterDataOperationsStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/operation").
                then().
                assertThat().
                statusCode(200);
    }

    // Get MasterData Field_types
    @Test
    public void GetMasterDataField_types() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 1);
        firstElement.put("name", "string");
        firstElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 2);
        secondElement.put("name", "date");
        secondElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 3);
        thirdElement.put("name", "enum");
        thirdElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        JSONObject forthElement = new JSONObject();
        forthElement.put("id", 4);
        forthElement.put("name", "int");
        forthElement.put("lastupdate", "2020-03-04T16:00:18.039615Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        request.put(forthElement);
        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/master-data/field-type").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get MasterData Field-type, Status 200
    @Test
    public void GetMasterDataFieldTypeStatus200() {
        given().
                when().
                get("http://130.193.39.129/master-data/field-type").
                then().
                assertThat().
                statusCode(200);
    }

    /// GET master-data/fields!!!! Not READY
    @Test
    public void GetMasterDataFields() {

        given().
                when().
                get("http://130.193.39.129/master-data/field").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }

    //local-master-data
    // Master Data Get Filters
    @Test
    public void GetMasterDataFilters() {
        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 1);
        firstElement.put("name", "string1");
        String[] dtRangeArray = new String[]{"2020-03-06T00:00:00Z", "2030-03-06T00:00:00Z"};
        firstElement.put("dtRange", dtRangeArray);
        firstElement.put("folderName", "Air Traffic Control");
        //  firstElement.put("lastupdate", "2020-03-11T12:13:37.111349Z");
        JSONObject secondElement = new JSONObject();
        secondElement.put("id", 2);
        secondElement.put("name", "string_2");
        String[] dtRangeArray1 = new String[]{"2020-03-06T00:00:00Z", "2030-03-06T00:00:00Z"};
        secondElement.put("dtRange", dtRangeArray1);
        secondElement.put("folderName", "Air Traffic Control");
//        secondElement.put("lastupdate", "2020-03-12T12:08:16.064738Z");
        JSONObject thirdElement = new JSONObject();
        thirdElement.put("id", 5);
        thirdElement.put("name", "test");
        String[] dtRangeArray2 = new String[]{"2020-01-01T00:00:00Z", "2030-01-01T00:00:00Z"};
        thirdElement.put("dtRange", dtRangeArray2);
        thirdElement.put("folderName", "Air Traffic Control");
        // thirdElement.put("lastupdate", "2020-03-11T12:22:48.247586Z");
        request.put(firstElement);
        request.put(secondElement);
        request.put(thirdElement);
        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/filters").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    /// GET master-data/fields, Status 200
    @Test
    public void GetMasterDataFiltersStatus200() {

        given().
                when().
                get("http://130.193.39.129/filters").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }

    //POST Filters
    @Test
    public void sendPostWhithArray() throws Exception {

        JSONObject message = new JSONObject();
        message.put("id", 1);
        message.put("name", "Hello");
        message.put("filterTypeID", 0);
        message.put("folder", "folderHello");
        message.put("folderID", 0);

        JSONArray conditions = new JSONArray();
        JSONObject firstConditions = new JSONObject();
        firstConditions.put("fieldId", 0);
        firstConditions.put("operationId", 0);
        firstConditions.put("value", "kjbgfvkhfbvd");
        conditions.put(firstConditions);
        message.put("conditions", conditions);

        String[] dtRangeArray = new String[]{"2020-01-01T00:00:00Z", "2030-01-01T00:00:00Z"};
        message.put("dtRange", dtRangeArray);

        given().
                with().
                body(message).
                when().
                post("http://130.193.39.129/filters").
                then().
                statusCode(201);
        System.out.println(message);
    }

    ///GET Master Data local-master-data/filters/{id}
    @Test
    public void GetMasterDataFilterID() {

        JSONObject message = new JSONObject();
        message.put("id", 1);
        message.put("name", "string1");
        message.put("folderId", 4);
        message.put("folderName", "Air Traffic Control");
        message.put("filterTypeId", 2);

        JSONArray conditions = new JSONArray();
        JSONObject firstConditions = new JSONObject();
        firstConditions.put("fieldId", 2);
        firstConditions.put("operationId", 2);
        firstConditions.put("value", "MVT*");
        conditions.put(firstConditions);
        message.put("conditions", conditions);

        String[] dtRangeArray = new String[]{"2020-03-06T00:00:00Z", "2030-03-06T00:00:00Z"};
        message.put("dtRange", dtRangeArray);

        String expected = message.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/filters/1").
                        thenReturn();
        String result = response.getBody().asString();
        System.out.println(result);

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // Get Senders
    //http://130.193.39.129/senders
    @Test
    public void GetMasterDataSenders() {

        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 1);
        firstElement.put("address", "XXXX");
        firstElement.put("baseApId", 0);
        firstElement.put("channelName", "SITATEX");
        firstElement.put("channelId", 2);
        String[] dtRangeArray = new String[]{"2021-03-06T00:00:00Z", "2031-03-06T00:00:00Z"};
        firstElement.put("dtRange", dtRangeArray);
        firstElement.put("lastupdate", "2020-03-14T10:55:59.784153Z");
        request.put(firstElement);
        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/senders").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // POST Sender
    //
    @Test
    public void PostMasterDataSenders() {

        JSONObject message = new JSONObject();
        message.put("id", 2);
        message.put("name", "string");
        message.put("filterTypeID", 4);
        message.put("folder", "String");
        message.put("folderID", 0);

        JSONArray conditions = new JSONArray();
        JSONObject firstConditions = new JSONObject();
        firstConditions.put("fieldId", 0);
        firstConditions.put("operationId", 0);
        firstConditions.put("value", "string");
        conditions.put(firstConditions);
        message.put("conditions", conditions);

        String[] dtRangeArray = new String[]{"2020-03-06T00:00:00Z", "2030-03-06T00:00:00Z"};
        message.put("dtRange", dtRangeArray);
        String expected = message.toString();
        given().
                with().
                body(expected).
                when().
                post("http://130.193.39.129/senders").
                then().
                statusCode(201);
        System.out.println(expected);
    }

    //COMMUNICATION MANAGER
    // FOLDERS
//POST Create new Folder
    @Test
    public void PostCreateNewFolder() {

        JSONObject message = new JSONObject();
        message.put("id", 50);
        message.put("name", "Hola");
        String expected = message.toString();
        given().
                with().
                body(expected).
                when().
                post("http://130.193.39.129/folders").
                then().
                statusCode(201);
        System.out.println(expected);
    }
    //GET folders (Get folder tree) Дописать когда будет тестовая база
//    @Test
//    public void GetFolderTree() {
//
//        JSONArray request = new JSONArray();
//        JSONObject firstElement = new JSONObject();
//        firstElement.put("id", 50);
//        firstElement.put("name", "string");
//        firstElement.put("unread", 0);
//        firstElement.put("all", "0");
//        firstElement.put("children", "null");
////        JSONArray children = new JSONArray();
////        JSONObject firstConditions = new JSONObject();
////        firstConditions.put("id", 4);
////        firstConditions.put("name", "Air Traffic Control/TST");
////        firstConditions.put("value", "string");
////        children.put(firstConditions);
////        firstElement.put("conditions", children);
////        String[] dtRangeArray = new String[]{"2021-03-06T00:00:00Z", "2031-03-06T00:00:00Z"};
////        firstElement.put("dtRange", dtRangeArray);
//
//        request.put(firstElement);
//        String expected = request.toString();
//        Response response =
//                given().
//                        when().
//                        get("http://130.193.39.129/folders").
//                        thenReturn();
//        String result = response.getBody().asString();
//
//        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
//    }

//GET folders (Get folder tree) Статус 200
    @Test
    public void GetFolderTreeStatus200() {

        given().
                when().
                get("http://130.193.39.129/folders").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }
    // MESSAGES
    // GET Messages (Page 1, Limit 1)
    @Test
    public void GetMessages() {

        JSONArray request = new JSONArray();
        JSONObject firstElement = new JSONObject();
        firstElement.put("id", 39);
        firstElement.put("sender", "");
        firstElement.put("recipient", "");
        firstElement.put("dtMessage", "0001-01-01T00:00:00Z");
        firstElement.put("dtFlight", "0001-01-01T00:00:00Z");
        firstElement.put("dtInsert", "2020-03-19T05:28:55.138286Z");
        firstElement.put("read", false);

        firstElement.put("airport", "TST");
        request.put(firstElement);
        String expected = request.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/messages?page=1&limit=1").
                        thenReturn();
        String result = response.getBody().asString();

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }
// Post Send message
@Test
public void PostSendMessage() {

    JSONObject message = new JSONObject();
    message.put("id", 3);
    message.put("dtMessage", "2020-03-19T02:41:31.048Z");
    message.put("dtInsert", "2020-03-19T02:41:31.048Z");
    message.put("sender", "string");
    message.put("recipient", "string");
    message.put("typeId", 0);
    message.put("typeName", "string");
    message.put("subject", "string");
    message.put("airline", "string");
    message.put("flight", "string");

    message.put("dtFlight", "2020-03-19T02:41:31.048Z");
    message.put("uin", "string");
    message.put("message", "string");
    message.put("original", "string");

    message.put("channelId", 0);
    message.put("channelName", "string");
    message.put("urgencyId", 0);
    message.put("urgencyName", "string");
    message.put("lastupdate", "2020-03-19T02:41:31.049Z");
    message.put("errored", 0);
    String expected = message.toString();
    given().
            with().
            body(expected).
            when().
            post("http://130.193.39.129/messages").
            then().
            statusCode(201);
    System.out.println(expected);
}


// PUT Update message by Id
@Test
public void PutSendMessageById() {

    JSONObject message = new JSONObject();
    message.put("id", 2);
    message.put("dtMessage", "2019-11-29T01:02:31.542717Z");
    message.put("dtInsert","2019-11-29T01:02:31.542717Z");
    message.put("sender", "SVXIBU6");
    message.put("recipient", "DMEKRXH,DMEOPXH,SVXOP7X");

    message.put("typeId", 1);
    message.put("typeName", "MVT");
    message.put("dtFlight", "0001-05-01T00:00:00Z");
    message.put("uin", "050926 050425");
    message.put("message", "U6265/05.VQBNI.DME\\nED051940\\nDL04");
    message.put("channelId", 2);

    message.put("channelName", "SITATEX");
    message.put("urgencyId", 1);
    message.put("urgencyName", "FF");
    message.put("lastupdate", "2020-05-27T07:09:17.197482Z");
    String expected = message.toString();
    given().
            with().
            body(expected).
           // when().
            put("http://130.193.39.129/messages/2").
            then().
            statusCode(200);
    System.out.println(expected);
}
    // GET Message by ID
    @Test
    public void GetMessageByID() {

        JSONObject message = new JSONObject();
        message.put("id", 2);
        message.put("dtMessage", "2019-11-29T01:02:31.542717Z");
        message.put("dtInsert","2019-11-29T01:02:31.542717Z");
        message.put("sender", "SVXIBU6");
        message.put("recipient", "DMEKRXH,DMEOPXH,SVXOP7X");

        message.put("typeId", 1);
        message.put("typeName", "MVT");
        message.put("dtFlight", "0001-05-01T00:00:00Z");
        message.put("uin", "050926 050425");
        message.put("message", "U6265/05.VQBNI.DME\\nED051940\\nDL04");
        message.put("channelId", 2);

        message.put("channelName", "SITATEX");
        message.put("urgencyId", 1);
        message.put("urgencyName", "QN");
        message.put("lastupdate", "2020-03-19T08:47:44.062177Z");


        String expected = message.toString();
        Response response =
                given().
                        when().
                        get("http://130.193.39.129/messages/2").
                        thenReturn();
        String result = response.getBody().asString();
        System.out.println(result);

        JSONAssert.assertEquals(expected, result, JSONCompareMode.LENIENT);
    }

    // PUT
//
//        HttpResponse httpResponse2 = Request.Post("http://testserv.ru/api/").bodyForm(new BasicNameValuePair("Парам1", "1"), new BasicNameValuePair("Парам2", Значение), new BasicNameValuePair("Парам3", Значение)).execute();
//
//
//        return httpResponse2;
//    }
//
    @Test
    public void sendPost() {

        JSONObject message = new JSONObject();
        message.put("parentId", 0);
        message.put("name", "Hello");
        given().
                with().
                body(message).
                post("http://130.193.39.129/folders").
                then().
                assertThat().
                statusCode(201);
    }

}
//
//
//[{"id":1,"name":"Incoming","unread":0,"all":5,"children":[{"id":4,"name":"Air Traffic Control/TST","unread":1,"all":6,"children":null}
//,{"id":10,"name":"Cargo/TST","unread":1,"all":1,"children":null},{"id":23,"name":"Subfolder/TST","unread":0,"all":0,"children":null}]}
//,{"id":2,"name":"Sent","unread":2,"all":6,"children":[{"id":22,"name":"Test subfolder send/TST","unread":0,"all":0,"children":null}]},
//        {"id":3,"name":"Draft","unread":0,"all":0,"children":null},{"id":39,"name":"","unread":0,"all":0,"children":null},{"id":40,"name":"","unread":0,"all":0,"children":null},
//        {"id":41,"name":"","unread":0,"all":0,"children":null},{"id":42,"name":"","unread":0,"all":0,"children":null},
//        {"id":43,"name":"","unread":0,"all":0,"children":null},{"id":44,"name":"string","unread":0,"all":0,"children":null}
//        ,{"id":45,"name":"","unread":0,"all":0,"children":null},{"id":46,"name":"","unread":0,"all":0,"children":null},
//        {"id":47,"name":"","unread":0,"all":0,"children":null},{"id":48,"name":"","unread":0,"all":0,"children":null},
//        {"id":49,"name":"","unread":0,"all":0,"children":null},{"id":50,"name":"string","unread":0,"all":0,"children":null}]
