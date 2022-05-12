import DB.Utils;
import DB.entity.Order;
import DB.entity.Route;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {
    @Test
    public void testGetPage() {
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            testList.add(i);
        }
        List<Integer> testCase1 = new ArrayList<>();
        testCase1.add(5);
        testCase1.add(6);
        testCase1.add(7);
        testCase1.add(8);
        testCase1.add(9);

        List<Integer> testCase2 = new ArrayList<>();
        testCase2.add(0);
        testCase2.add(1);
        testCase2.add(2);


        Assert.assertEquals(Utils.getPage(testList, 5, 1), testCase1);
        Assert.assertEquals(Utils.getPage(testList, 3, 0), testCase2);
        Assert.assertEquals(Utils.getPage(testList, 55, 0), testList);


    }

    @Test
    public void testFilterRouteList() {
        List<Route> routeList = new ArrayList<>();
        routeList.add(new Route(1L, "A", "B", 123));
        routeList.add(new Route(2L, "A", "B", 123));
        routeList.add(new Route(3L, "C", "B", 123));
        routeList.add(new Route(4L, "C", "B", 123));
        routeList.add(new Route(5L, "C", "D", 123));
        routeList.add(new Route(6L, "C", "D", 123));

        List<Route> routeList2 = new ArrayList<>(routeList);
        List<Route> routeList1 = new ArrayList<>(routeList);

        List<Route> testCase1 = new ArrayList<>();
        testCase1.add(routeList.get(2));
        testCase1.add(routeList.get(3));

        List<Route> testCase2 = new ArrayList<>();
        testCase2.add(routeList1.get(2));
        testCase2.add(routeList1.get(3));

        List<Route> testCase3 = new ArrayList<>();
        testCase3.add(routeList2.get(2));
        testCase3.add(routeList2.get(3));
        testCase3.add(routeList2.get(4));
        testCase3.add(routeList2.get(5));

        List<Route> testCase4 = new ArrayList<>();
        testCase4.add(routeList2.get(4));
        testCase4.add(routeList2.get(5));


        Assert.assertEquals(Utils.filterRouteList(routeList, "C", "B"), testCase1);
        Assert.assertEquals(Utils.filterRouteList(routeList, "C", "D"), new ArrayList<>());
        Assert.assertEquals(Utils.filterRouteList(routeList1, "C", "B"), testCase2);
        Assert.assertEquals(Utils.filterRouteList(routeList2, "C", ""), testCase3);
        Assert.assertEquals(Utils.filterRouteList(routeList2, "C", "D"), testCase4);


    }

    @Test
    public void testSortRouteList(){
        List<Route> routeList = new ArrayList<>();
        routeList.add(new Route(1L, "A", "B", 123));
        routeList.add(new Route(2L, "A", "B", 123));
        routeList.add(new Route(3L, "C", "B", 123));
        routeList.add(new Route(4L, "C", "B", 123));
        routeList.add(new Route(5L, "C", "D", 123));
        routeList.add(new Route(6L, "C", "D", 123));

        List<Route> routeList1 = new ArrayList<>(routeList);
        List<Route> testCase1 = new ArrayList<>();
        testCase1.add(routeList.get(4));
        testCase1.add(routeList.get(5));
        testCase1.add(routeList.get(0));
        testCase1.add(routeList.get(1));
        testCase1.add(routeList.get(2));
        testCase1.add(routeList.get(3));

        List<Route> testCase2 = new ArrayList<>();
        testCase2.add(routeList.get(2));
        testCase2.add(routeList.get(3));
        testCase2.add(routeList.get(4));
        testCase2.add(routeList.get(5));
        testCase2.add(routeList.get(0));
        testCase2.add(routeList.get(1));



        Assert.assertEquals(Utils.sortRouteList(routeList,"destinationDescending"),testCase1);
        Assert.assertEquals(Utils.sortRouteList(routeList,"destinationAscending"),routeList1);

        Assert.assertEquals(Utils.sortRouteList(routeList,"startDescending"),testCase2);
        Assert.assertEquals(Utils.sortRouteList(routeList,"startAscending"),routeList1);


    }

    @Test
    public void testPageCount(){
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            testList.add(i);
        }

        Assert.assertEquals(Utils.pageCount(testList,1),51);
        Assert.assertEquals(Utils.pageCount(testList,2),26);
        Assert.assertEquals(Utils.pageCount(testList,10),6);
        Assert.assertEquals(Utils.pageCount(testList,50),2);
        Assert.assertEquals(Utils.pageCount(testList,90),1);
    }

    @Test
    public void testSortOrderList(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.get(0).setOrderStatus("finished");
        orderList.get(0).setId(1L);
        orderList.get(1).setOrderStatus("in progress");
        orderList.get(1).setId(2L);
        orderList.get(2).setOrderStatus("awaiting payment");
        orderList.get(2).setId(3L);
        orderList.get(3).setOrderStatus("awaiting approval");
        orderList.get(3).setId(4L);

        List<Order> testCase = new ArrayList<>();
        testCase.add(orderList.get(3));
        testCase.add(orderList.get(2));
        testCase.add(orderList.get(0));
        testCase.add(orderList.get(1));

        Assert.assertEquals(Utils.sortOrderList(orderList,"statusAscending"),testCase);


    }

}
