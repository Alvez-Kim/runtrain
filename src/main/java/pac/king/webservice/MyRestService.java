package pac.king.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pac.king.pojo.User;

@Path("/")
public interface MyRestService {
	
	@POST
	@Path("limitUsers/{count}")
    @Produces({ MediaType.APPLICATION_XML })
	public User[] userInfos(@PathParam("count")int cnt);
}