package fairyShop.models.Shops;

import fairyShop.models.Helpers.Helper;
import fairyShop.models.Presents.Present;

public interface Shop {
    void craft(Present present, Helper helper);
}
