package com.booking.repositories;

import java.util.Arrays;
import java.util.List;

import com.booking.models.Service;

public class ServiceRepository {

    private List<Service> serviceList;

    private ServiceRepository(){
        initialServiceDataDummy();
    }

    public List<Service> getAll(){
        return serviceList;
    }

     private void initialServiceDataDummy(){
        if (serviceList == null){
        Service service1 = new Service("Serv-01", "Potong Rambut", 75000);
        Service service2 = new Service("Serv-02", "Styling Rambut", 125000);
        Service service3 = new Service("Serv-03", "Pewarnaan Rambut", 500000);
        Service service4 = new Service("Serv-04", "Rebonding", 60000);
        Service service5 = new Service("Serv-05", "Mengeriting Rambut", 70000);

        serviceList = Arrays.asList(service1, service2, service3, service4, service5);
        }
    }

    private static ServiceRepository INSTANCE;

    public static ServiceRepository getInstance() {
        if (INSTANCE == null){
           INSTANCE = new ServiceRepository();
        }
         return INSTANCE;
    }
}
