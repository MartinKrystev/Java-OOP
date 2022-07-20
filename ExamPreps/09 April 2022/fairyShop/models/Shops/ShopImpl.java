package fairyShop.models.Shops;

import fairyShop.models.Helpers.Helper;
import fairyShop.models.Instruments.Instrument;
import fairyShop.models.Presents.Present;

import java.util.ArrayList;
import java.util.List;

public  class ShopImpl implements Shop{

    @Override
    public void craft(Present present, Helper helper) {
        List<Instrument> helperInstruments = new ArrayList<>(helper.getInstruments());

        while (helper.canWork()) {
            Instrument instrument = helperInstruments.stream().filter(i -> !i.isBroken()).findFirst().orElse(null);

            if (instrument == null) {
                break;
            }

            while (!present.isDone()) {
                helper.work();
                instrument.use();
                present.getCrafted();

                if (instrument.isBroken()) {
                    instrument = helperInstruments.stream().filter(i -> !i.isBroken()).findFirst().orElse(null);
                }

                if (!helper.canWork() || instrument == null) {
                    break;
                }
            }

            if (present.isDone()){
                break;
            }
        }

    }
}
