package org.application;

import java.util.List;
import java.util.Scanner;

public class MainApp
{
    private static Service  service=new Service();
    private static Scanner scanner=new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println("===========================================================");
        System.out.println( "Select Mode of Operation" );
        System.out.println("1.Insert Product");
        System.out.println("2. Remove Product");
        System.out.println("3 Update Product");
        System.out.println("4. Display All Product");
        System.out.println("5. Display a Product by Id");
        System.out.println("6. get Product by applying Filter");
        System.out.println("7. Exit");

        int choise=scanner.nextInt();

        switch (choise){
            case 1:
                    insertOp();
                    break;
            case 2:
                    deleteOp();
                    break;
            case 3:
                    updateOp();
                    break;
            case 4:
                    displayProduct();
                    break;
            case 5:
                    searchById();
                    break;
            case 6:
                    filter();
                    break;
            case 8:
                    System.exit(0);
                    break;
            default:
                System.out.println("invalid input");
        }
        main(args);
    }
    private static void filter(){
        System.out.println("======================================");
        System.out.println("Enter your filter");
        System.out.println("1. Display Products by Range of Price");
        System.out.println("2. Display Products by category");
        int choise=scanner.nextInt();
        switch (choise){
            case 1:
                  displayByPrice();
                  break;
            case 2:
                  displayByCategory();
                  break;
        }
    }


    private static void displayByCategory() {
        System.out.println("Enter category");
        String prodType=scanner.next();
       List<Product> productList= service.getProdByCategory(prodType);
        System.out.println("ProductId \t productName \t productPrice \t productType");

        for (Product p:productList) {
            System.out.println(p.getProductId()+" \t\t\t"+p.getProductName()+" \t\t\t"+p.getProductPrice()+" \t\t\t"+p.getProductType());

        }
    }

    private static void displayByPrice() {
        System.out.println("Enter lower range of Price ");
        double prodPrice1=scanner.nextDouble();
        System.out.println("Enter Upper range of Price ");
        double prodPrice2=scanner.nextDouble();
        List<Product> productList= service.diplayByPrice(prodPrice1,prodPrice2);
        System.out.println("ProductId \t productName \t productPrice \t productType");

        for (Product p:productList) {
            System.out.println(p.getProductId()+" \t\t\t"+p.getProductName()+" \t\t\t"+p.getProductPrice()+" \t\t\t"+p.getProductType());

        }
    }

    private static void searchById() {
        System.out.println("Enter Product Id to display");
        int id=scanner.nextInt();
        if (service.getProdById(id)!=null) {
            Product product = service.getProdById(id);
            System.out.println("ProductId \t productName \t productPrice \t productType");
            System.out.println(product.getProductId() + "\t\t\t" + product.getProductName() + "\t\t\t" + product.getProductPrice() + "\t\t\t" + product.getProductType());
        }
    }

    private static void displayProduct() {
       List<Product> productList= service.displayAllProduct();
        System.out.println("ProductId \t productName \t productPrice \t productType");
        for (Product p:productList  ) {
            System.out.println(p.getProductId()+" \t\t\t"+p.getProductName()+" \t\t\t"+p.getProductPrice()+" \t\t\t"+p.getProductType());
        }
    }

    private static void updateOp() {
        System.out.println("Enter Product ID ");
        int prodId=scanner.nextInt();
        System.out.println("Enter Product Name");
        String prodName= scanner.next();
        System.out.println("Enter Product Price");
        double prodPrice=scanner.nextDouble();
        System.out.println("Enter Product Type");
        String prodType=scanner.next();

        Product productToUP=new Product(prodId,prodName,prodPrice,prodType);
        int n=service.updateOperation(productToUP);
        if (n!=0){
            System.out.println(n+" Product Updated");
        }else {
            System.out.println("Invalid Product Id or Product Not found");
        }
    }

    private static void deleteOp() {
        System.out.println("Enter Product ID to delete");
        int prodId=scanner.nextInt();

     int n= service.deleteOperation(prodId);
     if (n!=0){
        System.out.println(n+" Products removed");
     }else {
         System.out.println("Product Not found or Invalid ProductId");
     }
    }

    private static void insertOp() {
        System.out.println("Enter Product ID ");
        int prodId=scanner.nextInt();
        System.out.println("Enter Product Name");
        String prodName= scanner.next();
        System.out.println("Enter Product Price");
        double prodPrice=scanner.nextDouble();
        System.out.println("Enter Product Type");
        String prodType=scanner.next();

        Product newProduct=new Product(prodId,prodName,prodPrice,prodType);
        int  n=service.insertProdct(newProduct);
        System.out.println(n+" Record Added");
    }
}
