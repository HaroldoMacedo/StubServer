package haroldo.stub.api;

import org.junit.jupiter.api.Test;

public class DefaultApiTest {

    @Test
    public void basicTest() {
        Api api = new DefaultApi("/hello", "Hello World!", 100);
        assert(api.getUri().equals("/hello"));
        assert(api.getNextGetResponse().getMessage().equals("Hello World!"));
        assert(api.getNextPostResponse().getMessage().equals("Hello World!"));
        assert(api.getNextPutResponse().getMessage().equals("Hello World!"));
        assert(api.getNextDeleteResponse().getMessage().equals("Hello World!"));

        assert(api.getNextGetResponse().getLatencyMs() == 100);
        assert(api.getNextPostResponse().getLatencyMs() == 100);
        assert(api.getNextPutResponse().getLatencyMs() == 100);
        assert(api.getNextDeleteResponse().getLatencyMs() == 100);

        api = new DefaultApi();
        api.addGetResponse("Alo Mundo!", 200);
        assert(api.getNextGetResponse().getLatencyMs() == 200);
        assert(api.getNextGetResponse().getMessage().equals("Alo Mundo!"));

        api.addPostResponse("Alo Mundo Post!", 201);
        assert(api.getNextPostResponse().getLatencyMs() == 201);
        assert(api.getNextPostResponse().getMessage().equals("Alo Mundo Post!"));

        api.addPutResponse("Alo Mundo Put!", 202);
        assert(api.getNextPutResponse().getLatencyMs() == 202);
        assert(api.getNextPutResponse().getMessage().equals("Alo Mundo Put!"));

        api.addDeleteResponse("Alo Mundo Delete!", 203);
        assert(api.getNextDeleteResponse().getLatencyMs() == 203);
        assert(api.getNextDeleteResponse().getMessage().equals("Alo Mundo Delete!"));


        Response response = new Response();
        response.setMessage("Alo Mundo!");
        response.setLatencyMs(200);

        assert (response.getMessage().equals("Alo Mundo!"));
        assert (response.getLatencyMs() == 200);
    }
}
