package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.plugin2.os.windows.Windows;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //    1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("The limit is " + limit);
    }

    //2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("The total is " + total);
    }

    //3. Extract the name of 5th store
    @Test
    public void nameOf5thStore() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th store is : " + name);
    }

    //4. Extract the names of all the store
    @Test
    public void namesOfAllStore() {
        List<String> names = response.extract().path("data.name");
        System.out.println("Name of the all stores are : " + names);
    }

    //5. Extract the storeId of all the store
    @Test
    public void allStoresId() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("All store's Id are : " + ids);
    }

    //6. Print the size of the data list
    @Test
    public void sizeOfDatatList() {
        List<?> data = response.extract().path("data");
        int size = data.size();
        System.out.println("The size of the data list is :" + size);
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void valueOfStore() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("All the value of the store where store name = St Cloud " + value);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void addressOfStore() {
        List<?> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Address of the store where store name = Rochester :" + address);
    }

    //9. Get all the services of 8th store
    @Test
    public void getServices() {
        List<List<String>> services = response.extract().path("data[8].services");
        System.out.println("List of all the services of 8th store " + services);
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void storeSrevices() {
        List<List<String>> storeServices = response.extract().path("data.findAll{it.services.name == 'Windows Store'}");
        //data.services.storeservices
        System.out.println(storeServices);

    }

    //11. Get all the storeId of all the store
    @Test
    public void allStoreId() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println(ids);
    }
//12. Get id of all the store

    //13. Find the store names Where state = ND
    @Test
    public void storeNameWithND() {
        List<String> name = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println(name);

    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void findNumberOfServices() {
        List<List<?>> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println(services);
        int number = services.size();
        System.out.println("Number of services " + number);
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void getCreatedAll() {
        List<String> create = response.extract().path("data.findAll{it.name == 'Windows Store'}.createdAt");

        System.out.println("CreatedAt for all services whose name = “Windows Store” " + create);
    }

    //            16. Find the name of all services Where store name = “Fargo”
    @Test
    public void storeName() {
        List<HashMap<String, ?>> name = response.extract().path("data.findAll{it.name == 'Fargo'}");
//        HashMap<String, ?> productMap=name.get(0);
//        String name1=(String) productMap.get("name");
        System.out.println(name);
//        System.out.println(name1);
    }

    //            17. Find the zip of all the store
    @Test
    public void findZip() {
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("zip of all the store " + zip);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void zipOfStore() {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println(" Zip of store name = Roseville " + zip);
    }
//            19. Find the storeservices details of the service name = Magnolia Home Theater

    @Test
    public void storeSaervices() {
        List<String> services = response.extract().path("data.findAll{it.services.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println(services);
    }

    //20. Find the lat of all the stores
    @Test
    public void findLat() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("The lat of all the stores " + lat);
    }
}
