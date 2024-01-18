package vn.supperapp.apigw.utils;

import vn.supperapp.apigw.db.dto.Branch;

import java.util.ArrayList;
import java.util.List;

public class LocationUtils {
    private Location getLocation(Location location, Branch branch){
        Location location1 = new Location();
        location1.setId(branch.getCode());
        location1.setName(branch.getName());
        location1.setLatitude(Double.parseDouble(branch.getLatitude()));
        location1.setLongitude(Double.parseDouble(branch.getLongitude()));
        if (location1.getId().equals(location.getId()))
        {
            return location1;
        }
        return null;
    }
    public int CheckLocation(Location location, Branch branch)
    {
        if (location.getLatitude() == 0 || location.getLongitude() == 0)
        {
            return 0;
        }
        Location bus = getLocation(location, branch);
        if (bus == null)
        {
            return 1;
        }
        double a = CalculateDistance(location, bus);
        if (CalculateDistance(location, bus) > 50)
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }

    public double CalculateDistance(Location point1, Location point2)
    {
        double d1 = point1.getLatitude() * (Math.PI / 180.0);
        double num1 = point1.getLongitude() * (Math.PI / 180.0);
        double d2 = point2.getLatitude() * (Math.PI / 180.0);
        double num2 = point2.getLongitude() * (Math.PI / 180.0) - num1;
        double d3 = Math.pow(Math.sin((d2 - d1) / 2.0), 2.0) +
                Math.cos(d1) * Math.cos(d2) * Math.pow(Math.sin(num2 / 2.0), 2.0);
        return 6376500.0 * (2.0 * Math.atan2(Math.sqrt(d3), Math.sqrt(1.0 - d3)));
    }
}
