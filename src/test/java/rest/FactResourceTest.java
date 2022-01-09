package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Role;
import entities.User;
import errorhandling.API_Exception;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import security.LoginEndpoint;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Disabled

class FactResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/CA2/api/facts";
    private static User u1, u2, u3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static org.glassfish.grizzly.http.server.HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        // This method must be called before you request the EntityManagerFacotry
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        // Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        // System.in.read();
        // Don't forget this, if you called it's counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    void setUp() throws AuthenticationException, API_Exception {

        em = emf.createEntityManager();

        u1 = new User("bob","test1");
        u2 = new User("tut","test2");
        u3 = new User("rap","test3");

        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        u1.addRole(userRole);
        u2.addRole(adminRole);
        u3.addRole(userRole);
        u3.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.getTransaction().commit();

        System.out.println("PW: " + u1.getUserPass());
        System.out.println("Testing user with OK password: " + u1.verifyPassword("test1"));
        System.out.println("Testing user with wrong password: " + u1.verifyPassword("test2"));
        System.out.println("Created TEST Users");

        LoginEndpoint loginEndpoint = new LoginEndpoint();
        String jsonString = "{\n" +
                "    \"username\" : \"bob\",\n" +
                "    \"password\" : \"test1\"\n" +
                "}";
        loginEndpoint.login(jsonString);

    }
    @Test
    void getFacts() {
        given()
                .contentType("application/json")
                .get(SERVER_URL).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body(equalTo("5"))
                .body("catFact",notNullValue())
                .body("dogFact",notNullValue())
                .body("numberFact",notNullValue())
                .body("randomFact",notNullValue())
                .body("funFact",notNullValue());

    }
}