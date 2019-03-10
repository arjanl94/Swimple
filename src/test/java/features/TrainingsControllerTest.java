package features;


import org.jboss.shrinkwrap.api.Filters;
import swimple.Seeds;
import swimple.factories.TrainingsFactory;
import io.restassured.RestAssured;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swimple.services.TrainingService;

import javax.inject.Inject;

import static io.restassured.RestAssured.when;

@RunWith(Arquillian.class)
public class TrainingsControllerTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "swimple-test.war")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackages(true, Filters.exclude(Seeds.class), "swimple");
    }

    @Inject
    TrainingService trainingService;

    @Before
    public void setUp() {
        TrainingsFactory trainingsFactory = new TrainingsFactory();

        trainingService.create(trainingsFactory.create());
    }

    @Test
    public void testIndex() {
        RestAssured.port = 7070;

        when()
            .get("/swimple-test/api/trainings")
        .then()
            .statusCode(200);
    }
}
