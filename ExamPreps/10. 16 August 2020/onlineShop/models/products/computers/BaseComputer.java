package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance() + 0.0;
        } else {
            return components.stream().mapToDouble(c -> c.getOverallPerformance()).average().orElse(0.0);
        }
    }

    @Override
    public double getPrice() {
        double sum = 0;

        sum += super.getPrice();
        sum += components.stream().mapToDouble(Component::getPrice).sum();
        sum += peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        return sum;
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (components.stream().anyMatch(c -> c.getClass().equals(component.getClass()))) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                    , component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }

        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = components.stream().filter(c -> c.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);

        if (component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT
                    , componentType, this.getClass().getSimpleName(), this.getId()));
        }

        components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.stream().anyMatch(p -> p.getClass().equals(peripheral.getClass()))) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL
                    , peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }

        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {

        Peripheral peripheral = peripherals.stream().filter(p -> p.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);

        if (peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL
                    , peripheralType, this.getClass().getSimpleName(), this.getId()));
        }

        peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(PRODUCT_TO_STRING
                , this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName()
                , this.getManufacturer(), this.getModel(), this.getId()));
        sb.append(System.lineSeparator());

        sb.append(" ").append(String.format(COMPUTER_COMPONENTS_TO_STRING, components.size()));
        sb.append(System.lineSeparator());

        if (!components.isEmpty()) {
            for (Component c : this.components) {
                sb.append("  ").append(c.toString()).append(System.lineSeparator());
            }
        }

        sb.append(" ").append(String.format(COMPUTER_PERIPHERALS_TO_STRING
                , peripherals.size()
                , peripherals.stream().mapToDouble(p -> p.getOverallPerformance()).average().orElse(0.0)));

        if (!peripherals.isEmpty()) {
            for (Peripheral p : peripherals) {
                sb.append("  ").append(p.toString()).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
