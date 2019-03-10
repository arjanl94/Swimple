//package features;
//
//import swimple.controllers.HelloWorldController;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.Test;
//
//import javax.ws.rs.core.swimple.controllers.Application;
//
//import static org.junit.Assert.assertEquals;
//
//public class HelloWorldTest extends JerseyTest {
//
//    @Test
//    public void testCanViewHelloWorld() {
//        String hello = target("/hello").request().get(String.class);
//        assertEquals("Hello World!", hello);
//    }
//
//    @Override
//    protected swimple.controllers.Application configure() {
//        return new ResourceConfig(HelloWorldController.class);
//    }
//}
