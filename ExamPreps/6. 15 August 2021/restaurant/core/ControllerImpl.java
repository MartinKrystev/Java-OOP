package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;


    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = null;
        switch (type) {
            case "Salad":
                food = new Salad(name, price);
                break;
            case "VeganBiscuits":
                food = new VeganBiscuits(name, price);
                break;
        }

        HealthyFood alreadyAdded = this.healthFoodRepository.foodByName(name);
        if (alreadyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }

        healthFoodRepository.add(food);
        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = null;
        switch (type) {
            case "Fresh":
                beverage = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverage = new Smoothie(name, counter, brand);
                break;
        }

        Beverages alreadyAdded = this.beverageRepository.beverageByName(name, brand);
        if (alreadyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }

        beverageRepository.add(beverage);
        return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        switch (type) {
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
        }

        Table alreadyAdded = this.tableRepository.byNumber(tableNumber);
        if (alreadyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        tableRepository.add(table);
        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = this.tableRepository.getAllEntities()
                .stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (table == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood food = this.healthFoodRepository.foodByName(healthyFoodName);
        if (food == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(food);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverage = this.beverageRepository.beverageByName(name, brand);
        if (beverage == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverage);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        this.totalMoney += bill;
        table.clear();

        return String.format(OutputMessages.BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY, this.totalMoney);
    }
}
