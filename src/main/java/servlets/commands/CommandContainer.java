package servlets.commands;

import servlets.commands.clientProfile.ClientProfileCommand;
import servlets.commands.clientProfile.GoToClientsOrdersPageCommand;
import servlets.commands.clientProfile.PayForOrderCommand;
import servlets.commands.clientProfile.SortClientsOrdersCommand;
import servlets.commands.loginBlock.ChangeLanguageCommand;
import servlets.commands.loginBlock.LoginCommand;
import servlets.commands.loginBlock.LogoutCommand;
import servlets.commands.loginBlock.RegisterCommand;
import servlets.commands.managerProfile.*;
import servlets.commands.priceCalculator.CalculateExpectedPriceCommand;
import servlets.commands.priceCalculator.GetAvailableDestinationsCommand;
import servlets.commands.priceCalculator.PlaceOrderCommand;
import servlets.commands.routeTable.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {


    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();

        commands.put("login", new LoginCommand());
        commands.put("register",new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("sortRouteTable", new SortRouteTableCommand());
        commands.put("filterList", new FilterCommand());
        commands.put("resetList", new ResetListCommand());
        commands.put("getAvailableDestinations", new GetAvailableDestinationsCommand());
        commands.put("calculateExpectedPrice", new CalculateExpectedPriceCommand());
        commands.put("clientProfile", new ClientProfileCommand());
        commands.put("managerProfile", new ManagerProfileCommand());
        commands.put("placeOrder", new PlaceOrderCommand());
        commands.put("payForOrder", new PayForOrderCommand());
        commands.put("approveOrder", new ApproveOrderCommand());
        commands.put("terminateOrder", new TerminateOrderCommand());
        commands.put("finishOrder", new FinishOrderCommand());
        commands.put("goToRoutePage", new GoToRoutePageCommand());
        commands.put("setItemsPerRoutePage", new SetItemsPerRoutePageCommand());
        commands.put("goToClientsOrdersPage", new GoToClientsOrdersPageCommand());
        commands.put("sortClientOrderList", new SortClientsOrdersCommand());
        commands.put("goToManagerOrdersPage", new GoToManagerOrdersPageCommand());
        commands.put("sortManagerOrderList", new SortManagerOrdersCommand());
        commands.put("changeLanguage",new ChangeLanguageCommand());



        // ...
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
