package susstore.susstore.models.wrappers;

import susstore.susstore.models.FixedBill;

import java.util.List;

public class FixedBillWrapper {
    private List<FixedBill> fixedBillList;

    public List<FixedBill> getFixedBillList() {
        return fixedBillList;
    }

    public void setFixedBillList(List<FixedBill> fixedBillList) {
        this.fixedBillList = fixedBillList;
    }

    public FixedBillWrapper(List<FixedBill> fixedBillList) {
        this.fixedBillList = fixedBillList;
    }
}