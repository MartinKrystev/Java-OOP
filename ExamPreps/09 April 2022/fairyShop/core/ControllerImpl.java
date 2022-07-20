package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.Helpers.Happy;
import fairyShop.models.Helpers.Helper;
import fairyShop.models.Helpers.Sleepy;
import fairyShop.models.Instruments.Instrument;
import fairyShop.models.Instruments.InstrumentImpl;
import fairyShop.models.Presents.Present;
import fairyShop.models.Presents.PresentImpl;
import fairyShop.models.Shops.Shop;
import fairyShop.models.Shops.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

public class ControllerImpl implements Controller {
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private Shop shop;
    private int craftedPresents;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);

        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        helper.addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helper.getName());
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, present.getName());
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = presentRepository.findByName(presentName);

        Helper helper = this.helperRepository.getModels().stream().filter(h -> h.getEnergy() > 50).findFirst().orElse(null);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        shop.craft(present, helper);

        int brokenInstruments = (int) helper.getInstruments().stream().filter(i -> i.isBroken()).count();

        String doneNotDone;

        if (present.isDone()) {
            doneNotDone = "done";
            this.craftedPresents++;
        } else {
            doneNotDone = "not done";
        }

        return String.format(ConstantMessages.PRESENT_DONE, presentName, doneNotDone)
                + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d presents are done!", craftedPresents)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());

        this.helperRepository
                .getModels()
                .forEach(h -> sb.append(h.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
