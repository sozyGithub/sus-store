package susstore.susstore;

import susstore.susstore.models.Storable;

import java.util.ArrayList;
import java.util.List;

public class TemporaryBillController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Storable> temporaryBills;

    public TemporaryBillController(){
        this.temporaryBills = new ArrayList<Storable>();
    }
}
