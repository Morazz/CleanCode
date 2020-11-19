import Planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> GetPassengerPlanes() {
        return planes.stream().filter(p -> p instanceof PassengerPlane).map(p -> (PassengerPlane)p).collect(Collectors.toList());
    }

    public List<MilitaryPlane> GetMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof  MilitaryPlane).map(p -> (MilitaryPlane)p).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> GetUnclassifiedExperimentalPlanes() {
        return planes.stream().filter(p -> p instanceof ExperimentalPlane).map(p -> (ExperimentalPlane)p)
                .filter(p -> p.GetClassificationLevel() == ClassificationLevel.UNCLASSIFIED).collect(Collectors.toList());
    }

    public PassengerPlane GetPassengerPlaneWithMaxPassengersCapacity() {
        return this.GetPassengerPlanes().stream().map(p -> (PassengerPlane)p).max(new Comparator<PassengerPlane>() {
            @Override
            public int compare(PassengerPlane o1, PassengerPlane o2) {
                return o1.GetPassengersCapacity() - o2.GetPassengersCapacity();
            }
        }).get();
    }

    public List<MilitaryPlane> GetTransportMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof MilitaryPlane).map(p -> (MilitaryPlane)p)
                .filter(p -> p.GetType() == MilitaryType.TRANSPORT).collect(Collectors.toList());
    }

    public List<MilitaryPlane> GetBomberMilitaryPlanes() {
        return planes.stream().filter(p -> p instanceof MilitaryPlane).map(p -> (MilitaryPlane)p)
                .filter(p->p.GetType() == MilitaryType.BOMBER).collect(Collectors.toList());
    }

    public List<ExperimentalPlane> GetExperimentalPlanes() {
        return planes.stream().filter(p -> p instanceof  ExperimentalPlane).map(p -> (ExperimentalPlane)p).collect(Collectors.toList());
    }

    public Airport SortPlanesByMaxDistance() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.GetMaxFlightDistance() - o2.GetMaxFlightDistance();
            }
        });
        return this;
    }

    public Airport SortByMaxSpeed() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.GetMaxSpeed() - o2.GetMaxSpeed();
            }
        });
        return this;
    }

    public Airport SortByMaxLoadCapacity() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane o1, Plane o2) {
                return o1.GetMaxLoadCapacity() - o2.GetMaxLoadCapacity();
            }
        });
        return this;
    }

    public List<? extends Plane> GetPlanes() {
        return planes;
    }

    private void print(Collection<? extends Plane> collection) {
        planes.stream().forEachOrdered(p -> System.out.println(p.toString()));
    }

    public String ToString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}
