package ih.rs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/insurance")
public class InsuranceResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(){
        return "Hello from RS service";
    }
}
