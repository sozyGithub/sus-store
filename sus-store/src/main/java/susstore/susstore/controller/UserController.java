package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.models.MemberVIP;
import susstore.susstore.Subscriber;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Customer> customers;
    private List<Member> members;
    private List<Integer> vips;

    public UserController() {
        this.customers = new ArrayList<Customer>();
        this.subscribers = new SubscriberManager();
        this.members = new ArrayList<Member>();
        this.vips = new ArrayList<Integer>();
    }

    public UserController(List<Customer> barang) {
        this.customers = barang;
        this.subscribers = new SubscriberManager();
    }

    public String addCustomer(Customer c) {
        this.customers.add(c);
        return this.customers.get(this.customers.size() - 1).getUserID().toString();
        //this.subscribers.notifysubs();
    }

    public void addMember(Member c) {
        this.members.add(c);
        this.addCustomer(c);
        this.subscribers.notifysubs("add-member");
    }

    public void editMember(UUID id, String name, String phone, String type, boolean isActive) {
        Integer mid = getMemberIdxByID(id);
        Member m = members.get(mid);
        m.setNama(name);
        m.setNoTelp(phone);
        m.setStatus(isActive);
        if (m.getNama() != type) {
            if (type == "MEMBER") {
                deleteFromVIP(m, mid);
            } else {
                addToVIP(m, mid);
            }
        }
        this.subscribers.notifysubs("edit-member");
    }

    public void addToVIP(Member m, Integer id) {
        this.members.set(id, new MemberVIP(m));
    }

    public void deleteFromVIP(Member m, Integer id) {
        this.members.set(id, new Member(m));
    }

    public Integer getMemberIdxByID(UUID id) {
        int counter = 0;
        for (Member m : members) {
            if (m.getUserID().equals(id)) return counter;
            counter++;
        }
        return null;
    }

    public void addVIP(Integer c) {
        this.vips.add(c);
        //this.subscribers.notifysubs();
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Customer> customer) {
        this.customers = customer;
        //this.subscribers.notify();
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        //this.subscribers.notify();
    }

    public List<Integer> getVIPs() {
        return this.vips;
    }

    public void setVIPs(List<Integer> vips) {
        this.vips = vips;
        //this.subscribers.notify();
    }

    public void addSubscriber(Subscriber s) {
        this.subscribers.subscribe(s);
    }
}
