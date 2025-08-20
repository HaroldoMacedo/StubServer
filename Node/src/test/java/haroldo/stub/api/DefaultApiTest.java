package haroldo.stub.api;

import org.junit.jupiter.api.Test;

public class DefaultApiTest {

    @Test
    public void basicTest() {
        Api api = new DefaultApi();
        assert(api.getUri().equals("/hello"));
        assert(api.getGetResponse().getMessage().equals("Hello World!"));
        assert(api.getPostResponse().getMessage().equals("Hello World!"));
        assert(api.getPutResponse().getMessage().equals("Hello World!"));
        assert(api.getDeleteResponse().getMessage().equals("Hello World!"));

        assert(api.getGetResponse().getLatencyMs() == 100);
        assert(api.getPostResponse().getLatencyMs() == 100);
        assert(api.getPutResponse().getLatencyMs() == 100);
        assert(api.getDeleteResponse().getLatencyMs() == 100);

        api.setGetResponse("Alo Mundo!", 200);
        assert(api.getGetResponse().getLatencyMs() == 200);
        assert(api.getGetResponse().getMessage().equals("Alo Mundo!"));

        api.setPostResponse("Alo Mundo Post!", 201);
        assert(api.getPostResponse().getLatencyMs() == 201);
        assert(api.getPostResponse().getMessage().equals("Alo Mundo Post!"));

        api.setPutResponse("Alo Mundo Put!", 202);
        assert(api.getPutResponse().getLatencyMs() == 202);
        assert(api.getPutResponse().getMessage().equals("Alo Mundo Put!"));

        api.setDeleteResponse("Alo Mundo Delete!", 203);
        assert(api.getDeleteResponse().getLatencyMs() == 203);
        assert(api.getDeleteResponse().getMessage().equals("Alo Mundo Delete!"));


        Response response = new Response();
        response.setMessage("Alo Mundo!");
        response.setLatencyMs(200);

        assert (response.getMessage().equals("Alo Mundo!"));
        assert (response.getLatencyMs() == 200);
    }
}
