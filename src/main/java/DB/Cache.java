package DB;

import DB.entity.Route;
import DB.entity.Tariff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private static Cache instance;
    private static Map<String, List> cache = new HashMap<>();



    public static synchronized Cache getInstance() {
        if (instance == null) {

                instance = new Cache();

        }
        return instance;
    }

    public List<Route> getRouteList(){
        return cache.get("routeList");
    }

    public List<Tariff> getTariffList(){
        return cache.get("tariffList");
    }

    private Cache() {
        cache.put("routeList",DBManager.getInstance().getRouteList());
        cache.put("tariffList",DBManager.getInstance().getTariffList());


    }
}
