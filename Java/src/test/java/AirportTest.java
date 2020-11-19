import Planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-81 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET),
            new ExperimentalPlane("Ryan Z-13 Vertijet", 780, 541, 250, ExperimentalTypes.LIFTING_BODY, ClassificationLevel.UNCLASSIFIED),
            new ExperimentalPlane("Ryan VV-13 Vertijet", 900, 409, 678, ExperimentalTypes.HYPERSONIC, ClassificationLevel.CONFIDENTIAL)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void GetTransportMilitaryPlanesTest() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.GetTransportMilitaryPlanes();
        Assert.assertTrue(transportMilitaryPlanes.size() > 0);
    }

    @Test
    public void GetPassengerPlaneWithMaxCapacityTest() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.GetPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(planeWithMaxPassengerCapacity, expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void SortByMaxSpeedTest(){
        Airport airport = new Airport(planes);
        airport.SortByMaxSpeed();
        List<? extends Plane> planesSortedByMaxSpeed = airport.GetPlanes();
        Plane nextPlaneMaxSpeed = planesSortedByMaxSpeed.get(airport.GetPlanes().size()-1);
        Assert.assertTrue(nextPlaneMaxSpeed.GetMaxSpeed() > airport.GetPlanes().get(airport.GetPlanes().size()-2).GetMaxSpeed());
    }

    @Test
    public void SortByMaxLoadCapacityTest() {
        Airport airport = new Airport(planes);
        airport.SortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.GetPlanes();
        Plane nextPlaneMaxLoadCapacity = planesSortedByMaxLoadCapacity.get(airport.GetPlanes().size()-1);
        Assert.assertTrue(nextPlaneMaxLoadCapacity.GetMaxLoadCapacity() > airport.GetPlanes().get(airport.GetPlanes().size()-2).GetMaxLoadCapacity());
    }

    @Test
    public void SortByMaxFlightDistanceTest() {
        Airport airport = new Airport(planes);
        airport.SortPlanesByMaxDistance();
        List<? extends Plane> planesSortedByMaxFlightDistance = airport.GetPlanes();
        Plane nextPlaneMaxFlightDistance = planesSortedByMaxFlightDistance.get(airport.GetPlanes().size()-1);
        Assert.assertTrue(nextPlaneMaxFlightDistance.GetMaxFlightDistance() > airport.GetPlanes().get(airport.GetPlanes().size()-2).GetMaxFlightDistance());
    }

    @Test
    public void HasAtLeastOneBomberInMilitaryPlanesTest() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.GetBomberMilitaryPlanes();
        Assert.assertTrue(bomberMilitaryPlanes.size() > 0);
    }

    @Test
    public void ExperimentalPlanesHasClassificationLevelHigherThanUnclassifiedTest(){
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalUnclassifiedPlanes = airport.GetUnclassifiedExperimentalPlanes();
        Assert.assertNotEquals(experimentalUnclassifiedPlanes.size(), 0);
    }
}
